package com.lcwd.electronic.store.exception;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GloblaExceptionHandler {

private Logger logger = LoggerFactory.getLogger(GloblaExceptionHandler.class);

    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFounException ex){


        logger.info("Exception handler invoked");
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
      return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }

    //this method can be used to give message if method argumentValidation exception occurs , example if validation do not meet the requirement this method will handle all the exception message with each of them
    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
       List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
       Map<String,Object> response = new HashMap<>();

       allErrors.forEach(objectError -> {
        String message =   objectError.getDefaultMessage();
        String field =  ((FieldError) objectError).getField();

         response.put(field,message);
       });
 return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
}

//handle bad api request exception
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMessage> handleBadRequestAPi(BadApiRequest ex){
      logger.info("Bad request api handler invoked");
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }

}
