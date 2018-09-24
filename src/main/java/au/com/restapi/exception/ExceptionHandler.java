package au.com.restapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, WebRequest request) {
		final String error = "Could not decode request: JSON parsing failed";
	    ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.BAD_REQUEST, error);
	    logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
	    return buildResponseEntity(exceptionDetails);
	 }
	
	public final ResponseEntity<Object> handleInvalidDefinitionException(final InvalidDefinitionException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = "Unable to construct the request object";
	    ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.BAD_REQUEST, error);
	    logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
	    return buildResponseEntity(exceptionDetails);
	 }

	public final ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = "Method Not Supported";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.NOT_FOUND, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
	}

	public final ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = "Content Type should be JSON";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ExceptionDetails exceptionDetails) {
	       return new ResponseEntity<>(exceptionDetails, exceptionDetails.getStatus());
	}
}
