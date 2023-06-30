package gr.aueb.cf.medicalapp.security;

import gr.aueb.cf.medicalapp.repository.DoctorRepository;
import gr.aueb.cf.medicalapp.service.CustomDoctorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * DoctorSecurityConfig is the configuration class for securing the endpoints
 * and implementing authentication for doctors.
 */
@Configuration
@EnableWebSecurity
public class DoctorSecurityConfig {
    @Autowired
    private DoctorRepository doctorRepo;

    /**
     * Creates an instance of CustomDoctorDetailsService.
     *
     * @return the CustomDoctorDetailsService instance
     */
    @Bean
    public UserDetailsService customerUserDetailsService1() {
        return new CustomDoctorDetailsService();
    }

    /**
     * Creates an instance of BCryptPasswordEncoder.
     *
     * @return the BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an instance of DaoAuthenticationProvider and configures it.
     *
     * @return the configured DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerUserDetailsService1());
        authProvider.setPasswordEncoder(passwordEncoder1());
 
        return authProvider;
    }

    /**
     * Configures the security filter chain for doctor endpoints.
     *
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain instance
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider1());

        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/doctor/**")
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/doctor/login")
                .usernameParameter("email")
                .loginProcessingUrl("/doctor/login")
                .defaultSuccessUrl("/doctor/homepage")
                .failureUrl("/doctor/login?error")
                .permitAll()
            .and()
                .logout()
                    .logoutUrl("/doctor/logout")
                    .logoutSuccessUrl("/");
 
        return http.build();
    }
}
