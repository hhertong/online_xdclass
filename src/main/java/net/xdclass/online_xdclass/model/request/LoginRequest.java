package net.xdclass.online_xdclass.model.request;

import lombok.Data;

/**
 * 登录 request
 */
@Data
public class LoginRequest {

    private String phone;

    private String pwd;

}
