package com.rajdeep.blogapi.JWT;



import com.rajdeep.blogapi.daoRepositories.UserDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class UserDetailsConfig implements UserDetailsService{


    // spring security use this UserDetailsService, whenever we do login from spring security

    // using username and password, then this service loads the user details as per the username and password


// making it bean: as this class is by-default class of spring,
// and we want to make this class services available in whole application,
// means we can make object of this class and use anywhere in the project so we declare it as a bean.

//     @Bean
//      public UserDetailsService userDetailsService(){
//
//          UserDetails user1= User.builder().username("rajdeep").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
//          UserDetails user2= User.builder().username("gagandeep").password(passwordEncoder().encode("efgh")).roles("ADMIN").build();
//
//          return new InMemoryUserDetailsManager(user1, user2);
//
//
//      }


    // fetching Username and password from databse :-

    // to load user from database (for login, username==email, password==userpassword)

    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user=this.userDao.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email: "+username,0));
        // but issue is this that we need to return UserDetails type
        // so we implement UserModel to UserDetials, then we can return user, as UserModel implements
        // UserDetails

        UserModel user1=user;

        user1.setPassword(passwordEncoder().encode(user.getPassword()));

        return user1;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}