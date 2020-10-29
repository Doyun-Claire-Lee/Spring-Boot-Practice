package me.doyun.springwebsecurity.form;

import me.doyun.springwebsecurity.account.AccountContext;
import me.doyun.springwebsecurity.account.AccountRepository;
import me.doyun.springwebsecurity.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        if (principal == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello " + principal.getName());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Hello Spring Security");
        return "info";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());

        //setAccount를 통해 TheadLocal에 Account 정보를 넣음.
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();

        return "dashboard";
    }
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin " + principal.getName());
        return "user";
    }

    @GetMapping("async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");      /**/
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                //별도의 Thread에서 실행되는 코드
                SecurityLogger.log("Callable"); /**///위의 logger와 쓰레드는 다르지만 principal은 동일할 것. (WebAsyncManagerIntegrationFilter)
                return "Async Handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }


}
