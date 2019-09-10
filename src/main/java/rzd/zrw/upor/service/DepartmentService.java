package rzd.zrw.upor.service;

import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.util.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {

    public Department create(Department department);

    void delete(int id) throws NotFoundException;

    Department get(int id) throws NotFoundException;

    void update(Department department, int id);

    List<Department> getAll();

}
