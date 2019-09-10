package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;
import rzd.zrw.upor.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminUIController {
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("fullName") String fullName,
                               @RequestParam("departmentId") Integer departmentId,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, fullName, email, password, Role.ROLE_USER);
        user.setDepartment(departmentService.get(departmentId));
        if (user.isNew()) {
            userService.create(user);
        }
    }
}
