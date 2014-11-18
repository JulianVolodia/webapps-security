package mkolodziejski.webapp.users;

public class UserAccount {

    protected Integer id;
    protected String login;
    protected String password;
    protected String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount userAccount = (UserAccount) o;

        if (id != null ? !id.equals(userAccount.id) : userAccount.id != null) return false;
        if (login != null ? !login.equals(userAccount.login) : userAccount.login != null) return false;
        if (fullName != null ? !fullName.equals(userAccount.fullName) : userAccount.fullName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }
}
