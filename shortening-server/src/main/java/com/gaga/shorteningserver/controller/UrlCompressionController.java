package com.gaga.shorteningserver.controller;

import com.gaga.shorteningserver.dto.DefaultResponseDTO;
import com.gaga.shorteningserver.dto.InputUrlDTO;
import com.gaga.shorteningserver.model.ShortUrl;
import com.gaga.shorteningserver.service.UrlCompressionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UrlCompressionController {
    private final UrlCompressionService urlCompressionService;

    //리다이렉트
    @CrossOrigin
    @GetMapping("")
    public ShortUrl compressURL(@RequestParam("shortUrlAddress") String shortUrlAddress) {
        ShortUrl shortUrl = urlCompressionService.findByShortUrl(shortUrlAddress);
        return shortUrl;
    }

    //CORS 오류남
    /*@CrossOrigin
    @GetMapping("{shortUrlAddress}")
    public RedirectView compressURL(@PathVariable("shortUrlAddress") String shortUrlAddress) {
        String shortUrl = urlCompressionService.findByShortUrl(shortUrlAddress);
        return new RedirectView(shortUrl);
    }*/

    @CrossOrigin
    @PostMapping("/url")
    public DefaultResponseDTO compressURL(@Valid @RequestBody InputUrlDTO url) {
        ShortUrl shortUrl = urlCompressionService.compressionUrl(url.getUrl());
        return new DefaultResponseDTO(200, true, "Compress Success!", shortUrl.getShortUrl());
    }
}
