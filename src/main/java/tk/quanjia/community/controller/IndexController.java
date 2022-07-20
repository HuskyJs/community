package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.dto.PaginationDTO;
import tk.quanjia.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller //我允许当前的类接收前端请求
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")    //  "/"代表默认启动模板
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name="search",required  = false) String search
                        ) {
        PaginationDTO pagination = questionService.list(search,page,size); //QuestionDTO  为Question对象中加入user成员，成为一个新的对象
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}