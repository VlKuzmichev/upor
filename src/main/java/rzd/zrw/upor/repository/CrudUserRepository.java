package rzd.zrw.upor.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import rzd.zrw.upor.model.User;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    Optional<User> findById(Integer id);

    @Query("SELECT u FROM User u WHERE u.department.id=:departmentId ORDER BY u.fullName DESC")
    List<User> getAllByDepartment(@Param("departmentId") int departmentId);

    @Override
    List<User> findAll(Sort sort);

    @Query("SELECT u FROM User u JOIN FETCH u.department WHERE u.id =:id AND u.department.id =:departmentId")
    User getWithDepartment(@Param("id") int id, @Param("departmentId") int departmentId);

    User getByEmail(String email);
}
