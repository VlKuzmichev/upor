package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.UserService;

import java.util.List;

import static rzd.zrw.upor.util.ValidationUtil.assureIdConsistent;
import static rzd.zrw.upor.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    public static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    public List<User> getAllByDepartment(int departmentId) {
        return service.getAllByDepartment(departmentId);
    }

    @GetMapping("/{id}")
    public User get(int id) {
        return service.get(id);
    }

    public User create(User user, int departmentId) {
        checkNew(user);
        return service.create(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(int id) {
        service.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        assureIdConsistent(user, id);
        service.update(user, id);
    }

    public User getByMail(String email) {
        return service.getByEmail(email);
    }
}
