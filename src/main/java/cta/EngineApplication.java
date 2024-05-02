package cta;

import java.awt.EventQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EngineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EngineApplication.class, args);//
		var ctx = new SpringApplicationBuilder(EngineApplication.class).web(WebApplicationType.SERVLET)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {
			var vis = ctx.getBean(Visualizador.class);
			vis.reloadCatalogos();
			vis.setVisible(true);
		});

	}

}
