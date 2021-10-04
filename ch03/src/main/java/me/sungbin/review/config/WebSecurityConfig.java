package me.sungbin.review.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	String[] staticResources  =  {
            "/css/**",
            "/js/**",
            "/img/**",
            "/fonts/**",
            "/resources/**"
        };

	@Autowired
	private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable()
		.headers()
			.and()
		.authorizeRequests()
		 	.antMatchers(staticResources).permitAll()
		 	.antMatchers("/kakaoLogin").permitAll()
		 	.antMatchers("/fileUpload").permitAll()
		 	.anyRequest().authenticated()
			.and()

		.exceptionHandling()
			.and()
		.formLogin() 
			.loginPage("/login")
			.defaultSuccessUrl("/login")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
        	.permitAll();
    }

	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers(staticResources); 
    }
}