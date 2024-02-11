package ru.gb.springdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.UserEntity;
import ru.gb.springdemo.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByLogin(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));


    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of(
      new SimpleGrantedAuthority(user.getRole())
    ));
  }

}