package gr.aueb.cf.medicalapp.security;

import gr.aueb.cf.medicalapp.repository.PatientRepository;
import gr.aueb.cf.medicalapp.service.CustomPatientDetailsService;
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
 * PatientSecurityConfig is the configuration class for securing the endpoints
 * and implementing authentication for patients.
 */
@Configuration
@EnableWebSecurity
public class PatientSecurityConfig {

    @Autowired
    private PatientRepository patientRepo;

    /**
     * Creates an instance of CustomPatientDetailsService.
     *
     * @return the CustomPatientDetailsService instance
     */
    @Bean
    public UserDetailsService customerUserDetailsService2() {
        return new CustomPatientDetailsService();
    }

    /**
     * Creates an instance of BCryptPasswordEncoder.
     *
     * @return the BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an instance of DaoAuthenticationProvider and configures it.
     *
     * @return the configured DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerUserDetailsService2());
        authProvider.setPasswordEncoder(passwordEncoder2());

        return authProvider;
    }

    /**
     * Configures the security filter chain for patient endpoints.
     *
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain instance
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());

        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/patient/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/patient/login")
                .usernameParameter("email")
                .loginProcessingUrl("/patient/login")
                .defaultSuccessUrl("/patient/homepage")
                .failureUrl("/patient/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/patient/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
}
