package br.ufc.npi.auxilio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfig {

	@Autowired
	private Environment environment;
	
	// LDAP
	public static final String LDAP_URL = "ldap.url";
	public static final String LDAP_BASE = "ldap.base";
	public static final String LDAP_USER = "ldap.user";
	public static final String LDAP_PASSWORD = "ldap.password";
	public static final String LDAP_OU = "ldap.ou";

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(environment.getRequiredProperty(LDAP_URL));
		contextSource.setBase(environment.getRequiredProperty(LDAP_BASE));
		contextSource.setUserDn(environment.getRequiredProperty(LDAP_USER));
		contextSource.setPassword(environment.getRequiredProperty(LDAP_PASSWORD));
		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}

	@Bean(name = LDAP_URL)
	public String base() {
		return environment.getRequiredProperty(LDAP_OU);
	}

}
