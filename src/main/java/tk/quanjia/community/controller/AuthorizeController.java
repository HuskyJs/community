package tk.quanjia.community.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.dto.AccessTokenDTO;
import tk.quanjia.community.dto.GithubUser;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.User;
import tk.quanjia.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;


    /**
     * 如果用户接受您的请求，GitHub 将重定向回您的站点，
     * 其中，代码参数为临时 code，state 参数为您在上一步提供的状态。
     * 临时代码将在 10 分钟后到期。 如果状态不匹配，然后第三方创建了请求，您应该中止此过程。
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {//Spring会自动拿到上下文中的HttpServletRequest
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);//您收到的响应第 1 步的代码。
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);//您从 GitHub 收到的 OAuth 应用程序 的客户端 ID。
        accessTokenDTO.setClient_secret(clientSecret);//您从 GitHub 收到的 OAuth 应用程序 的客户端密钥。
        accessTokenDTO.setRedirect_url(redirectUri);//用户获得授权后被发送到的应用程序中的 URL。

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());

        if (githubUser != null) {
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtCreate(user.getGmtCreate());
            userMapper.insert(user);
            System.out.println("插入成功");
            //登录成功  写cookie 和 session
            request.getSession().setAttribute("user",githubUser);
        } else {
            //失败  重新登录
        }
        return "redirect:/";  //写redirect 重定向  特别注意

    }
}
