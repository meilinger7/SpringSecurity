package at.htlimst.springsecurity.controller;

import at.htlimst.springsecurity.dto.UserPojo;
import at.htlimst.springsecurity.dto.UserRegistrationDto;
import at.htlimst.springsecurity.service.SecurityService.UserPrincipal;
import at.htlimst.springsecurity.service.SecurityService.UserPrincipalDetailsService;
import at.htlimst.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    public UserService userService;
    public UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    public UserRegistrationController(UserService userService, UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userService = userService;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }


    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto, BindingResult result){

        UserPojo existingEmail = userPrincipalDetailsService.findUserByEmail(registrationDto.getEmail());
        if (existingEmail != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            System.out.println("Schon vorhanden");
        }

        if (result.hasErrors()) {
            return "redirect:registration";
        }

        userService.save(registrationDto);
        return "redirect:/registration?success";
    }


}
