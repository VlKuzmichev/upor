package rzd.zrw.upor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rzd.zrw.upor.service.UserService;
import rzd.zrw.upor.web.user.AdminRestController;


@Controller
public class RootController {
    @Autowired
    //private UserService service;
    private AdminRestController controller;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", controller.getAll());
        return "users";
    }

//    @PostMapping("/users")
//    public String setUser(HttpServletRequest request) {
//        int userId = Integer.valueOf(request.getParameter("userId"));
//        //SecurityUtil.setAuthUserId(userId);
//        return "redirect:users";
//    }
}
