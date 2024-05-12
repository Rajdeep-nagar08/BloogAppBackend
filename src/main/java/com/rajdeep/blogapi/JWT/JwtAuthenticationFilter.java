package com.rajdeep.blogapi.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
// make it component so that we can autowire it anywhere in our application.

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    //This class is present in config package,
    //InMemoryUserDetailsManager of this class gives all the users, currently we have two users

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // in header, we have like this :-
        // Authorization = Bearer bdjdhkejfkejfkejfehfkehfusgjdhwkdjkdjksdksj
//        so we are fetching "Bearer bdjdhkejfkejfkejfehfkehfusgjdhwkdjkdjksdksj"

        String requestHeader = request.getHeader("Authorization");

        logger.info(" Header : "+requestHeader);


        String username=null;

        String token=null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer")){

            token=requestHeader.substring(7);

            try {
                username=this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal argument while fetching the username !!!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        else {
            logger.info("Invalid Header Value !! ");
        }

        // authentication

        // will do authentication if username fetched from token is not null and its authentication is not done yet

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            //fetch user detail from databse using its username that we fetched from token

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // validating userdetails that is fetched from database vs userDetails present in token

            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }

        }

        filterChain.doFilter(request, response);
    }

}
