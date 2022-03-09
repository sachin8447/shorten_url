package com.shortenurl.controller;


import com.shortenurl.service.ShortenLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tinyUrl")
public class UrlController {

    @Autowired
    ShortenLinkService shortenLinkService;

    @PutMapping("/createTinyUrl")
    public ResponseEntity<String> generateShortenLink(@RequestBody String url, HttpServletRequest httpServletRequest){
      return  new ResponseEntity(shortenLinkService.creatShortenLink(url,httpServletRequest), HttpStatus.OK);
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalLink(@PathVariable String shortUrl){
        String originalUrl = shortenLinkService.findOriginalUrl(shortUrl);
        if(originalUrl !=null && !originalUrl.isBlank()){
            return new ResponseEntity(originalUrl, HttpStatus.OK);
        }else{
            return new ResponseEntity(originalUrl, HttpStatus.NOT_FOUND);
        }
    }

}
