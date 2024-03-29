package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tk.quanjia.community.dto.CommentDTO;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.enums.CommentTypeEnum;
import tk.quanjia.community.service.CommentService;
import tk.quanjia.community.service.QuestionService;

import java.util.List;

/**
 * 访问问题页面的时候调用
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //增加评论数
        questionService.incView(id);

        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOS);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }

    @GetMapping("/publish/delete/{id}")
    public String questionDelete(@PathVariable(name="id") Long id,
                           Model model){
        //删除文章
        questionService.deleteById(id);

        //删除回复
        commentService.deleteAllRelated(id);

        //删除完返回首页
        return "redirect:/";
    }
}
