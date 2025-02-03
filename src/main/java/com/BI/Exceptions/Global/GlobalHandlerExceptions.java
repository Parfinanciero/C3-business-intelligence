package com.BI.Exceptions.Global;


import com.BI.Exceptions.Custom.*;
import com.BI.Exceptions.ResponseDto.ErrorResponse;
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
                ("ERROR_API")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }



    @ExceptionHandler(NoCashFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpensesApiException(NoCashFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ("CASHFLOW_NOT_FOUND")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomArithmeticExceptions.class)
    public ResponseEntity<ErrorResponse> handlerArithmeticException(CustomArithmeticExceptions exceptions){
        ErrorResponse errorResponse = new ErrorResponse(
                exceptions.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ("ARITHMETIC_EXCEPTION")


                );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoIncomeException.class)
    public ResponseEntity<ErrorResponse> handlerIncomeException(NoIncomeException exceptions){
        ErrorResponse errorResponse = new ErrorResponse(
                exceptions.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ("INCOME_INVALID")


        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
