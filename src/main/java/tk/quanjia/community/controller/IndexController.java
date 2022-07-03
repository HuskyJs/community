package tk.quanjia.community.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.mapper.QuestionMapper;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.User;
import tk.quanjia.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller //我允许当前的类接收前端请求
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")    //  "/"代表默认启动模板
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("token")) {//先从Cookie中寻找是否有 “token” 关键字，有则代表存在已经登录的用户
                    String value = cookie.getValue();
                    User user = userMapper.findByToken(value);
                    if (user != null) {//如果根据token去数据库查找对应用户，加入session中，实现页面登录
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> qUestionDTOList = questionService.list(); //QuestionDTO  为Question对象中加入user成员，成为一个新的对象
        model.addAttribute("questions", qUestionDTOList);
        return "index";
    }
}