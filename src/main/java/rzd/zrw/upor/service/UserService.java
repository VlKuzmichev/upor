package rzd.zrw.upor.service;

import rzd.zrw.upor.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    void update(User user);

    List<User> getAll();
}
