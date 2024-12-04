package com.denteconvenio.planoservice.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    @Autowired
    private SecurityFilter securityFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(req -> {
            req.requestMatchers(new AntPathRequestMatcher("/assinatura", HttpMethod.GET.name())).hasAnyRole("BENEFICIARIO","EMPRESA");
            req.requestMatchers(new AntPathRequestMatcher("/assinatura", HttpMethod.POST.name())).hasAnyRole("BENEFICIARIO","EMPRESA");
            req.requestMatchers(new AntPathRequestMatcher("/assinatura", HttpMethod.PATCH.name())).hasAnyRole("BENEFICIARIO","EMPRESA");
            req.requestMatchers(new AntPathRequestMatcher("/assinatura", HttpMethod.DELETE.name())).hasAnyRole("BENEFICIARIO","EMPRESA");
            req.requestMatchers(new AntPathRequestMatcher("/assinatura/{idBeneficio}", HttpMethod.POST.name())).hasAnyRole("BENEFICIARIO");


            req.requestMatchers(new AntPathRequestMatcher("/plano", HttpMethod.GET.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/plano", HttpMethod.POST.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/plano/{id}", HttpMethod.PATCH.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/plano/{id}", HttpMethod.DELETE.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/plano/beneficio", HttpMethod.POST.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/plano/beneficio", HttpMethod.DELETE.name())).permitAll();

            req.requestMatchers(new AntPathRequestMatcher("/beneficio", HttpMethod.GET.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/beneficio", HttpMethod.POST.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/beneficio/{id}", HttpMethod.PATCH.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/beneficio/{id}", HttpMethod.DELETE.name())).permitAll();

            req.requestMatchers(new AntPathRequestMatcher("/cobertura/{idConsultorio}", HttpMethod.GET.name())).permitAll();
            req.requestMatchers(new AntPathRequestMatcher("/cobertura/{idConsultorio}/{idBeneficio}", HttpMethod.POST.name())).permitAll();

            req.requestMatchers(new AntPathRequestMatcher("/cobertura", HttpMethod.POST.name())).hasRole("CONSULTORIO");
            req.requestMatchers(new AntPathRequestMatcher("/cobertura/atendimento", HttpMethod.POST.name())).hasRole("CONSULTORIO");
            req.requestMatchers(new AntPathRequestMatcher("/cobertura/atendimento", HttpMethod.PATCH.name())).hasRole("CONSULTORIO");
            req.requestMatchers(new AntPathRequestMatcher("/cobertura", HttpMethod.DELETE.name())).hasRole("CONSULTORIO");

            req.anyRequest().authenticated();
        })
        .addFilterBefore(securityFilter, SecurityContextPersistenceFilter.class)
        .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    
}