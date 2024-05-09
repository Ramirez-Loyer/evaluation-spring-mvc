package fr.fms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity  //désactive le formulaire d'authentification par défaut de spring
                    // et active notre stratégie de sécurité

public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Override //cette m"thode précise si les users sont en base, dans un fichier, en mémoire comme ci-dessous
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //Il est impératif de toujours stocker en mémoire ou en base des mots de pass crypté
       //création d'utilisateurs en mémoire avec mot de passe crypté et des rôles distincts
       PasswordEncoder pe = passwordEncoder();
       auth.inMemoryAuthentication().withUser("alejandra").password(pe.encode("12345")).roles("CONNECTED_USER", "USER");
       auth.inMemoryAuthentication().withUser("coco").password(pe.encode("12345")).roles("USER");
       //indique à Spring l'algo utilisé pour le cryptage des pwd
       auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
   }

   @Bean     //annotation permettant à cet objet d'être inscrit dans le contexte de Spring
                //et delors peut-être utilisé ailleurs dnas l'appli via @Autowired
    PasswordEncoder passwordEncoder() {    //chaque méthode décorée par l'annotation @ Bean implique qu'elle sera exécutée au démarrage de l'app
                return new BCryptPasswordEncoder();
    }
@Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin();
       //attribution des accès aux pages en fonction des rôles
    http.authorizeRequests().antMatchers("/index", "contactsList", "/save", "/delete", "update", "/contact" ).hasRole("CONNECTED_USER");
    http.authorizeRequests().antMatchers("/index").hasRole("USER" );
}
}
