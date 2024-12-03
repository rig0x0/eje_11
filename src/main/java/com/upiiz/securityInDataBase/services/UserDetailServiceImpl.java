package com.upiiz.securityInDataBase.services;

import com.upiiz.securityInDataBase.entities.UserEntity;
import com.upiiz.securityInDataBase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Requerimos consultar un usuario por usename
        UserEntity userEntity = userRepository.findUserEntityByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username+ "not found"));

        // Roles y permisos
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // ROLES
        userEntity.getRoles().forEach(roles -> {
            authorities.add( new SimpleGrantedAuthority("ROLE" + roles.getRolEnum().name()));
        });
        //PERMISOS
        userEntity.getRoles().stream().flatMap(role -> role.getPermissions().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnable(), userEntity.isAccountNonLocked(), userEntity.isCredentialsNonExpired(), userEntity.isAccountNonLocked(), authorities);
    }
}
