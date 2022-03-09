package com.shortenurl.enitity;


import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "shortenUrl")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ShortenUrl {
    @Id
    private String id;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createdDate;
    private String createIpAddress;

}
