package ua.edu.ukma.javaee.polishchuk.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.javaee.polishchuk.demo.models.PermissionEntity;
import ua.edu.ukma.javaee.polishchuk.demo.models.User;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.UserRepository;

import java.security.Permission;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));
        return org.springframework.security.core.userdetails.User.builder().username(username).password(user.getPassword()).authorities(mapAuthorities(user.getPermissions())).build();
    }

    private static List<GrantedAuthority> mapAuthorities(final List<PermissionEntity> permissions) {
        return permissions.stream().map(PermissionEntity::getPermission).map(Enum::name).map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableList());
    }
}
