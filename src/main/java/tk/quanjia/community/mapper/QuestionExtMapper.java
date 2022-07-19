package tk.quanjia.community.mapper;

import tk.quanjia.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question row);
    int incCommentCount(Question row);
    List<Question> selectRelated(Question question);
}