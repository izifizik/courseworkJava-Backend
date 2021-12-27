package com.api.courswork.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // lombok feature which write inside @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@Document(collection = "shop")
public class Shop {
    @Id // that mean it is id
    private String id;
    private String title;
    private String description;
    private Integer count;
    private String price;

    public Shop(String title, String description, Integer count, String price) {
        this.title = title;
        this.description = description;
        this.count = count;
        this.price = price;
    }
}
