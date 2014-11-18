package mkolodziejski.webapp.users;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersServiceImpl implements UsersService {

    @Inject
    protected Provider<Connection> connectionProvider;

    @Override
    public UserAccount getUserAccountByLogin(String login) {
        Preconditions.checkNotNull(login);

        UserAccount userAccount = null;

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select id, login, full_name from user_account where login = '" + login.toLowerCase() + "'");

            ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                userAccount = new UserAccount();
                userAccount.setId(rs.getInt(1));
                userAccount.setLogin(rs.getString(2));
                userAccount.setFullName(rs.getString(3));
            }


            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userAccount;
    }

    @Override
    public UserAccount getUserAccountByLoginAndPassword(String login, String password) {
        Preconditions.checkNotNull(login);
        Preconditions.checkNotNull(password);

        UserAccount userAccount = null;

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select id, login, full_name from user_account" +
                    " where login = '" + login.toLowerCase() + "'" +
                    "   and password = '" + password + "'");

            ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                userAccount = new UserAccount();
                userAccount.setId(rs.getInt(1));
                userAccount.setLogin(rs.getString(2));
                userAccount.setFullName(rs.getString(3));
            }


            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userAccount;
    }
}
