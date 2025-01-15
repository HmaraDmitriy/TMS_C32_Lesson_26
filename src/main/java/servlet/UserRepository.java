package servlet;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "admin");
        users.put("user", "user");
    }

    public static Boolean isValid(String username, String password) {
        return username != null && password != null && users.containsKey(username) && users.get(username).equals(password);
    }

    public static void addUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
        }
    }
}
