package rzd.zrw.upor.service;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static rzd.zrw.upor.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    void testGet() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    void testGetNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.get(1);
        });
    }

    @Test
    void testCreate() throws Exception {
//        User user = new User(null, "newUser", "TEST TEST TEST", "email@email.ru", "password", false, new Date(), Collections.singleton(Role.ROLE_USER));
//        user.setDepartment(DEPARTMENT);
        User user = getCreated();
        User created = service.create(user);
        user.setId(created.getId());
        assertMatch(user, created);
        assertMatch(service.getAll(), ADMIN, DISPATCHER, user, USER);
    }

    @Test
    void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, DISPATCHER, USER);
    }

    @Test
    void testDelete() throws Exception {
        service.delete(USER_ID + 1);
        assertMatch(service.getAll(), ADMIN, USER);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(5));
    }

    @Test
    void testGetByEmail() throws Exception {
        User user = service.getByEmail("usersv@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    void testGetByEmailNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.getByEmail("usersv@yandex.u");
        });
    }

    @Test
    void testDuplicateMailCreate() throws Exception {
        assertThrows(DataIntegrityViolationException.class, () -> {
            User user = new User(null, "Duplicate", "Ivanov Ivan Ivanovich", "usersv@yandex.ru",
                    "newPass", Role.ROLE_USER);
            user.setDepartment(DEPARTMENT);
            service.create(user);
        });
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setDepartment(DEPARTMENT);
        updated.setFullName("Updated User Userovich");
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void testGetWithDepartment() throws Exception {
        User user = service.getWithDepartment(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    void testGetWithDepartmentNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.getWithDepartment(USER_ID+100);
            assertMatch(user, USER);
        });
    }

    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "Ivanov Ivan Ivanovich", "usersv@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "Ivanov Ivan Ivanovich", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "Ivanov Ivan Ivanovich", "usersv@yandex.ru", "", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "Ivanov Ivan Ivanovich", "usersv@yandex.ru", "password", true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "Ivanov Ivan Ivanovich", "usersv@yandex.ru", "password", true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
    }
}
