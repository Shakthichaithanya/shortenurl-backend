package com.shortenurl.shortenurl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortenURLDTO implements Serializable {

    private UUID id;
    private long clicks;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String shortURL;
    private String originalURL;
}
