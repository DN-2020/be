package com.db2020.pj.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.db2020.pj.config.jwt.JwtRequestFilter;
import com.db2020.pj.exception.custom.CustomAuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private CustomUserDetailsService myUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(corsConfigurationSource())
			.and()
		    .csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.httpBasic()
			// Filter단 Exception은 검토 불가능하기 때문에 커스텀 EntryPoint
			.and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				  .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
			.and()
	       		  .authorizeRequests()
//				  .antMatchers("/**").permitAll()
	       		  .antMatchers(HttpMethod.OPTIONS, "/api/v1/**").permitAll() 
	       		  .antMatchers("/**/admin/**").hasRole("ADMIN")
	       		  .antMatchers("/**/company/**").hasAnyRole("ADMIN", "EMP")
	       		  .antMatchers("/exception/**").permitAll()
	       		  .antMatchers("/**/image/**").permitAll()
				  .antMatchers("/*/signin", "/*/signup", "/*/logout").permitAll()
				  .antMatchers(HttpMethod.GET,"/*/goods/**").permitAll()
				  .antMatchers(HttpMethod.POST,"/*/goods/**").hasAnyRole("ADMIN", "EMP")
				  .antMatchers(HttpMethod.PUT,"/*/goods/**").hasAnyRole("ADMIN", "EMP")
				  .antMatchers(HttpMethod.DELETE,"/*/goods/**").hasAnyRole("ADMIN", "EMP")
				  .antMatchers("/*/user/**").hasAnyRole("ADMIN", "USER")
				  .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				  .anyRequest().authenticated();
           
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	        
	  CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "TOKEN_ID", "X-Requested-With", "Authorization", "Content-Type", "Content-Length", "Cache-Control"));
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        
	        return source;
	    }
}
