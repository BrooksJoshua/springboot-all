package com.boy.springbootalldynamicdatasource.bean;


/**
 * Created by zouhanbiao on 2017/5/24.
 */
public class AuthorityInfo  {//implements GrantedAuthority
    private static final long serialVersionUID = -175781100474818800L;

    /**
     * 权限CODE
     */
    private String authority;

    public AuthorityInfo(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
