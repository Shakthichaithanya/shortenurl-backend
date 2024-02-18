package com.shortenurl.shortenurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shortenurl {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private long clicks = 0;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String shortURL;
    private String originalURL;
}
