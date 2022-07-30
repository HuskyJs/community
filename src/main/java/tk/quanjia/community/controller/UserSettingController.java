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
public class UserSettingController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/setting")
    public String login() {
        return "setting";
    }


    @PostMapping("/setting")
    public String Setting(@RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "accountId", required = false) String accountId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        if(accountId==null || accountId.equals("")){
            return "setting";
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        user.setName(username);
        user.setAccountId(accountId);
        user.setGmtModified(user.getGmtCreate());
//        user.setAvatarUrl("https://myimg-rqj.obs.cn-east-3.myhuaweicloud.com:443/3feac0fab03a4ffba467123fd9d146ed.jpg?AccessKeyId=JOTO9PWRAB5RZRIWEKEQ&Expires=1690742673&Signature=RYNgsb%2F2h2Pt1tN8WphJzea0Qmo%3D");
        userService.createOrUpdate(user);
        System.out.println("---------------------");
        response.addCookie(new Cookie("token", user.getToken()));//将token加入到Cookie中   后续实现持续性的登录状态时  会从cookie中获取
        //登录成功  写cookie 和 session
        request.getSession().setAttribute("user", user);
        return "setting";
    }


}