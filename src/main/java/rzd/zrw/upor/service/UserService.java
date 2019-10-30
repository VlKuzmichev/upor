package rzd.zrw.upor.service;

import rzd.zrw.upor.model.User;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    public User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user, int id);

    void update(User user);

    List<User> getAll();

    List<User> getAllByDepartment(int departmentId);

    User getWithDepartment(int id) throws NotFoundException;

    void enable(int id, boolean enable);

    void changePassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
