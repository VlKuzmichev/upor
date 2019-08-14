package rzd.zrw.upor.repository;

import rzd.zrw.upor.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department save(Department department);

    // false if not found
    boolean delete(int id);

    // null if not found
    Department get(int id);

    List<Department> getAll();
}
