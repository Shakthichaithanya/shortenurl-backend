package com.shortenurl.shortenurl.controller;

import com.shortenurl.shortenurl.dto.ShortenURLDTO;
import com.shortenurl.shortenurl.infos.ResponseInfo;
import com.shortenurl.shortenurl.model.Shortenurl;
import com.shortenurl.shortenurl.service.ShortenurlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ShortenurlController {

    @Autowired
    private ShortenurlService shortenurlService;

    @PostMapping("shortenurl")
    public ResponseEntity<ResponseInfo> createShortenURL(@Validated @RequestBody Shortenurl shortUrl) {

        String message = shortenurlService.createShortUrl(shortUrl);
        ResponseInfo response = new ResponseInfo(HttpStatus.CREATED, message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("{shortURL}")
    public RedirectView redirectToOriginalURL(@PathVariable("shortURL") String shortURL) {

        String originalURL = shortenurlService.getOriginalURL(shortURL);
        return new RedirectView(originalURL);

    }

    @GetMapping("shortURLs")
    public ResponseEntity<List<ShortenURLDTO>> getAllUrlsList() {
        return new ResponseEntity<>(shortenurlService.getAllURLsList(), HttpStatus.OK);
    }

}
