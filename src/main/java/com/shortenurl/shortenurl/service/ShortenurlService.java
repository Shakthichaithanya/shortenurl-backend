package com.shortenurl.shortenurl.service;

import com.shortenurl.shortenurl.model.Shortenurl;
import com.shortenurl.shortenurl.repository.ShortenurlRepository;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShortenurlService {
    @Autowired
    private ShortenurlRepository shortenurlRepository;

    public String createShortUrl(Shortenurl shortUrl) {

        shortUrl.setCreatedDateTime(LocalDateTime.now());
        shortUrl.setUpdatedDateTime(LocalDateTime.now());

        shortenurlRepository.save(shortUrl);
        return "short url created";
    }
}
