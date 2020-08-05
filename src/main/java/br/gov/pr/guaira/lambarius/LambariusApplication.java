package br.gov.pr.guaira.lambarius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.gov.pr.guaira.lambarius.storage.FotoStorage;
import br.gov.pr.guaira.lambarius.storage.local.FotoStorageLocal;

@SpringBootApplication
public class LambariusApplication {

	public static void main(String[] args) {
		SpringApplication.run(LambariusApplication.class, args);
	}

	public static class MvcConfig implements WebMvcConfigurer {
		
		@Bean
		public FotoStorage fotoStorage() {
			return new FotoStorageLocal();
		}				
	}
}
