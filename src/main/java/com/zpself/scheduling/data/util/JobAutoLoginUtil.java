

package com.zpself.scheduling.data.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



/**
 * @author MingSu
 */


//@Component("jobAutoLoginUtil")
public class JobAutoLoginUtil {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${jobAccount:}")
    private String account;
    @Value("${jobPassword:}")
    private String password;

    public void login() {
        if(authenticationManager!=null&&StringUtils.isNotBlank(account)&&StringUtils.isNotBlank(password)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}

