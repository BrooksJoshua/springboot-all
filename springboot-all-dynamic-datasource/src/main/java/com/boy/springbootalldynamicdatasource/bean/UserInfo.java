package com.boy.springbootalldynamicdatasource.bean;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 17:00
 */

public class UserInfo implements UserDetails {
    private static final long serialVersionUID = -1041327031937199938L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登录名称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
    private String loginType;

    /**加密权限*/
    private  Boolean encryption;
    /**解密权限 */
    private  Boolean decryption;

    public String getLoginType() {
        return loginType;
    }


    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public Set<AuthorityInfo> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityInfo> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public Boolean getDecryption() {
        return decryption;
    }

    public void setDecryption(Boolean decryption) {
        this.decryption = decryption;
    }
}

