package com.dyq.demo.util;

import org.elasticsearch.search.SearchParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {    
@ExceptionHandler(value = SearchParseException.class)
public ResponseEntity searchParseException(SearchParseException e){
   // String content = e.getCause().getMessage();
    return new ResponseEntity("elastic没有任何数据", HttpStatus.OK);
    }
}