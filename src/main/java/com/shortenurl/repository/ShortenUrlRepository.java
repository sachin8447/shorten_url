package com.shortenurl.repository;

import com.shortenurl.enitity.ShortenUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShortenUrlRepository extends MongoRepository<ShortenUrl ,Long> {
    ShortenUrl findFirstByShortUrl(String shortUrl);
}
