package tk.quanjia.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginRegistController {
    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest,
                        Model model) {
        StringBuffer str = httpServletRequest.getRequestURL();
        String url = str.substring(0, str.length() - ("login").length());
        model.addAttribute("url", url);
        return "login";
    }
}
