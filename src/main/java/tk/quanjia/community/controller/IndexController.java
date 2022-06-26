package tk.quanjia.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //我允许当前的类接收前端请求
public class IndexController {
    @GetMapping("/")    //  "/"代表默认启动模板
    public String index() {
        return "index";
    }
}