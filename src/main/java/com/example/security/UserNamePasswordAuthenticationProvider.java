package com.example.security;

import com.example.model.Person;
import com.example.model.Role;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = null , password = null;
        loginName = authentication.getName();
        password = authentication.getCredentials().toString();
        Person person = personRepository.findByEmail(loginName);
        if(person != null && person.getPersonId()>0 && passwordEncoder.matches(password,person.getPassword())){
            return new UsernamePasswordAuthenticationToken(loginName,null,getGrantedAuthorities(person.getRole()));
        }else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }
    public List<GrantedAuthority> getGrantedAuthorities(Role role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
