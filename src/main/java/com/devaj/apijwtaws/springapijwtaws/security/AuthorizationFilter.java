package com.devaj.apijwtaws.springapijwtaws.security;

import com.devaj.apijwtaws.springapijwtaws.constant.SecurityConstant;
import com.devaj.apijwtaws.springapijwtaws.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwt == null || !jwt.startsWith(SecurityConstant.JWT_PROVIDER)){
            ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityConstant.JWT_INVALID_MSG, new Date());
            PrintWriter writer = httpServletResponse.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            String apiErrorString = mapper.writeValueAsString(apiError);

            writer.write(apiErrorString);

            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        jwt = jwt.replace(SecurityConstant.JWT_PROVIDER, "");

        try{

            Claims claims = new JwtManager().parseToken(jwt);
            String email = claims.getSubject();
            List<String> roles = (List<String>) claims.get(SecurityConstant.JWT_ROLE_KEY);

            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));

            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (Exception ex){

            ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());
            PrintWriter writer = httpServletResponse.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            String apiErrorString = mapper.writeValueAsString(apiError);

            writer.write(apiErrorString);

            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
