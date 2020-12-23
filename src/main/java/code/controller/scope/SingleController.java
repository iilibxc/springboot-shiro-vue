package code.controller.scope;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/*
*
* controller默认是单例的
*
* */
@RestController
public class SingleController {

    private int num = 0;

    @RequestMapping("/single1")
    @ResponseBody
    public String single1() {
        return ++num+"";
    }

    @RequestMapping("/single2")
    @ResponseBody
    public String single2() {
        return ++num+"";
    }

}
