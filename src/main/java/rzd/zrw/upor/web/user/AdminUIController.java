package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.Role;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminUIController {
    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("fullname") String fullname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, fullname, name, email, password, Role.ROLE_USER);
        // Temporary hardcode because Department Entity isn't ready
        user.setDepartment(new Department(100003,"RCS3","Full name rcs3"));
        if (user.isNew()) {
            service.create(user);
        }
    }
}
