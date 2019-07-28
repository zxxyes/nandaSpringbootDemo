//package com.example.demo.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.persistence.EntityNotFoundException;
//
///**
// * spring 异常处理
// * */
//@ControllerAdvice
//public class NormalAdvice {
//
//    /**
//     * 404 not found
//     * */
//    @ResponseBody
//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private String entityNotFoundHandler(EntityNotFoundException e){
//        return e.getMessage();
//    }
//
//    /**
//     * 500 Internal Error
//     * */
//    @ResponseBody
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    private String internalServerErrorHandler(RuntimeException e){
//        return e.getMessage();
//    }
//
//}
