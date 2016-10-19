package br.ufc.quixada.npi.gpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "br.ufc.quixada.npi.ldap", "br.ufc.quixada.npi.gpa" })
public class AssuntosEstudantisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssuntosEstudantisApplication.class, args);
	}
}
