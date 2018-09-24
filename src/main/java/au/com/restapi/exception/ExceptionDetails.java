package au.com.restapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionDetails {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String error;

	private ExceptionDetails() {
		timestamp = LocalDateTime.now();
	}

	static ExceptionDetails newExcptionWithStatus(final HttpStatus status) {
		final ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.status = status;
		return exceptionDetails;
	}

	static ExceptionDetails newExcptionWithStatusAndException(final HttpStatus status, final Throwable ex) {
		final ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.status = status;
		exceptionDetails.error = "Unexpected error";
		return exceptionDetails;
	}

	static ExceptionDetails newExcptionWithStatusExceptionAndMessage(final HttpStatus status, String message, Throwable ex) {
		final ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.status = status;
		exceptionDetails.error = ex.getLocalizedMessage();
		return exceptionDetails;
	}
	
	static ExceptionDetails newExcptionWithStatusAndMessage(final HttpStatus status, String message) {
		final ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.status = status;
		exceptionDetails.error = message;
		return exceptionDetails;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "ExceptionDetails [status=" + status + ", timestamp=" + timestamp + ", error=" + error + "]";
	}

	
}
