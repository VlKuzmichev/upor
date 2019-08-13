package rzd.zrw.upor;

import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;

public class UserTestData {
    public static final User USER = new User(100001, "Userradio", "Petrov Petr Petrovich",
            "userrad@yandex.ru", "password", Role.ROLE_USER);
    public static final User DISPATCHER = new User(100001, "Disp", "Dispetcherov Disp Dispetcherovich",
            "disp@yandex.ru", "disp", Role.ROLE_DISPATCHER);
    public static final User ADMIN = new User(100003, "Admin", "Adminovich Adminus Adminov",
            "admin@gmail.com", "admin", Role.ROLE_ADMIN);
}
