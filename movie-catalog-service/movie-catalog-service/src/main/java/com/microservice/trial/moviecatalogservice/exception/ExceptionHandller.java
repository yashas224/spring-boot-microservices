package com.microservice.trial.moviecatalogservice.exception;

import java.net.SocketTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.microservice.trial.moviecatalogservice.entity.ErrorResponse;

/*
 * When Hystrics fallback Method is not used, the exception Handler catches the timeout Exception
 */
@ControllerAdvice
public class ExceptionHandller {

	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<ErrorResponse> handleRequestTimeOut() {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse("Timeout problem occured", HttpStatus.REQUEST_TIMEOUT.toString(), 999999),
				HttpStatus.REQUEST_TIMEOUT);
	}
}
