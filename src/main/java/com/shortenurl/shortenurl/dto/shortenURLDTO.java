package com.shortenurl.shortenurl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class shortenURLDTO {

    private UUID id;
    private long clicks;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String shortURL;
    private String originalURL;
}
