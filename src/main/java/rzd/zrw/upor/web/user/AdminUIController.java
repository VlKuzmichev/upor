package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;
import rzd.zrw.upor.service.UserService;
import rzd.zrw.upor.to.UserTo;
import rzd.zrw.upor.util.UserUtil;

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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return userService.getWithDepartment(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(UserTo userTo) {
        if (userTo.isNew()) {
            User user = UserUtil.createNewFromTo(userTo);
            user.setDepartment(departmentService.get(userTo.getDepartmentId()));
            userService.create(user);
        }else {
            userService.update(userTo);
        }
    }


    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        userService.enable(id, enabled);
    }
}
