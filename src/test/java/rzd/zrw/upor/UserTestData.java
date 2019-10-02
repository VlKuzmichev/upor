package rzd.zrw.upor;

import org.springframework.test.web.servlet.ResultMatcher;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static rzd.zrw.upor.TestUtil.readFromJsonMvcResult;
import static rzd.zrw.upor.TestUtil.readListFromJsonMvcResult;

import java.util.List;


public class UserTestData {
    public static final Integer USER_ID = 100004;
    public static final Integer ADMIN_ID = 100006;
    public static final Integer DEPART_ID = 100003;

    public static final Department DEPARTMENT = new Department(DEPART_ID, "RCS3", "Full name rcs3");

    public static final User USER = new User(USER_ID, "User", "Ivanov Ivan Ivanovich",
            "usersv@yandex.ru", "password", Role.ROLE_USER);
    public static final User DISPATCHER = new User(USER_ID + 1, "Disp", "Dispetcherov Disp Dispetcherovich",
            "disp@yandex.ru", "disp", Role.ROLE_DISPATCHER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "Adminovich Adminus Adminov",
            "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static User getCreated() {
        User user = new User(null, "newUser", "TEST TEST TEST", "email@email.ru", "password", Role.ROLE_USER);
        user.setDepartment(DEPARTMENT);
        return user;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "department", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "department", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
