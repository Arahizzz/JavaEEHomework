package ua.edu.ukma.javaee.polishchuk.homework3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }
}
