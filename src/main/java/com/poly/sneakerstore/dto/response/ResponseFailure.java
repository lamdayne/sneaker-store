package com.poly.sneakerstore.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ResponseFailure extends ResponseSuccess {

    public ResponseFailure(HttpStatusCode status, String message, Object data) {
        super(status, message, data);
    }
}
