package code.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class ScopeController {

    private int num = 0;

    @RequestMapping("/scope1")
    @ResponseBody
    public String scope1() {
        return ++num + "";
    }

    @RequestMapping("/scope2")
    @ResponseBody
    public String scope2() {
        return ++num + "";
    }

}
