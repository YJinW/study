package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")   // MVC와 템플릿 엔진 ⭐
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);   //http://localhost:8080/hello-mvc?name=%ED%99%80%EB%9E%80%EB%93%9C
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody               //API (문자 반환) 거의 사용 X
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;       // body에 그대로 넣는다.
    }

    @GetMapping("/hello-api")
    @ResponseBody               //API (객체 반환) ⭐⭐⭐
    public Hello helloApi(@RequestParam("name") String name, Model model){
        Hello hello=new Hello();
        hello.setName(name);
        return hello;           // HttpMessageConverter -> 'Json' or 'String'
    }

    static class Hello{
        private String name;
        // alt + ins
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
