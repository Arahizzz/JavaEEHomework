package ua.edu.ukma.javaee.polishchuk.homework9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.edu.ukma.javaee.polishchuk.homework9.models.RegisterForm;
import ua.edu.ukma.javaee.polishchuk.homework9.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService users;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model, @ModelAttribute("registerForm") RegisterForm form){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        users.registerUser(form);
        return "redirect:/";
    }
}
