package com.zhf.mycommunity.controller;

import com.zhf.mycommunity.dto.AccesstokenDTO;
import com.zhf.mycommunity.dto.GithubUser;
import com.zhf.mycommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.PrinterGraphics;
import java.io.IOException;

@Controller
public class AuthorizeController {
    
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    
    
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) throws IOException {
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id("clientid");
        accesstokenDTO.setClient_secret("clientSecret");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_rui("redirectUri");
        accesstokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
        
    }


}
