package com.finantech.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login.htm")
@SessionAttributes("loginId")
public class LoginController {

    protected ModelAndView loginPage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String sloginId = (String) session.getAttribute("loginId");
        ModelAndView model = new ModelAndView("welcome.jsp");
        String rloginId = request.getParameter("loginId");
        if (sloginId == null && rloginId == null) {
            return new ModelAndView("login");
        } else {
            if (sloginId == null) {
                if ("admin".equals(rloginId)) {
                    model = new ModelAndView("index");
                    model.addObject("msg", "Login successful");
                    session.setAttribute("loginId", "admin");
                    return model;
                } else {
                    model = new ModelAndView("login");
                    model.addObject("msg", "Login un-successful.Try Again");
                    return model;

                }
            } else {
                return new ModelAndView("index");
            }
        }
    }

}
