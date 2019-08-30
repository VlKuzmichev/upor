package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.UserService;

import java.net.URI;
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

    @GetMapping("/bydepartment")
    public List<User> getAllByDepartment(@RequestBody int departmentId) {
        return service.getAllByDepartment(departmentId);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return service.get(id);
    }

    public User create(User user) {
        checkNew(user);
        return service.create(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        User created = create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
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

    @GetMapping("/byemail")
    public User getByMail(@RequestParam String email) {
        return service.getByEmail(email);
    }
}
