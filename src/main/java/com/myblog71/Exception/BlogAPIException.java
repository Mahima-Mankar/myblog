package com.myblog71.Exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends Throwable {
    public BlogAPIException(HttpStatus httpStatus, String invalidJwtSignature) {
    }
}
