package br.com.equipe4.app_produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AppProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppProdutosApplication.class, args);
	}

}
