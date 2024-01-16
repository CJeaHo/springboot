package com.study.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 반환형은 똑같이 일치시켜야한다
		// request -> request(security 7.0부터 무조건 람다형태로 ) 전엔 http.csrf().disable() 
		http.csrf((csrf)->csrf.disable())
			.cors((cors)->cors.disable())
			.authorizeHttpRequests(request->request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 디스패쳐(서버)에 오는(forward) 모든 것들을 로그인 안해도 허용
					.requestMatchers("/").permitAll() // 제일 최상위 루트 폴더내 파일 전부 허용
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()  // resources 하위 폴더내 파일 전부 허용
					
					// 위의 코드는 거의 고정 아래 코드는 그때 그때 다르다
					.requestMatchers("/guest/**").permitAll() // guest 폴더내 파일 전부 허용
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN") // 유저나 관리자만 허용 (한명 이상)
					.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 허용 (한명만)
					.anyRequest().authenticated() // 그외 모든 사용자는 권한이 필요하다
			); 
		
		// 스프링부트에서 제공해줌
//		http.formLogin((formLogin)->formLogin.permitAll()); // 로그인폼 전부 허용
//		이번엔 직접 만든 loginForm을 사용할 거임
		http.formLogin((formLogin)->formLogin
									.loginPage("/loginForm") // loginForm.jsp
									.loginProcessingUrl("/login_check") // 매핑한 url(action의 url)
									.usernameParameter("username") // 기본값(j_username)
									.passwordParameter("password") // 기본값(j_password)
									.permitAll() // 로그인 폼에 입력한 사람은 허용
		);
		
		http.logout((logout)->logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll()); // 로그아웃 전부 허용
		
		return http.build();
	}
	
	@Bean // 메모리에만 사용자 정보를 담는다(DB말고)
	public UserDetailsService users() {
		UserDetails user = User.builder()
							   .username("user")
							   .password(pEncoder().encode("1234")) // 그냥 넣으면 인코딩이 안되서 오류
							   .roles("USER") // 대문자로
							   .build();
		
		UserDetails admin = User.builder()
								.username("admin")
								.password(pEncoder().encode("1234")) // 그냥 넣으면 인코딩이 안되서 오류
								.roles("USER", "ADMIN") // 대문자로
								.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// 비밀번호 인코딩
	public PasswordEncoder pEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
