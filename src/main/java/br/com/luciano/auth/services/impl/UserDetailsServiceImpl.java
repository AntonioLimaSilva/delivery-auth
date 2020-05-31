package br.com.luciano.auth.services.impl;

import br.com.luciano.auth.entities.UserEntity;
import br.com.luciano.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luciano lima
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserEntity> users = userRepository.findByUsername(username);
        UserEntity userEntity = users.stream().findAny().orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválido!"));
        return new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), userEntity.getAuthorities());
    }
}
