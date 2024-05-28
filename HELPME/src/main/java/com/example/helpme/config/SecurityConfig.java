package com.example.helpme.config;
import com.example.helpme.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)throws Exception{
       return auth.getAuthenticationManager();
   }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
       AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
       authBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
       AuthenticationManager authenticationManager = authBuilder.build();
        http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth->auth
                       .requestMatchers("/static/**","/",".api/user/**","/*").permitAll()
                       .anyRequest().authenticated()
               )
               .sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authenticationManager(authenticationManager)
               .httpBasic(Customizer.withDefaults());
       http
               .logout(logout -> logout
                       .logoutUrl("/logout") //
                       .logoutSuccessUrl("/") //
                       .invalidateHttpSession(true) //
                       .deleteCookies("JSESSIONID")
               );
        return http.build();

   }



    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }





//@Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//       return http
//
//               .csrf(csrf->csrf.disable())
//                .authorizeRequests(authorizeRequests ->
//                {authorizeRequests.requestMatchers("/", "/email", "/add","/task").permitAll(); // Разрешить доступ ко всему
//
//                    //authorizeRequests.requestMatchers("/task").hasRole("USER");
//                    authorizeRequests.requestMatchers("/PointAdd","/showPoint").authenticated();// Требовать аутентификацию
//                    authorizeRequests.anyRequest().authenticated(); // Для всех остальных запросов требуется аутентификация
//
//
//
//    })
//               .formLogin(Customizer.withDefaults())
//               .httpBasic(Customizer.withDefaults())
//               .build();
//
//
//    }

}