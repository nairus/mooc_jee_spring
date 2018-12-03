package fr.eservices.week402.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// Set this class as a configuration class.
@Configuration
// enable spring web mvc
@EnableWebMvc
// scan fr.eservices.week402.ctrl for components
@ComponentScan("fr.eservices.week402")
public class AppConfig {

    // Add a method to provide an InternalReourceViewResolver
    // all views should be some jsp
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver res = new InternalResourceViewResolver();
        res.setPrefix("/WEB-INF/views/");
        res.setSuffix(".jsp");
        return res;
    }

}
