package com.gaga.shorteningserver.service;

import com.gaga.shorteningserver.algorithm.Base62;
import com.gaga.shorteningserver.exception.InvalidProtocolException;
import com.gaga.shorteningserver.exception.SunCertPathBuilderException;
import com.gaga.shorteningserver.model.ShortUrl;
import com.gaga.shorteningserver.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Service
public class UrlCompressionService {

    public static String local = "http://localhost:8001/";

    @Autowired
    private final ShortUrlRepository shortUrlRepository;

    public ShortUrl findByShortUrl(String shortUrl) {
        return shortUrlRepository.findByShortUrl(local + shortUrl);

        /*String originUrl = shortUrl1.getOriginUrl();
        return originUrl;*/
        //return shortUrlRepository.findById(id);
    }

    public ShortUrl findByOriginUrl(String originUrl) {
        return shortUrlRepository.findByOriginUrl(originUrl);
    }

    // ##에러 처리 방법 -> ##정리 후 바꾸기
    public ShortUrl compressionUrl(String originUrl) {
        if (!isValidUrl(originUrl)) {
            throw new InvalidProtocolException();
        }

        ShortUrl shortUrl = findByOriginUrl(originUrl);
        if(shortUrl == null) {
            //DB에 똑같은 URL이 들어온 적이 있는지 판단.
            log.info("[compressionUrl find by url] : " + shortUrlRepository.findByShortUrl(originUrl));
            String resultUrl = local + compressionUrl();
            shortUrl = new ShortUrl(resultUrl, originUrl);
            log.info("[compressionUrl] shortUrl count : " + shortUrlRepository.count());
            shortUrl = shortUrlRepository.save(shortUrl);
        }

        return shortUrl;
    }

    //test 해보기 -> 추후에 static 지우기
    public static boolean isValidUrl(String urlStr) {
        if (!urlStr.startsWith("http") && !urlStr.startsWith("https")) {
            return false;
        }

        //##Issue
        //https://naver.com 을 돌렸을 때, 처음은 true로 반환이 된다.
        //똑같은 url로 요청을 보냈을 경우 false가 나왔다. 왜일까?
        try {
            log.info("[TEST] urlStr: " + urlStr);
            URL url = new URL(urlStr);
            url.openStream().close();
            return true;
        } catch (IOException | SunCertPathBuilderException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public String compressionUrl() {
        Base62 base62 = new Base62();

        log.info("[compressionString()] shortUrl count : " + shortUrlRepository.count());
        long num = shortUrlRepository.count() + 1;
        return base62.convert62Number(num);
    }

    public static void main(String[] args) {
        System.out.println(isValidUrl("https://www.naver.com/")); //예상결과 -> true
        System.out.println(isValidUrl("https://naver.com/")); //예상결과 -> true
        System.out.println(isValidUrl("https://youtube.com/")); //예상결과 -> true
        System.out.println(isValidUrl("https://qastack.kr/programming/2230676/how-to-check-for-a-valid-url-in-java")); // true
        System.out.println(isValidUrl("http://sdkfjlsjflsjlfkfasjdljfs/")); // false
        System.out.println(isValidUrl("http://eclass.seoultech.ac.kr/ilos/main/main_form.acl")); // true
    }
}
