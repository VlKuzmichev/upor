package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.UserService;

import java.util.List;

import static rzd.zrw.upor.util.ValidationUtil.assureIdConsistent;
import static rzd.zrw.upor.util.ValidationUtil.checkNew;

@Controller
public class AdminRestController {

    @Autowired
    private UserService service;

    public List<User> getAll() {
        return service.getAll();
    }

    public List<User> getAllByDepartment(int departmentId) {
        return service.getAllByDepartment(departmentId);
    }

    public User get(int id, int departmentId) {
        return service.get(id, departmentId);
    }

    public User create(User user, int departmentId) {
        checkNew(user);
        return service.create(user, departmentId);
    }

    public void delete(int id, int departmentId) {
        service.delete(id, departmentId);
    }

    public void update(User user, int departmentId) {
        assureIdConsistent(user, departmentId);
        service.update(user, departmentId);
    }

    public User getByMail(String email) {
        return service.getByEmail(email);
    }
}
