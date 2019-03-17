package com.mcy.website.controller.advice;

import com.mcy.website.entity.ResultDataEnum;
import com.mcy.website.utils.ResultData;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice("com.mcy.website.controller")
@ResponseBody
public class ExceptionAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)

    public ResultData ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            builder.append(cvl.getMessage());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        ResultData resultData = new ResultData(ResultDataEnum.FAILD_1001);
        resultData.setMsg(builder.toString());
        return resultData;
    }

    @ExceptionHandler(value = BindException.class)
    public ResultData BindException(BindException bx) {
        StringBuilder builder = new StringBuilder();
        List<FieldError> fieldErrors = bx.getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            builder.append(fieldError.getDefaultMessage());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        ResultData resultData = new ResultData(ResultDataEnum.FAILD_1001);
        resultData.setMsg(builder.toString());
        return resultData;
    }
}
