package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.UserService;

import java.util.List;

import static rzd.zrw.upor.util.ValidationUtil.assureIdConsistent;
import static rzd.zrw.upor.util.ValidationUtil.checkNew;

@Controller
public class UserRestController {

    @Autowired
    private UserService service;

    //    @RequestMapping(value = "allUsers", method = RequestMethod.GET)
    public List<User> getAll() {
//        log.info("getAll");
        return service.getAll();
    }

    public List<User> getAllByDepartment(int departmentId) {
//        log.info("getAll");
        return service.getAllByDepartment(departmentId);
    }

    public User get(int id, int departmentId) {
//        log.info("get {}", id);
        return service.get(id, departmentId);
    }

    public User create(User user, int departmentId) {
//        log.info("create {}", user);
        checkNew(user);
        return service.create(user, departmentId);
    }

    public void delete(int id, int departmentId) {
//        log.info("delete {}", id);
        service.delete(id, departmentId);
    }

    public void update(User user, int departmentId) {
//           log.info("update {} with id={}", user, id);
        assureIdConsistent(user, departmentId);
        service.update(user, departmentId);
    }

    public User getByMail(String email) {
//           log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
