package com.jgeekmz.management_app.controllers;

import com.jgeekmz.management_app.models.Employee;
import com.jgeekmz.management_app.services.EmployeeService;
import com.jgeekmz.management_app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileController {

    Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired private EmployeeService employeeService;
    @Autowired private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal, Employee employee) {

        String un = principal.getName();
        log.debug("UserPrinciple name : " + un);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();

        boolean admin = true;
        Employee user = employeeService.findByUsername(userName);
        Boolean checkAdminIfTrue =  userService.checkAdmin(userName,admin);

        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        String email = user.getEmail();
        String m = user.getDateOfBirth();
        String k = user.getMobile();
        String t = user.getAddress();
        String g = user.getCountry().getCapital();
        String p = user.getHireDate();

        log.debug("User Details >>> " + m + " " + k + " " + g + " " + email + " " + p);

        model.addAttribute("userName", userName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("m", m);
        model.addAttribute("k", k);
        model.addAttribute("t", t);
        model.addAttribute("g", g);
        model.addAttribute("p", p);
        if(checkAdminIfTrue) {
            model.addAttribute("admin", "Administrator");
        } else {
            model.addAttribute("admin", "Employee/User");
        }

        return "profile";
    }

    @RequestMapping(value = "/profile/editDetails", method = RequestMethod.POST)
    public String profileEdit(Model model, Employee employee) {
        log.debug("PROFILE  EDIT Page 2021 :) :) :) :)");
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return "redirect:/profile";
    }
}