package com.shortenurl.shortenurl.infos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    private HttpStatus httpStatus;
    private LocalDate date;
    private String message;
    private String path;
}
