package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;
import rzd.zrw.upor.service.UserService;
import rzd.zrw.upor.to.UserTo;
import rzd.zrw.upor.util.UserUtil;
import rzd.zrw.upor.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

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
    public User get(@PathVariable int id) {
        return userService.getWithDepartment(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

//    @PostMapping
//    public ResponseEntity<String> createOrUpdate(@Valid UserTo userTo, BindingResult result) {
//        if (result.hasErrors()) {
//            return ValidationUtil.getErrorResponse(result);
//        }
//        if (userTo.isNew()) {
//            User user = UserUtil.createNewFromTo(userTo);
//            user.setDepartment(departmentService.get(userTo.getDepartmentId()));
//            userService.create(user);
//        } else {
//            User user = userService.get(userTo.getId());
//            User updatedUser = UserUtil.updateFromTo(user, userTo);
//            updatedUser.setDepartment(departmentService.get(userTo.getDepartmentId()));
//            userService.update(updatedUser);
//        }
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public void createOrUpdate(@Valid UserTo userTo) {
        if (userTo.isNew()) {
            User user = UserUtil.createNewFromTo(userTo);
            user.setDepartment(departmentService.get(userTo.getDepartmentId()));
            userService.create(user);
        } else {
            User user = userService.get(userTo.getId());
            User updatedUser = UserUtil.updateFromTo(user, userTo);
            updatedUser.setDepartment(departmentService.get(userTo.getDepartmentId()));
            userService.update(updatedUser);
        }
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        userService.enable(id, enabled);
    }
}
