package com.sample.enterprise.devops.daas.controller;

import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.aware.HeaderInfoAware;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.util.ExceptionReviewerUtil;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@ControllerAdvice(basePackages = "com.sample.enterprise.devops.daas.controller")
@SuppressWarnings("unchecked")
public class RequestValidExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getDaasErrorResponse("REQUEST_MESSAGE_VALIDATION_ERROR", ex, headers, status, request);
    }

    private ResponseEntity getDaasErrorResponse(String reason, MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        GeneralResponseMessage responseMsg = new GeneralResponseMessage();
        if (ex.getBindingResult() instanceof BeanPropertyBindingResult) {
            BeanPropertyBindingResult result = (BeanPropertyBindingResult) ex.getBindingResult();
            if (result.getTarget() instanceof HeaderInfoAware) {
                HeaderInfoAware messageBase = (HeaderInfoAware) result.getTarget();
                responseMsg.setHeader(messageBase.getHeader());
                responseMsg.getErrorDetail().setErrorMessage("fail validation fields: " + ex.getBindingResult().getAllErrors().size());
            }
        }
        responseMsg.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
        responseMsg.getErrorDetail().setErrorCode("REQUEST_MESSAGE_VALIDATION_ERROR");
        for (ObjectError theOne : ex.getBindingResult().getAllErrors()) {
            if (theOne instanceof FieldError) {
                FieldError temp = (FieldError) theOne;
                responseMsg.getErrorDetail().getUserResponse().add(temp.getField() + ":'" + temp.getRejectedValue() + "' - " + theOne.getDefaultMessage());
            } else {
                responseMsg.getErrorDetail().getUserResponse().add(theOne.getDefaultMessage());
            }
        }
        return new ResponseEntity(responseMsg, HttpStatus.BAD_REQUEST);

    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode.value()) {
            request.setAttribute("jakarta.servlet.error.exception", ex, 0);
        }

        GeneralResponseMessage responseMsg = new GeneralResponseMessage();
        responseMsg.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
        responseMsg.getErrorDetail().setErrorCode("INTERNAL_SERVER_MISMATCH");
        ExceptionReviewerUtil reviewer = new ExceptionReviewerUtil();
        responseMsg.getErrorDetail().getAdminResponse().addAll(reviewer.getProjectCauseExceptionTrace(ex, 3));

        return new ResponseEntity(responseMsg, statusCode);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleGeneralException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        DeploymentMessageBase base = null;
        try {
            if (request instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper temp = (ContentCachingRequestWrapper) request;
                Gson gson = new Gson();
                String bodyStr = new String(temp.getContentAsByteArray(), StandardCharsets.UTF_8);
                base = gson.fromJson(bodyStr, DeploymentMessageBase.class);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        GeneralResponseMessage responseMsg = new GeneralResponseMessage();
        if (base != null) {
            responseMsg.setHeader(base.getHeader());
        }
        responseMsg.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
        responseMsg.getErrorDetail().setErrorCode("INTERNAL_SERVER_ERROR");
        ExceptionReviewerUtil reviewer = new ExceptionReviewerUtil();
        responseMsg.getErrorDetail().getAdminResponse().addAll(reviewer.getProjectCauseExceptionTrace(ex, 3));
        return new ResponseEntity(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
