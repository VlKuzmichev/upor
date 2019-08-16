package rzd.zrw.upor.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static rzd.zrw.upor.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        User user = service.get(1);
    }

    @Test
    public void testCreate() throws Exception {
        User user = getCreated();
        User created = service.create(user, DEPART_ID);
        user.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, DISPATCHER, user, USER);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID + 1);
        assertMatch(service.getAll(), ADMIN, USER);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(5);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("usersv@yandex.ru");
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void testGetByEmailNotFound() throws Exception {
        User user = service.getByEmail("usersv@yandex.u");
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailCreate() throws Exception {
        service.create(USER, USER_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setFullName("Updated User Userovich");
        service.update(updated, DEPART_ID);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, DISPATCHER, USER);
    }

    @Test
    public void testGetWithDepartment() throws Exception {
        User user = service.getWithDepartment(USER_ID, 100003);
        assertMatch(user, USER);
    }

    @Test(expected = AssertionError.class)
    public void testGetWithDepartmentNotFound() throws Exception {
        User user = service.getWithDepartment(USER_ID, 100000);
        assertMatch(user, USER);
    }
}
