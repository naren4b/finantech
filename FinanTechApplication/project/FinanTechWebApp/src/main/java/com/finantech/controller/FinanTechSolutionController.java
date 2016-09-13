package com.finantech.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finantech.exception.FinanTechGenericException;

@Controller
public class FinanTechSolutionController {

    @RequestMapping(value = "/{type:.+}", method = RequestMethod.GET)
    public ModelAndView getPages(@PathVariable("type") String type)
            throws Exception {

        if ("error".equals(type)) {
            // go handleCustomException
            throw new FinanTechGenericException("E888",
                    "This is custom message");
        } else if ("io-error".equals(type)) {
            // go handleAllException
            throw new IOException();
        } else {
            return new ModelAndView("index").addObject("msg", type);
        }

    }

}
