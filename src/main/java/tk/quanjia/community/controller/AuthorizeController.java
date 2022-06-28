package tk.quanjia.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.quanjia.community.dto.AccessTokenDTO;
import tk.quanjia.community.dto.GithubUser;
import tk.quanjia.community.provider.GithubProvider;

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
    /**
     * 如果用户接受您的请求，GitHub 将重定向回您的站点，
     * 其中，代码参数为临时 code，state 参数为您在上一步提供的状态。
     * 临时代码将在 10 分钟后到期。 如果状态不匹配，然后第三方创建了请求，您应该中止此过程。
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);//您收到的响应第 1 步的代码。
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);//您从 GitHub 收到的 OAuth 应用程序 的客户端 ID。
        accessTokenDTO.setClient_secret(clientSecret);//您从 GitHub 收到的 OAuth 应用程序 的客户端密钥。
        accessTokenDTO.setRedirect_url(redirectUri);//用户获得授权后被发送到的应用程序中的 URL。

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
