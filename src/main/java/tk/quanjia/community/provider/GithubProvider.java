package tk.quanjia.community.provider;


import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import tk.quanjia.community.dto.AccessTokenDTO;
import tk.quanjia.community.dto.GithubUser;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        System.out.println("---------------GithubProvider------------");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.Companion.create(JSON.toJSONString(accessTokenDTO),mediaType);

        Request request = new Request
                .Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println("---------------输出------------");
            System.out.println(string);
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class); //将string的json对象自动转换为java的类对象
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
