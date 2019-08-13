package rzd.zrw.upor.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static rzd.zrw.upor.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void testGet() throws Exception {
        User user = service.get(100000);
        assertMatch(user, USER);
    }

    @Test
    public void testCreate() throws Exception {
        User user = new User(null, "newUser", "TEST TEST TEST", "email@email.ru", "password", Role.ROLE_USER);
        User created = service.create(user);
        user.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, DISPATCHER, user, USER);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100001);
        assertMatch(service.getAll(), ADMIN, USER);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailCreate() throws Exception {
        service.create(new User(null, "newUser", "TEST TEST TEST", "disp@yandex.ru", "password", Role.ROLE_USER));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(5);
    }

    @Test(expected = NotFoundException.class)
    public void textGetByEmail() throws Exception {
        service.getByEmail("usersv@yandex.u");
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, DISPATCHER, USER);
    }
}
