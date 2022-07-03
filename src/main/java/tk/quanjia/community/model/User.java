package tk.quanjia.community.model;

import lombok.Data;

/**
 * @Data // lombok插件，用该注释可以自动生成get set方法
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;//获取用户头像
}
