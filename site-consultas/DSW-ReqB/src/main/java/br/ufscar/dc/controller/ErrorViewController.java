package br.ufscar.dc.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ErrorViewController implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map){

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", status.value());
        switch (status.value()){
            case 403:
                model.addObject("error", "403.error");
                model.addObject("message", "403.message");
                break;
            case 404:
                model.addObject("error", "404.error");
                model.addObject("message", "404.message");
            default:
                model.addObject("error", "default.error");
                model.addObject("message", "default.message");
        }

        return model;
    }
}
