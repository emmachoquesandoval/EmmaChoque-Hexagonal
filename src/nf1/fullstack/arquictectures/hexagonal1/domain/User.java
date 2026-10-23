package nf1.fullstack.arquictectures.hexagonal1.domain;

public class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
