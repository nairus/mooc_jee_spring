package fr.eservices.sample1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    @Autowired
    Greeter greeter;
    @Autowired
    Printer printer;
    @Autowired
    Welcome welcome;

    public void run() {
        String name = welcome.askName();
        String response = greeter.hello(name);
        printer.print(response);
    }

    public static void main(String[] args) {
        // Create a spring context
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        // Get Application From context
        Application app = ctx.getBean(Application.class);

        // Call run
        app.run();
    }

}
