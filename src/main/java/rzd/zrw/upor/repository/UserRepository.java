package rzd.zrw.upor.repository;


import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user, int id);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    List<User> getAllByDepartment(Department department);
}
