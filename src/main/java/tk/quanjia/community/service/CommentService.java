package tk.quanjia.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.quanjia.community.dto.CommentDTO;
import tk.quanjia.community.enums.CommentTypeEnum;
import tk.quanjia.community.enums.NotificationStatusEnum;
import tk.quanjia.community.enums.NotificationTypeEnum;
import tk.quanjia.community.exception.CustomizeErrorCode;
import tk.quanjia.community.exception.CustomizeException;
import tk.quanjia.community.mapper.*;
import tk.quanjia.community.model.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;
    /**
     * 新评论 以及 回复功能
     * @param comment 要插入的评论的对象
     * @Transactional 该注解是spring自动添加事务，将整个方法体当作一个事务
     */
    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_WRONG);
        }

        if (Objects.equals(comment.getType(), CommentTypeEnum.COMMENT.getType())) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {//找不到评论  抛异常
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }


            //插入评论
            commentMapper.insertSelective(comment);

            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);

            // 创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());


        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {//找不到问题  抛异常
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入问题
            comment.setCommentCount(0);
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            // 创建通知
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());

        }

    }

    /**
     * 增加通知
     * @param comment
     * @param receiver
     * @param notifierName
     * @param outerTitle
     * @param notificationType
     * @param outerId
     */
    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if (receiver == comment.getCommentator()) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insertSelective(notification);
    }


    /**
     * 得到所有的评论
     * @param id   父亲id
     * @param type 评论的类型  1:对文章本身的评论，也就是1级评论
     *                       2：对评论的评论，也就是二级评论
     * @return
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    /**
     * 删除当前页面下所有人的评论
     * @param id
     */
    public void deleteAllRelated(Long id) {
        //id 为1级评论人的父母id
        List<CommentDTO> FirstCommentDTOS = listByTargetId(id, CommentTypeEnum.QUESTION);//所有的一级评论

        for (CommentDTO firstCommentDTO:
                FirstCommentDTOS) {
            //commentDTO.getId()得到一级评论的id，其为二级评论的父id
            List<CommentDTO> SecondCommentDTOS = listByTargetId(firstCommentDTO.getId(), CommentTypeEnum.COMMENT);
            for (CommentDTO secondCommentDTO : SecondCommentDTOS){
                commentExtMapper.deleteRelatedComment(secondCommentDTO);
            }
            commentExtMapper.deleteRelatedComment(firstCommentDTO);
        }
    }
}
