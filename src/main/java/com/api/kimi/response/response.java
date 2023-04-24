package com.api.kimi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class response<T> {
    private HttpStatus status;
    private String msg;
    private T responseData;

    public void customErrorResponse (HttpStatus status, String msg, T responseData) {
        int statusCode = status.value();
        String statusMsg = status.getReasonPhrase();
        this.msg = msg;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(dateTimeFormatter);
        this.responseData = responseData;
    }
}
