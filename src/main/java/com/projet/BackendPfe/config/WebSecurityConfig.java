package com.projet.BackendPfe.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.BackendPfe.services.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPointJwt unauthorizedHandler;

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/login").permitAll()
			.antMatchers("/api/sendEmailAdminEtMedecin").permitAll()
			.antMatchers("/api/sendEmailPatient").permitAll()
			.antMatchers("/api/test/**").permitAll()
			
			/*******************  Admin Digital Manager ***************************/
			
			.antMatchers("/adminDigital/signup").permitAll()
			.antMatchers("/adminDigital/login").permitAll()
			.antMatchers("/adminDigital/update/{id}").permitAll()
			.antMatchers("/adminDigital/updateImageProfileAdminDigial/{id}").permitAll()
			.antMatchers("/adminDigital/getAdminDigital/{id}").permitAll()
			.antMatchers("/adminDigital/getImageProfileAdminDigial/{id}").permitAll()
			.antMatchers("/adminDigital/deletAdminDigital/{id}").permitAll()

			/*******************  Admin Medical Manager ***************************/

			.antMatchers("/adminMedical/login").permitAll()
			.antMatchers("/adminMedical/update/{id}").permitAll()
			.antMatchers("/adminMedical/getAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/updateImageProfileAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/getImageProfileAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/deletAdminMedical/{id}").permitAll()
			.antMatchers("/addAdminMedicalParAdminDigitalSansImage/{id}").permitAll()
			.antMatchers("/adminMedical/all").permitAll()
		
			/*******************  Medecin ***************************/
			.antMatchers("/medecin/login").permitAll()
			.antMatchers("/medecin/addMedecinParAdminSansImage/{idAdmin}").permitAll()
			.antMatchers("/medecin/updateMedecin/{id}").permitAll()
			.antMatchers("/medecin/getMedecin/{id}").permitAll()
			.antMatchers("/medecin/getImageMedecin/{id}").permitAll()
			.antMatchers("/medecin/updateImageMedecin/{id}").permitAll()
			.antMatchers("/medecin/delecteMedecin/{id}").permitAll()
			.antMatchers("/medecin/all").permitAll()
			.antMatchers("/medecin/patientsMedecin/{idMedecin}").permitAll()
			.antMatchers("/medecin/homme").permitAll()
			.antMatchers("/medecin/femme").permitAll()
			.antMatchers("/medecin/nbrAll").permitAll()

			/*******************  Patient ***************************/
			
			.antMatchers("/patient/login").permitAll()
			.antMatchers("/patient/updateImageProfilePatient/{id}").permitAll()
			.antMatchers("/patient/getImageProfilePatient/{id}").permitAll()
			.antMatchers("/patient/deletPatient/{id}").permitAll()
			.antMatchers("/patient/addPatientParAdminSansImage/{id}").permitAll()
			.antMatchers("/patient/updatePatient/{id}").permitAll()
			.antMatchers("/patient/getPatient/{id}").permitAll()
			.antMatchers("/patient/all").permitAll()
			.antMatchers("/patient/age/{idPatient}").permitAll()
			.antMatchers("/patient/homme").permitAll()
			.antMatchers("/patient/femme").permitAll()
			.antMatchers("/patient/nbrAll").permitAll()
		
			/*******************  Specialite ***************************/

			.antMatchers("/specialite/addSpecialite").permitAll()
			.antMatchers("/specialite/getSpecialite/{id}").permitAll()
			.antMatchers("/specialite/getAll").permitAll()
			.antMatchers("/specialite/deletSpecialite/{id}").permitAll()
			.antMatchers("/specialite/updateSpecialite/{id}").permitAll()
			
		
		   /****************** Operations *****************************/
		.antMatchers("/operation/addOperation/{idPatient}/{idMedecin}").permitAll()
		.antMatchers("/operation/getOperation/{id}").permitAll()
		.antMatchers("/operation/update/{id}").permitAll()
		.antMatchers("/operation/deletOperation/{id}").permitAll()
		
		.anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
