package com.api.courswork.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // lombok feature which write inside @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@Document(collection = "users")
public class User {

    @Id // that mean it is id
    private String id;
    private String username;
    private String password;
    private Boolean admin;

    public User(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
}
