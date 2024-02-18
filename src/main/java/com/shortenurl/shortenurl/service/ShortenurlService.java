package com.shortenurl.shortenurl.service;

import com.shortenurl.shortenurl.dto.ShortenURLDTO;
import com.shortenurl.shortenurl.exception.DuplicateShortURLException;
import com.shortenurl.shortenurl.exception.ShortURLNotFoundException;
import com.shortenurl.shortenurl.model.Shortenurl;
import com.shortenurl.shortenurl.repository.ShortenurlRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShortenurlService {
    @Autowired
    private ShortenurlRepository shortenurlRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String createShortUrl(Shortenurl shortUrl) {

        shortUrl.setCreatedDateTime(LocalDateTime.now());
        shortUrl.setUpdatedDateTime(LocalDateTime.now());
        try {
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

        Optional<Shortenurl> shortUrl = shortenurlRepository.findByShortURL(shortURL);
        if (shortUrl.isPresent()) {
            updateShortUrlClicksAndDate(shortUrl.get());
            return shortUrl.get().getOriginalURL();
        }
        throw new ShortURLNotFoundException("Passed ShortURL is not present or invalid. Please pass valid shortURL");

    }

    public List<ShortenURLDTO> getAllURLsList() {
        List<Shortenurl> shortUrls = shortenurlRepository.findAll();
        return shortUrls.stream().map(url -> modelMapper.map(url, ShortenURLDTO.class)).collect(Collectors.toList());
    }
}
