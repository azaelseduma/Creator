package com.creator.service.user;

/**
 * Created by Azael on 2017/08/28.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
