package mkolodziejski.webapp.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import mkolodziejski.webapp.users.UserAccount;
import mkolodziejski.webapp.users.UsersService;

public class AuthenticationServiceImpl implements AuthenticationService {

    protected final UsersService usersService;

    protected final Provider<AuthenticationInfo> authenticationInfoProvider;

    @Inject
    public AuthenticationServiceImpl(UsersService usersService, Provider<AuthenticationInfo> authenticationInfoProvider) {
        this.usersService = usersService;
        this.authenticationInfoProvider = authenticationInfoProvider;
    }


    @Override
    public AuthenticationStatus authenticate(String login, String password) {
        AuthenticationInfo authenticationInfo = authenticationInfoProvider.get();
        AuthenticationStatus status;

        UserAccount userAccount = usersService.getUserAccountByLoginAndPassword(login, password);

        if(userAccount != null) {
            authenticationInfo.setUserLogin(userAccount.getLogin());
            status = AuthenticationStatus.OK;

        } else {
            authenticationInfo.setUserLogin(null);
            status = AuthenticationStatus.FAILED;
        }

        return status;
    }

    @Override
    public void logout() {
        AuthenticationInfo authenticationInfo = authenticationInfoProvider.get();
        authenticationInfo.setUserLogin(null);
    }
}
