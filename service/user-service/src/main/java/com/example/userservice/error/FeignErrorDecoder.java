package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (methodKey.contains("getOrders")) { //Feign 에 등록된 메소드 이름
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            "User's orders is empty");
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
