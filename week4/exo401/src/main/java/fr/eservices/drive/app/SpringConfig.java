package fr.eservices.drive.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;

// use this class as a configuration class for spring context
@Configuration
// set a scan package to get JPA DAO and Hmac password checker
@ComponentScan(basePackages = { "fr.eservices.drive.app", "fr.eservices.drive.dao.impl", "fr.eservices.drive.util" })
public class SpringConfig implements ApplicationListener<ApplicationContextEvent> {

    // expose this as a bean for spring context
    // expose an entity manager for DAO using JPA
    @Bean
    EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        return emf.createEntityManager();
    }

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        if (!(event instanceof ContextClosedEvent))
            return;

        EntityManagerFactory emf = event.getApplicationContext().getBean(EntityManagerFactory.class);
        emf.close();
    }

}
