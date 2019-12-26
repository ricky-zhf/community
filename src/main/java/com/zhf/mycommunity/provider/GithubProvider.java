package com.zhf.mycommunity.provider;

import com.alibaba.fastjson.JSON;
import com.zhf.mycommunity.dto.AccesstokenDTO;
import com.zhf.mycommunity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO) throws IOException {
        MediaType mediaType  = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String[] split = string.split("&");
                String tokenStr = split[0];
                String[] split1 = tokenStr.split("=");
                String token = split1[1];
                //System.out.println(string);
                
                return token;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
    }
    
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //这个静态方法可以把String类型的json对象自动的直接转换为java的类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
    
}
