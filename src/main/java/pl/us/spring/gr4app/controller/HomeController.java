package pl.us.spring.gr4app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {
    byte a = 0x5;
    // /home?name=karol
    @RequestMapping("/home")
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String name = request.getParameter("name");
        response.setStatus(200);
        response.setContentType("text/html");
        final PrintWriter writer = response.getWriter();
        writer.println("Hello from HomeController!");
        writer.println("Hello " + name);
        writer.flush();
    }
}
