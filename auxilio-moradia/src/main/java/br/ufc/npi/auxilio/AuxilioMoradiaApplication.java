package br.ufc.npi.auxilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "br.ufc.quixada.npi.ldap", "br.ufc.npi.auxilio" })
public class AuxilioMoradiaApplication extends SpringBootServletInitializer {
	public AuxilioMoradiaApplication() {
	    super();
	    this.setRegisterErrorPageFilter(false);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		this.setRegisterErrorPageFilter(false);
		application.sources(AuxilioMoradiaApplication.class);
	    return application;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuxilioMoradiaApplication.class, args);
	}
}
