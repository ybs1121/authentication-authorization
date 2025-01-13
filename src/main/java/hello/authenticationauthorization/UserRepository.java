package hello.authenticationauthorization;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {
    private HashMap<Long, User> data = new HashMap<>();

    public void save(User user) {
        data.put(user.getId(), user);
    }
}
