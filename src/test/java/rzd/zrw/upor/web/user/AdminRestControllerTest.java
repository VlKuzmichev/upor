package rzd.zrw.upor.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rzd.zrw.upor.UserTestData;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static rzd.zrw.upor.UserTestData.ADMIN;


public class AdminRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeEach
    void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/controllers-context.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @Test
    void delete() throws Exception {
        controller.delete(UserTestData.USER_ID, 100003);
        Collection<User> users = controller.getAll();
        assertEquals(users.size(), 2);
        assertEquals(users.iterator().next(), ADMIN);
    }

//    @Test(expected = NotFoundException.class)
    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
        controller.delete(10, 100003));
    }
}