package rzd.zrw.upor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public User create(User user, int departmentId) {
        return repository.save(user, departmentId);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void delete(int id, int departmentId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, departmentId), id);
    }

    @Override
    public User get(int id, int departmentId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, departmentId), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void update(User user, int userId) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user, userId), user.getId());
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Cacheable("users")
    @Override
    public List<User> getAllByDepartment(int departmentId) {
        return repository.getAllByDepartment(departmentId);
    }

    @Override
    public User getWithDepartment(int id, int departmentId) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithDepartment(id, departmentId), id);
    }

}
