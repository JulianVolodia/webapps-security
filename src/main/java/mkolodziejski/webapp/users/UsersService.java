package mkolodziejski.webapp.users;

public interface UsersService {

    UserAccount getUserAccountByLogin(String login);

    UserAccount getUserAccountByLoginAndPassword(String login, String password);
}
