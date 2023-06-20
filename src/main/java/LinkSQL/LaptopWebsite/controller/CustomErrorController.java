package LinkSQL.LaptopWebsite.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @GetMapping
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && statusCode == HttpStatus.NOT_FOUND.value()) {
            return "404";
        }
        return "error";
    }
}
