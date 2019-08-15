package rzd.zrw.upor.service;

import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    public User create(User user, int departmentId);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user, int userID);

    List<User> getAll();

    List<User> getAllByDepartment(Department department);
}
