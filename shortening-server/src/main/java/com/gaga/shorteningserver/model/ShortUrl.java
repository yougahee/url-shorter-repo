package com.gaga.shorteningserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "short_urls")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_url_id")
    private Long shortUrlId;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(length = 4000, name = "origin_url")
    private String originUrl;

    public ShortUrl(String shortUrl, String originUrl) {
        this.shortUrl = shortUrl;
        this.originUrl = originUrl;
    }
}
