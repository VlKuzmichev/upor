package rzd.zrw.upor.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {

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
}
