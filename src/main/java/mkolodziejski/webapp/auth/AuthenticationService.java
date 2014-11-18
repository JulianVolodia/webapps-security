package mkolodziejski.webapp.auth;

public interface AuthenticationService {

    AuthenticationStatus authenticate(String login, String password);

    void logout();
}
