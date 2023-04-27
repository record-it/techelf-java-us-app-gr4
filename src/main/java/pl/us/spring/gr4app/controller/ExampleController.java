package pl.us.spring.gr4app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

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
    public String calculator(
            @RequestParam Double pa,
            @RequestParam Double pb,
            @RequestParam String op
    ){
        Map<String, BiFunction<Double, Double, Double>> operators = new HashMap<>();
        operators.put("add", (a, b) -> a + b);
        operators.put("sub", (a, b) -> a - b);
        operators.put("mul", (a, b) -> a * b);
        operators.put("div", (a, b) -> a / b);
        if (operators.containsKey(op)) {
            return operators.get(op).apply(pa, pb) + " ";
        } else {
            return "Nieznany operator";
        }
    }
}
