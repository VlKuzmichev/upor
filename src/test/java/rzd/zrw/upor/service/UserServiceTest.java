package rzd.zrw.upor.service;

import org.junit.Assert;
import org.junit.Test;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static rzd.zrw.upor.UserTestData.USER;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void testGet() throws Exception {
        User user = service.get(100001);
        Assert.assertEquals(USER, user);
    }

    @Test
    public void testCreate() throws Exception {
        User user = service.save(new User(1, "newUser", "TEST TEST TEST", "email@email.ru", "password", Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100003);
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
    }
}
