package com.code.secretary.exceptions;

 import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.code.secretary.exceptions.custom.BusinessException;
import com.code.secretary.exceptions.custom.DataNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /********************* Custom Exceptions *********************/

    /**
     * Handles BusinessException. Created when violating business rules
     *
     * @param ex the BusinessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessError(BusinessException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, request.getDescription(false), ex.getMessage(), ex.getParams());
        return buildResponseEntity(errorResponse);
    }

    /**
     * Handles DataNotFoundException. Thrown when trying to retrieve resource
     * That's not exist in the database
     *
     * @param ex the DataNotFoundException
     * @return
     */
    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(DataNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, request.getDescription(false), ex.getMessage(), ex.getParams());
        return buildResponseEntity(errorResponse);
    }

    /********************* Common Exceptions *********************/


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
     * invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ErrorResponse object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, request.getDescription(false), builder.substring(0, builder.length() - 2)));
    }

    /**
     * @param ex      HttpRequestMethodNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ErrorResponse object
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, request.getDescription(false), builder.toString());
        return buildResponseEntity(errorResponse);
    }

    /**
     * Handle javax.persistence.EntityNotFoundException
     */
 
    /**
     * @param ex
     * @param request
     * @return
     */
  

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB
     * causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return
     */
     

    /**
     * Handle DataAccessResourceFailureException, can not connected to database
     *
     * @param ex
     * @param request
     * @return
     */
 

    /**
     * Handle DataAccessException, inspects the cause for different DB
     *
     * @param ex
     * @param request
     * @return
     */
 

    /**
     * handle all types of exceptions
     *
     * @param ex      Exception
     * @param request
     * @return
     */
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request, HandlerMethod handlerMethod) {
        logger.error(ex.getLocalizedMessage(), ex);

        //String msg = "error_" + handlerMethod.getMethod().getName(); //TODO: remove later
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), null);
        String debugMsg = ex.getClass().getSimpleName() + ": " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        errorResponse.setDebugMessage(debugMsg);
        return buildResponseEntity(errorResponse);
    }

    /********************* helper methods *********************/

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
