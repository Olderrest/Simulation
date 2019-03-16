package com.stasuma.interceptor;

import com.stasuma.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = {ResourceNotFoundException.class, NoHandlerFoundException.class})
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        ModelAndView mv = new ModelAndView("/404");
        return mv;
    }
}
