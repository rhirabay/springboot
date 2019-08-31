package rhirabay.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private HttpSession session;

    @GetMapping
//    @ResponseBody
    public Object root() {
        session.setAttribute("key", "value");
        return "redirect:/session/test";
    }

    @GetMapping("/test")
    @ResponseBody
    public Object test() {
        return session.getAttribute("key");
    }

    @GetMapping("/set")
    @ResponseBody
    public Object set() {
        String value = (String)session.getAttribute("key");
        session.setAttribute("key", value);
        return value;
    }
}
