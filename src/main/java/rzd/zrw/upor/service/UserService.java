package rzd.zrw.upor.service;

import rzd.zrw.upor.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
