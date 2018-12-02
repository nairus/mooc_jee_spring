package fr.eservices.sample2.appl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eservices.sample2.api.Greeter;
import fr.eservices.sample2.api.Printer;
import fr.eservices.sample2.api.Welcome;

@Configuration
@ComponentScan("fr.eservices.sample2")
public class Application {

    Welcome welcome;
    Greeter greeter;
    Printer printer;

    public void run() {
        String name = welcome.askName();
        String response = greeter.hello(name);
        printer.print(response);
    }

    public static void main(String[] args) {
        // Create a spring context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        // Get Application From context
        Application app = ctx.getBean(Application.class);

        // Call run
        app.run();
    }

    public Welcome getWelcome() {
        return welcome;
    }

    public void setWelcome(Welcome welcome) {
        this.welcome = welcome;
    }

    public Greeter getGreeter() {
        return greeter;
    }

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
