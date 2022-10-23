public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password)
    {

        this.username = username;
        this.password = password;
    }

    /**
     * Checks if the given credentials are the same.
     * @return True if the credentials are the same.
     */
    public Boolean equals(Credentials credentials)
    {
        return credentials.username.equalsIgnoreCase(username) && credentials.password.equals(password);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
