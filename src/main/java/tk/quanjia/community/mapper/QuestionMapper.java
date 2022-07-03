package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.quanjia.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into table_question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from table_question")
    List<Question> list();
}
