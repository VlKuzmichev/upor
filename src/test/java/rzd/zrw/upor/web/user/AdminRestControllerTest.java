package rzd.zrw.upor.web.user;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rzd.zrw.upor.UserTestData;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

import static rzd.zrw.upor.UserTestData.ADMIN;


public class AdminRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/controllers-context.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @Test
    public void delete() throws Exception {
        controller.delete(UserTestData.USER_ID, 100003);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 2);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        controller.delete(10, 100003);
    }
}