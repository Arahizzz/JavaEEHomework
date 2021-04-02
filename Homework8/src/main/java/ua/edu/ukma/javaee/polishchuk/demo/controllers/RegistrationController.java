package ua.edu.ukma.javaee.polishchuk.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.edu.ukma.javaee.polishchuk.demo.models.RegisterForm;
import ua.edu.ukma.javaee.polishchuk.demo.services.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService users;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterForm form){
        users.registerUser(form);
        return "redirect:/";
    }
}
