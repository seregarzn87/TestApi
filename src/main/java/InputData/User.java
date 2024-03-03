package InputData;

public class User {
    public String name;
    public String jop;

    public User(String name, String jop) {
        this.name = name;
        this.jop = jop;
    }
    public static class AvtorisationUser {
        public String email;
        public String password;

        public AvtorisationUser(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
