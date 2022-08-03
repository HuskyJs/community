package tk.quanjia.community.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private String accountId;
}
