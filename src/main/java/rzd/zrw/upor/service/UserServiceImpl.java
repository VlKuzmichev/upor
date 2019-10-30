package rzd.zrw.upor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rzd.zrw.upor.AuthorizedUser;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.repository.DepartmentRepository;
import rzd.zrw.upor.repository.UserRepository;
import rzd.zrw.upor.util.exception.NotFoundException;
import rzd.zrw.upor.web.SecurityUtil;

import java.util.List;

import static rzd.zrw.upor.util.UserUtil.prepareToSave;
import static rzd.zrw.upor.util.UserUtil.updateFromTo;
import static rzd.zrw.upor.util.ValidationUtil.checkNotFound;
import static rzd.zrw.upor.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public User create(User user) {
//        return repository.save(user);
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
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

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void update(User user, int userId) {
        Assert.notNull(user, "user must not be null");
//        checkNotFoundWithId(repository.save(user), user.getId());
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void update(User user) {
//        repository.save(user);
        //  User userser = updateFromTo(get(user.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    //    @Cacheable("users")
    @Override
    public List<User> getAll() {
        User user = getWithDepartment(SecurityUtil.authUserId());
        if (user.getRoles().contains(Role.ROLE_sADMIN))
            return repository.getAll();
        return getAllByDepartment(user.getDepartment().getId());
        //return repository.getAll();
    }

    @Override
    public List<User> getAllByDepartment(int departmentId) {
        return repository.getAllByDepartment(departmentId);
    }

    @Override
    public User getWithDepartment(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithDepartment(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(password);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.getByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        return new AuthorizedUser(user);
    }

}
