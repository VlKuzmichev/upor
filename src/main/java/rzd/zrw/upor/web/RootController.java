package rzd.zrw.upor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import rzd.zrw.upor.AuthorizedUser;
import rzd.zrw.upor.PasswordsEqualConstraint;
import rzd.zrw.upor.PasswordsEqualConstraintValidator;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;
import rzd.zrw.upor.service.UserService;
import rzd.zrw.upor.to.PasswordFormTo;

import javax.validation.Valid;


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
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser) {
        //model.addAttribute("userTo", authUser.getUserTo());
        model.addAttribute("passwordFormTo", new PasswordFormTo());
        return "profile";
    }

    @GetMapping("/profile?error=true")
    public String incorrectPassword(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser) {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid PasswordFormTo passwordFormTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authUser) {

        if (result.hasErrors()) {
            return "profile";
        }
        User user = userService.getWithDepartment(authUser.getId());
        if (!userService.checkIfValidOldPassword(user, passwordFormTo.getOldPassword())){
            return "profile";
        }
        user.setPassword(passwordFormTo.getNewPassword());
        userService.update(user, SecurityUtil.authUserId());
        status.setComplete();
        return "redirect:/";
    }

}
