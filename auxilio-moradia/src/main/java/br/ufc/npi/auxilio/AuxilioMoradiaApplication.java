package br.ufc.npi.auxilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "br.ufc.quixada.npi.ldap", "br.ufc.npi.auxilio" })
@EnableAutoConfiguration
public class AuxilioMoradiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuxilioMoradiaApplication.class, args);
	}
}
