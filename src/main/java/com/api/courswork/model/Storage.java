package com.api.courswork.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // lombok feature which write inside @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@Document(collection = "storage")
public class Storage {
    @Id // that mean it is id
    private String id;
    private String title;
    private String description;
    private Integer count;

    public Storage(String title, String description, Integer count) {
        this.title = title;
        this.description = description;
        this.count = count;
    }
}
