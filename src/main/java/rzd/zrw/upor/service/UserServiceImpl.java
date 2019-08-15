package rzd.zrw.upor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.repository.DepartmentRepository;
import rzd.zrw.upor.repository.UserRepository;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static rzd.zrw.upor.util.ValidationUtil.checkNotFound;
import static rzd.zrw.upor.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public User create(User user, int departmentId) {
        return repository.save(user, departmentId);
    }

    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    @Transactional
    public void update(User user, int userId) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user, userId), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public List<User> getAllByDepartment(Department department){
        return repository.getAllByDepartment(department);
    }
}
