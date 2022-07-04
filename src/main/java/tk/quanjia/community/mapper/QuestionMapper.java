package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.*;
import tk.quanjia.community.dto.QuestionDTO;
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

    @Select("select * from table_question where id = #{id}")
    Question getById(@Param(value="id") Integer id);

    @Update("update table_question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void update(Question question);
}
