package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.quanjia.community.dto.CommentDTO;
import tk.quanjia.community.model.Comment;
import tk.quanjia.community.model.CommentExample;
import tk.quanjia.community.model.Question;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment row);

    void deleteRelatedComment(CommentDTO row);
}