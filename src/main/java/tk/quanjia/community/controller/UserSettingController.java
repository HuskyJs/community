package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.quanjia.community.dto.CommentCreateDTO;
import tk.quanjia.community.dto.FileDTO;
import tk.quanjia.community.dto.UserDTO;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.User;
import tk.quanjia.community.model.UserExample;
import tk.quanjia.community.provider.HFileResult;
import tk.quanjia.community.provider.HuaweiCloudProvider;
import tk.quanjia.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class UserSettingController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HuaweiCloudProvider huaweiCloudProvider;
    @Autowired
    private UserService userService;

    @GetMapping("/setting")
    public String Setting() {
        return "setting";
    }


    @PostMapping("/setting/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        System.out.println("文件为："+ file.getOriginalFilename());
        try {
            HFileResult hFileResult = huaweiCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());//上传文件
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(hFileResult.getFileUrl());
            return fileDTO;
        } catch (Exception e) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }

    }

    @PostMapping("/setting")
    public String Setting(@RequestParam(name="userid") Long  userid,
                          @RequestParam(name="username") String  username,
                       HttpServletRequest request,
                       Model model) {
        if(username.equals("")){
            model.addAttribute("settingError", "用户名不能为空");
            return "setting";
        }

        UserExample userNameExample = new UserExample();
        userNameExample.createCriteria()
                .andNameEqualTo(username);
        List<User> userName = userMapper.selectByExample(userNameExample);
        if (userName.size()!=0) {
            model.addAttribute("settingError", "该用户名已存在，你可以换一个");
            return "setting";
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(userid);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        user.setName(username);
        user.setGmtModified(System.currentTimeMillis());
//        user.setAvatarUrl("https://myimg-rqj.obs.cn-east-3.myhuaweicloud.com:443/3feac0fab03a4ffba467123fd9d146ed.jpg?AccessKeyId=JOTO9PWRAB5RZRIWEKEQ&Expires=1690742673&Signature=RYNgsb%2F2h2Pt1tN8WphJzea0Qmo%3D");
        userService.createOrUpdate(user);
        System.out.println("---------------------");
        //登录成功  写cookie 和 session
        request.getSession().setAttribute("user", user);
        return "setting";
    }


}