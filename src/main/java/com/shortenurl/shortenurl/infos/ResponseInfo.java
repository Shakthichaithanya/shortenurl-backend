package com.shortenurl.shortenurl.infos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo {

    private HttpStatus status;
    private String message;
}
