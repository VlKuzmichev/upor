package rzd.zrw.upor.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import rzd.zrw.upor.model.Department;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDepartmentRepository extends JpaRepository<Department, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Department d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Department save(Department department);

    @Override
    Optional<Department> findById(Integer id);

    @Override
    List<Department> findAll(Sort sort);
}
