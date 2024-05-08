package cta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import cta.Visualizador;
import jakarta.servlet.ServletContextListener;

public class WebHookShutdownConfig {
	@Autowired
	Visualizador vis;

    @Bean
    ServletListenerRegistrationBean<ServletContextListener> myServletListener() {
        ServletListenerRegistrationBean<ServletContextListener> srb =
                new ServletListenerRegistrationBean<>();
        srb.setListener(vis);
        return srb;
    }
}
