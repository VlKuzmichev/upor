package rzd.zrw.upor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import rzd.zrw.upor.model.Department;

import java.util.List;

@Repository
public class DataJpaDepartmentRepository implements DepartmentRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");


    @Autowired
    private CrudDepartmentRepository crudRepository;

    @Override
    public Department save(Department department) {
        return crudRepository.save(department);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Department get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
