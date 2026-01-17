package org.stylianopoulos.logistics.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

// * INFO: Senior Implementation: Handles both UI and REST endpoints
@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    // ? MUST include @ResponseBody if return a List from a @Controller
    @GetMapping("/api/v1/health")
    @ResponseBody
    public List<String> getHealth() {
        return List.of("Kernel", "Network", "Storage")
                .stream()
                .map(String::toUpperCase)
                .map(s -> s + ": ACTIVE")
                .toList();
    }
}