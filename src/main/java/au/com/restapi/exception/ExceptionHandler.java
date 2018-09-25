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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final String error = "Could not decode request: JSON parsing failed";
	    ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.BAD_REQUEST, error);
	    logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
	    return buildResponseEntity(exceptionDetails);
	 }
	

	@Override
	public final ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final String error = "Method Not Supported";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.NOT_FOUND, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
	}

	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported( HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final String error = "Content Type should be JSON";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
	}
	
	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   {
		final String error = "Invalid URL";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.NOT_FOUND, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
		final String error = "Something unexpected happened. Please call the administrator.";
		ExceptionDetails exceptionDetails = ExceptionDetails.newExcptionWithStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, error);
		logger.error("Exception occured " + exceptionDetails + "exception details " + ex.getLocalizedMessage());
		return buildResponseEntity(exceptionDetails);
    }
	
	private ResponseEntity<Object> buildResponseEntity(ExceptionDetails exceptionDetails) {
	       return new ResponseEntity<>(exceptionDetails, exceptionDetails.getStatus());
	}
	
}
