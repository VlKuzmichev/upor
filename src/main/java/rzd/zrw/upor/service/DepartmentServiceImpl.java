package rzd.zrw.upor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.repository.DepartmentRepository;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

import static rzd.zrw.upor.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    @Transactional
    public Department create(Department department) {
        return repository.save(department);
    }

    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Department get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    @Transactional
    public void update(Department department, int id) {
        Assert.notNull(department, "department must not be null");
        checkNotFoundWithId(repository.save(department), department.getId());
    }

    @Override
    public List<Department> getAll() {
        return repository.getAll();
    }
}
