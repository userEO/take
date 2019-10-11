package take.controller;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/8/30 17:21
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    //接口改为80端口,直接访问localhost绑定，然后通过云服务器地址直接访问，再将域名绑定云服务器地址,通过域名直接访问
    @GetMapping("/")
    public Object index(){
        return "login";
    }
    @GetMapping("/login")
    public Object login(){
        return "login";
    }
    @GetMapping("/contact")
    public Object contact(){
        return "contact";
    }
    @GetMapping("/single")
    public Object single(){
        return "single";
    }
    @GetMapping("/index")
    public Object forIndex(){
        return "index";
    }
    @GetMapping("/player")
    public Object player(){
        return "player";
    }
    @GetMapping("/louver")
    public Object louver(){
        return "louver";
    }
    @GetMapping("/photo")
    public Object photo(){
        return "photo";
    }
    @GetMapping("/music")
    public Object music(){
        return "music";
    }
}
