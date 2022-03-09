package com.shortenurl.service;


import javax.servlet.http.HttpServletRequest;

public interface ShortenLinkService {
    public String creatShortenLink(String url, HttpServletRequest httpServletRequest);

    String findOriginalUrl(String shortUrl);
}
