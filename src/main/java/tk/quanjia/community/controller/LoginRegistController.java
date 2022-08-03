package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.User;
import tk.quanjia.community.model.UserExample;
import tk.quanjia.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class LoginRegistController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest,
                        Model model) {
//        StringBuffer str = httpServletRequest.getRequestURL();
//        String url = str.substring(0, str.length() - ("login").length());
//        model.addAttribute("url", url);
//        System.out.println("url:  "+url);
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam(name  = "accountId", required = false) String accountId,
                        @RequestParam(name = "password", required = false) String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        System.out.println("-------------登录----------");

        if (accountId == null || accountId.equals("") || password == null || password.equals("")) {
            return "login";
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);

        if(users.isEmpty()){
            model.addAttribute("loginError", "账号不存在");
            return "login";
        }

        System.out.println(users.toString());

        User user = users.get(0);


        if (user.getPassword().equals(password)) {
            // 登录成功
            System.out.println("---------------------");
            System.out.println("1：" +user.getToken());
            System.out.println("2：" +user.getAccountId());
            System.out.println("3：" +user.getName());
            System.out.println("4：" +user.getId());
            response.addCookie(new Cookie("token", user.getToken()));//将token加入到Cookie中   后续实现持续性的登录状态时  会从cookie中获取
            // 登录成功  写cookie 和 session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "密码不正确");
            return "login";
        }
    }

    @GetMapping("/login/regist")
    public String loginToRegister() {
        return "regist";
    }

    @PostMapping("/login/registOK")
    public String register(@RequestParam(name = "username", required = false) String username,
                           @RequestParam(name = "accountId", required = false) String accountId,
                           @RequestParam(name = "password", required = false) String password,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {
        System.out.println("注册");
        if (accountId == null || accountId.equals("")) {
            model.addAttribute("registError", "输入为空");
            return "regist";
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        if (!users.isEmpty()) {
            model.addAttribute("registError", "用户已存在");
            return "regist";
        }
        User user = new User();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setName(username);
        user.setPassword(password);
        user.setAccountId(accountId);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl("https://myimg-rqj.obs.cn-east-3.myhuaweicloud.com:443/3feac0fab03a4ffba467123fd9d146ed.jpg?AccessKeyId=JOTO9PWRAB5RZRIWEKEQ&Expires=1690742673&Signature=RYNgsb%2F2h2Pt1tN8WphJzea0Qmo%3D");

        System.out.println("---------------------");
        response.addCookie(new Cookie("token", token));//将token加入到Cookie中   后续实现持续性的登录状态时  会从cookie中获取
        //登录成功  写cookie 和 session
        request.getSession().setAttribute("user", user);
        return "login";
    }

}
