package LaptopWebsite.Nhom14.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home(){return "index";}

    // @Autowired
    // @RequestMapping("/login")
    // public String login(){return ""}
}
