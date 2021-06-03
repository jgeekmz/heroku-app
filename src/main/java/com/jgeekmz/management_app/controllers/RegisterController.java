package com.jgeekmz.management_app.controllers;

import com.jgeekmz.management_app.models.User;
import com.jgeekmz.management_app.repositories.RoleRepository;
import com.jgeekmz.management_app.services.NotificationService;
import com.jgeekmz.management_app.services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {

    private final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private NotificationService notificationService;
    private RoleRepository roleRepository;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, NotificationService notificationService, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.notificationService = notificationService;
        this.roleRepository = roleRepository;
    }

    /** Return registration html template */
    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registerPage");
        return modelAndView;
    }

    /** Processing the registration Form from user view */
    @RequestMapping(value = "/registerPage", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {

        String email = user.getEmail();
        String name = user.getUsername();
        System.out.println(email);
        System.out.println(name);

        // Lookup user in database by e-mail & username
        User userExists = userService.findByEmail(email);
        Optional<User> userName = Optional.ofNullable(userService.findByUsername(name));

        System.out.println("Check by email for existence >> " + userExists);
        System.out.println("Check by username >> " + userName);

        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredEmail", "Oops!  There is already a user registered with the email provided!");
            modelAndView.setViewName("registerPage");

            return modelAndView;
        }

        if (userName.isPresent()) {
            modelAndView.addObject("alreadyRegisteredUsername", "Oops!  The username is already taken!");
            modelAndView.setViewName("registerPage");
            return modelAndView;
        }

        // Error before registration/ validation of the form
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registerPage");

        } else { // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email
            //user.setEnabled(false);

            // Generate random 36-character string token for confirmation link
            user.setConfirmationToken(UUID.randomUUID().toString());

            Date todayDate = new Date();
            user.setRegDate(todayDate);

            userService.saveUser(user);

            // URL for registration
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";

            // email notification
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n\n\n" + appUrl + "confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");

            notificationService.sendEmail(registrationEmail);

            // redirect to register view with confirmation message
            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
            modelAndView.setViewName("registerPage");
        }

        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }


    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
        modelAndView.setViewName("confirm");

        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure((String) requestParams.get("password"));

        if (strength.getScore() < 3) {
            bindingResult.reject("password");

            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        // Find the user associated with the reset token
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));

        // Set new password
        user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));

        // Set user to enabled
        //user.setEnabled(true);

        //User needs to get USER role when registering on the website by himself
        //user.setUserRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        // Save user
        userService.saveUser(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }
}