package com.yuhan.board.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yuhan.board.provider.JwtProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  { // 확장해준다.
    
    private JwtProvider jwtProvider;

    @Autowired
    
    public JwtAuthenticationFilter(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try{
                    String jwt = parseToken(request);

                    boolean hasJwt = jwt != null;
                    if(!hasJwt) {
                        filterChain.doFilter(request, response);
                        return;
                    }
                    String email = jwtProvider.validate(jwt);

                    AbstractAuthenticationToken authenticationToken = 
                    new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);
                    SecurityContextHolder.setContext(securityContext);




                } catch (Exception exception){
                    exception.printStackTrace();

                }
                filterChain.doFilter(request, response);
       
    }
    private String parseToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        
        boolean hasToken = 
        token!= null && 
        !token.equalsIgnoreCase("null");
        if(!hasToken) return null;

        boolean isbearer = token.startsWith("bearer ");
        if(!isbearer) return null;

        String jwt = token.substring(7);
        return jwt;

    
}
}
