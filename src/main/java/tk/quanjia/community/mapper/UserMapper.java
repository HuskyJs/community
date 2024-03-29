package tk.quanjia.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.quanjia.community.model.User;
import tk.quanjia.community.model.UserExample;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int insert(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int insertSelective(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    List<User> selectByExampleWithRowbounds(UserExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int updateByPrimaryKeySelective(User row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_USER
     *
     * @mbg.generated Mon Aug 01 03:14:24 CST 2022
     */
    int updateByPrimaryKey(User row);
}