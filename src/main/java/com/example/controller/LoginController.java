package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error
            ,@RequestParam(value = "register",required = false) String register ,@RequestParam(value = "logout",required = false) String logout, Model model){
        String errorMsg = null;
        if(error !=null){
            errorMsg= "User or Password is incorrect !!";
        }
        else if(logout!=null){
            errorMsg="You have been successfully logout !!";
        }
        else if(register != null)
        {
            errorMsg="Your registration successful. Login with registered credentials!! ";
        }

        model.addAttribute("errormsg",errorMsg);
        return "login.html";


    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String Logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
            return "redirect:/login?logout=true";
    }


}
