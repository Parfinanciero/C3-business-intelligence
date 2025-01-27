package com.BI.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerExceptions {


    //invalidRequestException
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidRequestExceptions(InvalidRequestException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "REQUEST_ERROR");


        return  new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    //lanzadores
    //Usernotfounexception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundUserException( UserNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "USER_NOT_FOUND");
        return  new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CashApiExceptions.class)
    public ResponseEntity<ErrorResponse> handleExpensesApiException(CashApiExceptions ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
               HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ("ERROR_API_EXPENSES")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NoCashFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpensesApiException(NoCashFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ("ERROR_API_EXPENSES")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
