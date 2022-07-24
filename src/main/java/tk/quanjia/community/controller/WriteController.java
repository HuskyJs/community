package tk.quanjia.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.cache.TagCache;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.User;
import tk.quanjia.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WriteController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/write/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        model.addAttribute("tags", TagCache.get());
        return "write";
    }

    @GetMapping("/write")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "write";
    }

    @PostMapping("/write")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        boolean flag = false;
        if(title == null || title.equals("")){
            model.addAttribute("title", "标题不能为空");
            flag=true;
        }
        if(title.length()>1024){
            model.addAttribute("title", "长度太大");
            flag=true;
        }
        if(description == null || description.equals("")){
            model.addAttribute("description", "简要不能为空");
            flag=true;
        }
        if(description.length()>1024){
            model.addAttribute("description", "长度太大");
            flag=true;
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("tag", "标签不能为空");
            flag=true;
        }
        if(tag.length()>1024){
            model.addAttribute("tag", "长度太大");
            flag=true;
        }
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("tag", "输入非法标签:" + invalid);
            flag=true;
        }
        if(flag){
            return "write";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "write";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
