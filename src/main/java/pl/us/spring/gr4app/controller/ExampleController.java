package pl.us.spring.gr4app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ExampleController {

    @RequestMapping("/hello")
    public String hello(
            @RequestParam(required = false, defaultValue = "nieznajomy") String name,
            @RequestParam(required = false, defaultValue = "0") int counter,
            @RequestParam(name = "birth-date", required = false, defaultValue = "2000-10-10")LocalDate birth){
        return "Hello " + name + " " + counter + ", your age is " + (LocalDate.now().getYear() - birth.getYear());
    }

    // przykład
    // ?a=3.5&b=3.6&op=add
    // op: add, sub, mul, div
    // odpowiedź:
    // 2.5 + 3.6 = 6.1
    @RequestMapping("/calc")
    public String calculator(){

    }
}
