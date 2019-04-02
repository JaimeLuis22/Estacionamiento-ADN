package co.com.ceiba.estacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ImportResource({"classpath*:applicationContext.xml"})
public class EstacionamientoCeibaApplication {

	/**
	 * Metodo que ejecuta la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EstacionamientoCeibaApplication.class, args);
	}

}
