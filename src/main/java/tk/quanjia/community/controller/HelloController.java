package tk.quanjia.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //我允许当前的类接收前端请求
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name",name);
        return "hello";
    }
}