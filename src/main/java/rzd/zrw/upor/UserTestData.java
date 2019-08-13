package rzd.zrw.upor;

import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


public class UserTestData {
    public static final User USER = new User(100000, "User", "Ivanov Ivan Ivanovich",
            "usersv@yandex.ru", "password", Role.ROLE_USER);
    public static final User DISPATCHER = new User(100001, "Disp", "Dispetcherov Disp Dispetcherovich",
            "disp@yandex.ru", "disp", Role.ROLE_DISPATCHER);
    public static final User ADMIN = new User(100002, "Admin", "Adminovich Adminus Adminov",
            "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

}
