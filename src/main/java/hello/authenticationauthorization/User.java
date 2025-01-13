package hello.authenticationauthorization;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String username;
    private String password;
    private String role; // ROLE_USER, ROLE_ADMIN 등

}
