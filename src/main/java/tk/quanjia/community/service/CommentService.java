package tk.quanjia.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.quanjia.community.exception.CommentTypeEnum;
import tk.quanjia.community.exception.CustomizeErrorCode;
import tk.quanjia.community.exception.CustomizeException;
import tk.quanjia.community.mapper.CommentMapper;
import tk.quanjia.community.mapper.QuestionExtMapper;
import tk.quanjia.community.mapper.QuestionMapper;
import tk.quanjia.community.model.Comment;
import tk.quanjia.community.model.Question;

import java.util.Objects;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    /**
     * @Transactional 该注解是spring自动添加事务，将整个方法体当作一个事务
     * @param comment
     */
    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_WRONG);
        }

        if(Objects.equals(comment.getType(), CommentTypeEnum.COMMENT.getType())){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){//找不到评论  抛异常
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //插入评论
            commentMapper.insertSelective(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){//找不到问题  抛异常
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入问题
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }
}
