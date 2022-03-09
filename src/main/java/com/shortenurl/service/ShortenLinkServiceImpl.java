package com.shortenurl.service;

import com.shortenurl.ShortenLinkUtil;
import com.shortenurl.enitity.ShortenUrl;
import com.shortenurl.repository.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class ShortenLinkServiceImpl implements ShortenLinkService {


    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Autowired
    ShortenUrlRepository shortenUrlRepositoryl;


    @Override
    public String creatShortenLink(String url, HttpServletRequest httpServletRequest) {
        String shortUrl =  ShortenLinkUtil.createLinkShortenLink(url);
        ShortenUrl shortenUrl = populateShortenUrl(shortUrl,url);
        shortenUrl =  shortenUrlRepositoryl.save(shortenUrl);
        try{
            String  lhostName =  httpServletRequest.getRequestURL().substring(0,
                    httpServletRequest.getRequestURL().length() - httpServletRequest.getRequestURI().length())
                    + httpServletRequest.getContextPath()+"/tinyUrl/";
            return lhostName+shortUrl;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return shortUrl;
    }

    @Override
    public String findOriginalUrl(String shortUrl) {
        try{
            ShortenUrl shortenUrl =  shortenUrlRepositoryl.findFirstByShortUrl(shortUrl);
            if(shortenUrl != null){
                return shortenUrl.getOriginalUrl();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private ShortenUrl populateShortenUrl(String shortUrl, String url) {
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setCreatedDate(LocalDateTime.now());
        shortenUrl.setOriginalUrl(url);
        shortenUrl.setShortUrl(shortUrl);
        return shortenUrl;
    }
}


