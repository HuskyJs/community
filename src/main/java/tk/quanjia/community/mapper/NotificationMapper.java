package tk.quanjia.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.quanjia.community.model.Notification;
import tk.quanjia.community.model.NotificationExample;

public interface NotificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    long countByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int deleteByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int insert(Notification row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int insertSelective(Notification row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    Notification selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int updateByExampleSelective(@Param("row") Notification row, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int updateByExample(@Param("row") Notification row, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int updateByPrimaryKeySelective(Notification row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Jul 29 17:12:44 CST 2022
     */
    int updateByPrimaryKey(Notification row);
}