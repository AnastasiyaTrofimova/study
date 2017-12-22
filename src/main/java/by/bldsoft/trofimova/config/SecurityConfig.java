package by.bldsoft.trofimova.config;

import by.bldsoft.trofimova.repository.UserRepository;
import by.bldsoft.trofimova.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@ComponentScan("by.bldsoft.trofimova")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // private String query = "select user where username=? and password=?";


    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()  {

         DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
         authProvider.setUserDetailsService(userDetailService);
         authProvider.setPasswordEncoder(passwordEncoder);

         return authProvider;
    }



  /*  public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }*/

   /* @Bean
    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        return
    }*/

   /* @Bean
    public UserDetailsService userDetailsService(){
        DaoAuthenticationProvider aut = new DaoAuthenticationProvider();
        aut.setUserDetailsService(); */
        //AuthenticationManagerBuilder aut = new AuthenticationManagerBuilder();
        //aut.jdbcAuthentication().usersByUsernameQuery("query");



              //  .userDetailsService(userDetailsService()).getUserDetailsService()
              //  .loadUserByUsername().getAuthorities().
                //.loadUserByUsername();

   // }


    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("ivan").password("$2a$12$YnSVSv..LbyDgHuUTmvyCejKb8uchvfkgwxA4/AyyZnU.93YXyWTG").roles("admin").build());
        return manager;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login")
                .permitAll();
    }

}
