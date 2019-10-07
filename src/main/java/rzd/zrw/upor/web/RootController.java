package rzd.zrw.upor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;
import rzd.zrw.upor.service.UserService;
import rzd.zrw.upor.to.UserTo;
import rzd.zrw.upor.util.UserUtil;
import rzd.zrw.upor.web.user.AbstractUserController;

import javax.validation.Valid;

import static rzd.zrw.upor.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;


@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "profile";
        }
            User user = userService.getWithDepartment(SecurityUtil.authUserId());
            User updatedUser = UserUtil.updateFromTo(user, userTo);
            //updatedUser.setDepartment(departmentService.get(userTo.getDepartmentId()));
         //   userService.update(updatedUser);

            userService.update(updatedUser, SecurityUtil.authUserId());
            SecurityUtil.get().update(userTo);
            status.setComplete();
            return "redirect:/";
//            return "redirect:logout";
    }

}
