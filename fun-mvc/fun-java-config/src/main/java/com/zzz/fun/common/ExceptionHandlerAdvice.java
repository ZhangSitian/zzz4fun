package com.zzz.fun.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    /**
     * 将键值对添加到全局，有注解@RequestMapping会添加
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

}
