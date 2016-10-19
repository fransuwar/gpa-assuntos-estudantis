package br.ufc.quixada.npi.gpa.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "br.ufc.quixada.npi.ldap", "br.ufc.quixada.npi.gpa.config", "br.ufc.quixada.npi.gpa.model"})
@EnableJpaRepositories("br.ufc.quixada.npi.gpa.repository")
@EntityScan("br.ufc.quixada.npi.gpa.model")
@EnableAutoConfiguration
public class AssuntosEstudantisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AssuntosEstudantisApplication.class, args);
	}

}
