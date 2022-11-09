package Daniela.TodoGamersShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class TodoGamersShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoGamersShopApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect(){
		return new SpringDataDialect();
	}

}
