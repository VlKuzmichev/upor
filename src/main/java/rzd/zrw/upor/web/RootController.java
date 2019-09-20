package rzd.zrw.upor.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        return "users";
    }

//    @PostMapping("/users")
//    public String setUser(HttpServletRequest request) {
//        int userId = Integer.valueOf(request.getParameter("userId"));
//        //SecurityUtil.setAuthUserId(userId);
//        return "redirect:users";
//    }
}
