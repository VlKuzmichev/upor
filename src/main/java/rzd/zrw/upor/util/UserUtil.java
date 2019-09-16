package rzd.zrw.upor.util;

import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getFullName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }
}