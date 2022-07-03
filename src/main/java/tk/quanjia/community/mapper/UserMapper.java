package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.quanjia.community.model.User;


@Mapper
public interface UserMapper {
    //MySQL
//    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
//    void insert(User user);
//    @Select("select * from user where token = #{token}")
//    User findByToken(@Param("token") String token);

    //H2
    @Insert("insert into table_user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from table_user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from table_user where id = #{id}")
    User findById(Integer creator);
}