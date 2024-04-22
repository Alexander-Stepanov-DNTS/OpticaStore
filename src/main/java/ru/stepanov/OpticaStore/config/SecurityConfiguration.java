package ru.stepanov.OpticaStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //  registry.requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN");
        // МОЖНО ТАК
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> { ///clientReceptions
                    //registry.requestMatchers("/mainPage/**").hasAnyRole("ADMIN", "DOCTOR", "DOCTOR");
                    registry.requestMatchers("/mainPage/**").permitAll();
                    registry.requestMatchers("/clients/**").hasAnyRole("ADMIN", "DOCTOR", "MANAGER");
                    registry.requestMatchers("/documents/**").hasAnyRole("ADMIN", "MANAGER");
                    registry.requestMatchers("/contracts/**").hasAnyRole("ADMIN", "MANAGER");
                    registry.requestMatchers("/clientReceptions/**").hasAnyRole("ADMIN", "DOCTOR");
                    registry.requestMatchers("/order-forms/**").hasAnyRole("ADMIN", "DOCTOR", "MANAGER");
                    registry.requestMatchers("/lenses-catalog/**").hasAnyRole("ADMIN", "DOCTOR", "MANAGER");
                    registry.requestMatchers("/frames-catalog/**").hasAnyRole("ADMIN", "DOCTOR", "MANAGER");
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
        /*
        //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    //Перенарпавление на главную страницу после успешного входа
                    .defaultSuccessUrl("/admin")
                    .permitAll()
         */
    }
    //Hotel Management Service
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("manager")
                .password("$2a$12$xOIYYujxAOmczE0MoEjayehbthwwqDKlWGPS9o2BKGZmiKG9cJVg2") //1234
                .roles("MANAGER")
                .build();
        UserDetails adminUser = User.builder()
                .username("admin")
                .password("$2a$12$5QM7j4KXIXId6Nm/81zseOxTIfGFuNHEyRs.tH.RU.VNE11fefEqm") //12345
                .roles("ADMIN")
                .build();
        UserDetails hotelManagementServiceUser = User.builder()
                .username("Doctor")
                .password("$2a$12$wKrAloN8sKDsM9QdXlH0O.7nUvIC4iQWCMOlgjHPygc26xLBPzLIa") //1111
                .roles("DOCTOR")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser,hotelManagementServiceUser);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
