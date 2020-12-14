package com.gaga.shorteningserver.repository;

import com.gaga.shorteningserver.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByOriginUrl(String originUrl);

    ShortUrl findByShortUrl(String shortUrl);
}
