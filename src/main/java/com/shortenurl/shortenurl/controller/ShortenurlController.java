package com.shortenurl.shortenurl.controller;

import com.shortenurl.shortenurl.infos.ResponseInfo;
import com.shortenurl.shortenurl.model.Shortenurl;
import com.shortenurl.shortenurl.service.ShortenurlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ShortenurlController {

    @Autowired
    private ShortenurlService shortenurlService;

    @PostMapping("shortenurl")
    public ResponseEntity<ResponseInfo> createShortenURL(@RequestBody Shortenurl shortUrl) {

        String message = shortenurlService.createShortUrl(shortUrl);

        ResponseInfo response = new ResponseInfo(HttpStatus.CREATED, message);

        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

}
