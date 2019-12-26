package com.zhf.mycommunity.dto;

public class AccesstokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_rui;
    private String state;

    
    
    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_rui() {
        return redirect_rui;
    }

    public void setRedirect_rui(String redirect_rui) {
        this.redirect_rui = redirect_rui;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
