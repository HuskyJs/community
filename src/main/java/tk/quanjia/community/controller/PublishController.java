package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.mapper.QuestionMapper;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model) {

        boolean flag = false;
        if(title == null || title.equals("")){
            model.addAttribute("title", "标题不能为空");
            flag=true;
        }
        if(description == null || description.equals("")){
            model.addAttribute("description", "简要不能为空");
            flag=true;
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("tag", "标签不能为空");
            flag=true;
        }
        if(flag){
            return "publish";
        }


        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        User user = null;
        System.out.println("title:"+title);
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            System.out.println("123123");
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("token")) {//先从Cookie中寻找是否有 “token” 关键字，有则代表存在已经登录的用户
                    System.out.println("======");
                    String value = cookie.getValue();
                    user = userMapper.findByToken(value);
                    if (user != null) {//如果根据token去数据库查找对应用户，加入session中，实现页面登录
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
