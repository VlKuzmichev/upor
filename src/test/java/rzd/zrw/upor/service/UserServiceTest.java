package rzd.zrw.upor.service;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static rzd.zrw.upor.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    void testGet() throws Exception {
        User user = service.get(USER_ID, DEPART_ID);
        assertMatch(user, USER);
    }

    @Test
    void testGetNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.get(1, DEPART_ID);
        });
    }

    @Test
    void testCreate() throws Exception {
        User user = getCreated();
        User created = service.create(user, DEPART_ID);
        user.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, DISPATCHER, user, USER);
    }

    @Test
    void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, DISPATCHER, USER);
    }

    @Test
    void testDelete() throws Exception {
        service.delete(USER_ID + 1, DEPART_ID);
        assertMatch(service.getAll(), ADMIN, USER);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(5, DEPART_ID));
    }

    @Test
    void testGetByEmail() throws Exception {
        User user = service.getByEmail("usersv@yandex.ru");
        assertMatch(user, USER);
    }

    //@Test(expected = NotFoundException.class)
    @Test
    void testGetByEmailNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.getByEmail("usersv@yandex.u");
        });
    }

    @Test
    void testDuplicateMailCreate() throws Exception {
        assertThrows(DataIntegrityViolationException.class, () ->
                service.create(new User(null, "Duplicate", "Ivanov Ivan Ivanovich", "usersv@yandex.ru",
                        "newPass", Role.ROLE_USER), DEPART_ID));
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setFullName("Updated User Userovich");
        service.update(updated, DEPART_ID);
        assertMatch(service.get(USER_ID, DEPART_ID), updated);
    }

    @Test
    void testGetWithDepartment() throws Exception {
        User user = service.getWithDepartment(USER_ID, 100003);
        assertMatch(user, USER);
    }

    @Test
    void testGetWithDepartmentNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            User user = service.getWithDepartment(USER_ID, 100000);
            assertMatch(user, USER);
        });
    }
}
