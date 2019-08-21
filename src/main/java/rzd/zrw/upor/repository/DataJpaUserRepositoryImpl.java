package rzd.zrw.upor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import rzd.zrw.upor.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudDepartmentRepository crudDepartmentRepository;

    @Override
    public User save(User user, int departmentId) {
        if (!user.isNew() && get(user.getId(), departmentId) == null) {
            return null;
        }
        user.setDepartment(crudDepartmentRepository.getOne(departmentId));
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int id, int departmentId) {
        return crudUserRepository.delete(id, departmentId) != 0;
    }

    @Override
    public User get(int id, int departmentId) {
        return crudUserRepository.findById(id).filter(user -> user.getDepartment().getId() == departmentId).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public List<User> getAllByDepartment(int departmentId) {
        return crudUserRepository.getAllByDepartment(departmentId);
    }

    @Override
    public User getWithDepartment(int id, int departmentId) {
        return crudUserRepository.getWithDepartment(id, departmentId);
    }
}
