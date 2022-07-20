package tk.quanjia.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.quanjia.community.dto.NotificationDTO;
import tk.quanjia.community.dto.PaginationDTO;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.enums.NotificationStatusEnum;
import tk.quanjia.community.enums.NotificationTypeEnum;
import tk.quanjia.community.exception.CustomizeErrorCode;
import tk.quanjia.community.exception.CustomizeException;
import tk.quanjia.community.mapper.NotificationMapper;
import tk.quanjia.community.model.Notification;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.NotificationExample;
import tk.quanjia.community.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();//分页的DTO

        NotificationExample notificationExample = new NotificationExample();//直接用Mybatis生成的sql语句进行数据库操作
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);//查找所有通知的接收用户为userId的总数
        Integer totalCount =(int) notificationMapper.countByExample(notificationExample);

        paginationDTO.setPagination(totalCount, page, size);//设置总数量   当前页   页大小
        Integer currentPage = paginationDTO.getCurrentPage();
        Integer offset = size * (currentPage - 1);//起始索引


        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);//查找所有通知的接收用户为userId的总数
        example.setOrderByClause("gmt_create desc");//根据创建时间排序
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        List<NotificationDTO> notificationDTOs = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOs.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOs);
        return paginationDTO;
    }

    public NotificationDTO read(Long id, User user) {//读取通知
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification==null){//判断然后输出异常
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());//设置状态
        notificationMapper.updateByPrimaryKey(notification);//更新数据库

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }
}
