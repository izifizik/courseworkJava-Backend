package com.api.courswork.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data // lombok feature which write inside @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@Document(collection = "users")
public class User {

    @Id // that mean it is id
    private String id;
    private String username;
    private String password;
    private List<String> myEvents;

    public User(String username, String password, List<String> myEvents) {
        this.username = username;
        this.password = password;
        this.myEvents = myEvents;
    }
}
