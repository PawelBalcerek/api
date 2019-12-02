package AI.dtapijava.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    @RequestMapping("/dt-app")
    public String forwarda() {
        return "forward:/dt-app/index.html";
    }

    @RequestMapping("/dt-app/")
    public String forwardb() {
        return "forward:/dt-app/index.html";
    }

    @RequestMapping("/dt-app/{path:[^\\.]+}/**")
    public String forwardc() {
        return "forward:/dt-app/index.html";
    }

    @RequestMapping("/")
    public String forwardd() {
        return "forward:/dt-app/index.html";
    }
}
