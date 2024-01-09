package hello.hellospring.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//컨트롤러에서 리턴값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리
//viewName매핑이후, resources:templates/` + {ViewName} + `.html`접근
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody//그냥 적은그대로 내려간다. 적은 그대로란 즉, 내가 만든 데이터 그대로이다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//문자가 아닌 첫 객체
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}