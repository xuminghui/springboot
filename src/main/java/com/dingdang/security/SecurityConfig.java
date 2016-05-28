package com.dingdang.security;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/securityError", "/").permitAll().anyRequest().authenticated()
				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasRole("USER");
		http.formLogin().loginPage("/login").failureUrl("/securityError").defaultSuccessUrl("/").and().rememberMe()
				.tokenValiditySeconds(2419200).key("spitterKey").and().logout().logoutSuccessUrl("/").and().httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("xuminghui").password("xuminghui").roles("USER").and().withUser("admin")
				.password("admin").roles("USER", "ADMIN");
	}
	//此处配置H2 DATABASE CONSOLE,此处仅仅是在未使用spring security时的唯一配置
	@Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
