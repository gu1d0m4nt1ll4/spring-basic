/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.ws.handler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;
import spring.basic.model.exception.ModelNotFoundException;
import spring.basic.model.exception.ParamNotValidException;

/**
 *
 * Clase basada en
 * {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler @ResponseEntityExceptionHandler}
 *
 * @author guido.mantilla
 */
@ControllerAdvice
public class RestExceptionHandler {

    /*
     * Handle the 400's *
     */
    // --- 
    @ExceptionHandler({
        MissingServletRequestParameterException.class,
        ServletRequestBindingException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        MissingServletRequestPartException.class,
        BindException.class,
        // Excepciones Personalizadas
        ParamNotValidException.class
    })
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest webRequest) {

        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler({
        NoHandlerFoundException.class,
        // Excepciones Personalizadas
        ModelNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest webRequest) {

        return handleExceptionInternal(ex, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({
        HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<Object> handleMethodNotAllowed(Exception ex, WebRequest webRequest) {

        HttpHeaders headers = new HttpHeaders();

        Set<HttpMethod> supportedMethods = ((HttpRequestMethodNotSupportedException) ex).getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        return handleExceptionInternal(ex, HttpStatus.METHOD_NOT_ALLOWED, webRequest);
    }

    @ExceptionHandler({
        HttpMediaTypeNotAcceptableException.class
    })
    public ResponseEntity<Object> handleNotAcceptable(Exception ex, WebRequest webRequest) {

        return handleExceptionInternal(ex, HttpStatus.NOT_ACCEPTABLE, webRequest);
    }

    @ExceptionHandler({
        HttpMediaTypeNotSupportedException.class
    })
    public ResponseEntity<Object> handleUnsupportedMediaType(Exception ex, WebRequest webRequest) {

        HttpHeaders headers = new HttpHeaders();

        List<MediaType> mediaTypes = ((HttpMediaTypeNotSupportedException) ex).getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return handleExceptionInternal(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE, webRequest);
    }

    /*
     * Handle the 500's *
     */
    // --- 
    @ExceptionHandler({
        MissingPathVariableException.class,
        ConversionNotSupportedException.class,
        HttpMessageNotWritableException.class
    })
    public ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest webRequest) {

        return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler({
        AsyncRequestTimeoutException.class
    })
    public ResponseEntity<Object> handleServiceUnavailable(Exception ex, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletRequest request = servletWebRequest.getRequest();
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                return null;
            }
        }

        return handleExceptionInternal(ex, HttpStatus.SERVICE_UNAVAILABLE, webRequest);
    }

    /*
     * Internal Handling Error *
     */
    // --- 
    private ResponseEntity<Object> handleExceptionInternal(
            Exception ex, HttpStatus status, WebRequest webRequest) {

        return handleExceptionInternal(ex, new HttpHeaders(), status, webRequest);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        /*
        String[] x = request.getAttributeNames(WebRequest.SCOPE_REQUEST);
        List<Object> list = new ArrayList<>(0);
        for (String attr : x) {
            list.add(request.getAttribute(attr, WebRequest.SCOPE_REQUEST));
        }
         */
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("path", "" + webRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, WebRequest.SCOPE_REQUEST));
        responseBody.put("http_status", "" + status.value() + " " + status.getReasonPhrase());
        responseBody.put("description", ex.getMessage());
        responseBody.put("timestamp", "" + Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(responseBody, headers, status);
    }
}
