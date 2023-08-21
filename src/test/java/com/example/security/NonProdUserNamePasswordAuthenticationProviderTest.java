package com.example.security;

import com.example.model.Person;
import com.example.model.Role;
import com.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class NonProdUserNamePasswordAuthenticationProviderTest {

    @Mock // mock dependency
    private PersonRepository personRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks // inject mocks to your selected unit test  class
    NonProdUserNamePasswordAuthenticationProvider authenticationProvider;



    @Test
    public void validUserAuthentication(){
        Role role = new Role();
        role.setRole_Id(1);
        role.setRoleName("ROLE_ADMIN");
        Person person = new Person();
        person.setPersonId(1);
        person.setEmail("shehan@gmail.com");
        person.setRole(role);
        when(authentication.getName()).thenReturn("shehan@gmail.com");
        when(authentication.getCredentials()).thenReturn("shehan");
        when(personRepository.findByEmail("shehan@gmail.com")).thenReturn(person);

        assertThat(authenticationProvider.authenticate(authentication)).isNotNull();

    }


}
