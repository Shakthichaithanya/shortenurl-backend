package com.shortenurl.shortenurl.service;

import com.shortenurl.shortenurl.dto.ShortenURLDTO;
import com.shortenurl.shortenurl.exception.DuplicateShortURLException;
import com.shortenurl.shortenurl.exception.ShortURLNotFoundException;
import com.shortenurl.shortenurl.model.Shortenurl;
import com.shortenurl.shortenurl.repository.ShortenurlRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShortenurlService {
    private final ShortenurlRepository shortenurlRepository;
    private final ModelMapper modelMapper;

    private SecureRandom random = new SecureRandom();
    @Autowired
    public ShortenurlService(ShortenurlRepository shortenurlRepository, ModelMapper modelMapper){
        this.shortenurlRepository =shortenurlRepository;
        this.modelMapper = modelMapper;
    }

    public String generateRandomString(){
        String alphaNumeric  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        StringBuilder result = new StringBuilder();
        for(int i=0; i<10;i++){
            int index = this.random.nextInt(alphaNumeric.length());
            char ch = alphaNumeric.charAt(index);
            result.append(ch);
        }
        return result.toString();
    }

    public String createShortUrl(Shortenurl shortUrl) {

        shortUrl.setCreatedDateTime(LocalDateTime.now());
        shortUrl.setUpdatedDateTime(LocalDateTime.now());
        try {
            if(shortUrl.getShortURL() == null || shortUrl.getShortURL().isEmpty() || shortUrl.getShortURL().isBlank()){
                shortUrl.setShortURL(generateRandomString());
            }
            shortenurlRepository.save(shortUrl);
        } catch (Exception exception) {
            throw new DuplicateShortURLException("Passed ShortUrl is already present please send unique value");
        }
        return "short url created";
    }

    public void updateShortUrlClicksAndDate(Shortenurl shortUrl) {
        shortUrl.setClicks(shortUrl.getClicks() + 1);
        shortUrl.setUpdatedDateTime(LocalDateTime.now());
        shortenurlRepository.save(shortUrl);
    }

    public String getOriginalURL(String shortURL) {
        System.out.println("inside method");
        Optional<Shortenurl> shortUrl = shortenurlRepository.findByShortURL(shortURL);
        if (shortUrl.isPresent()) {
            updateShortUrlClicksAndDate(shortUrl.get());
            return shortUrl.get().getOriginalURL();
        }
        throw new ShortURLNotFoundException("Passed ShortURL is not present or invalid. Please pass valid shortURL");

    }

    @Cacheable(value = "urls")
    public List<ShortenURLDTO> getAllURLsList() {
        System.out.println("list method");
        List<Shortenurl> shortUrls = shortenurlRepository.findAll();
        return shortUrls.stream().map(url -> modelMapper.map(url, ShortenURLDTO.class)).toList();
    }
    @Scheduled(cron = "0 0 9-18 * * ?")
    @CachePut(value = "urls")
    public List<ShortenURLDTO> getAllURLsListUpdatedCache() {
        System.out.println("list method");
        List<Shortenurl> shortUrls = shortenurlRepository.findAll();
        return shortUrls.stream().map(url -> modelMapper.map(url, ShortenURLDTO.class)).toList();
    }

    @CacheEvict(value = "urls",allEntries = true)
    public void deleteCache() {
    }
}
