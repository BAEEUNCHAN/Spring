package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Security가 처리하는 모든 비밀번호는 이 코드를 이용해서 처리.
	}

	@Bean // 메모리상 인증정보 등록 => 테스트 전용
		InMemoryUserDetailsManager inMemoryUserDetailsService() {
			UserDetails user 
				  = User.builder()
						.username("user1")
						.password(passwordEncoder().encode("1234"))
						.roles("USER") // ROLE_USER 강제로 "ROLE_" 붙여서 인식함
					  //.authorities("ROLE_USER") // 이름을 넣는대로 권한이 됨
						.build();
			
				UserDetails admin 
					= User.builder()
					.username("admin1")
					.password(passwordEncoder().encode("1234"))
					//.roles("admin") // ROLE_USER 강제로 "ROLE_" 붙여서 인식함
				   .authorities("ROLE_ADMIN") // 이름을 넣는대로 권한이 됨
				   .build();
				   
			return new InMemoryUserDetailsManager(user,admin);
	}
		//인증 및 인가 
	@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//적용될 Security 설정
		// =>URI에 적용될 권한
		http
			.authorizeHttpRequests(authrize
					-> authrize
					.requestMatchers("/","/all").permitAll()
					.requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
					.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().authenticated() // 위에 정의 된 것을 빼고 나머지 전부를 일괄처리
			)
			.formLogin(formlogin -> formlogin
					  .defaultSuccessUrl("/all"))
			.logout(logout -> logout
					.logoutSuccessUrl("/all"));
		
		return http.build();
	}
}


