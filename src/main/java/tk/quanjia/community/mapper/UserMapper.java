package tk.quanjia.community.mapper;

import org.apache.ibatis.annotations.*;
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

    @Select("select * from table_user where account_id = #{accountId}")
    User findByAccountId(String accountId);

    @Update("update table_user set name = #{name},token = #{token}, gmt_modified = #{gmtModified},avatar_url = #{avatarUrl}")
    void update(User dbUser);
}