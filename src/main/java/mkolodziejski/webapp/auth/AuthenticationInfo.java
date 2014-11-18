package mkolodziejski.webapp.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class AuthenticationInfo implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationInfo.class);

    private String userLogin;

    public AuthenticationInfo() {
        log.info("Creating AuthenticationInfo instance");
    }


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationInfo that = (AuthenticationInfo) o;

        if (!userLogin.equals(that.userLogin)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userLogin.hashCode();
    }
}
