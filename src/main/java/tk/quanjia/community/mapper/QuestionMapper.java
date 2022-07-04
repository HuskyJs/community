package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.quanjia.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into table_question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from table_question limit #{offset}, #{size}")
    List<Question> list(@Param(value="offset") Integer offset, @Param(value="size") Integer size);

    @Select("select count(1) from table_question")
    Integer count();

    @Select("select * from table_question where creator = #{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value="userId") Integer userId, @Param(value="offset") Integer offset, @Param(value="size") Integer size);

}