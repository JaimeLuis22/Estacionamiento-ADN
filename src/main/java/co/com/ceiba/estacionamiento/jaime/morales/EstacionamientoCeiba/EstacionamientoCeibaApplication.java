package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ImportResource({"classpath*:applicationContext.xml"})
public class EstacionamientoCeibaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamientoCeibaApplication.class, args);
	}

}
