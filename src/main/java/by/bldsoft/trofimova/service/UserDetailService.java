package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Role;
import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        by.bldsoft.trofimova.entity.User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found!");

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role role = user.getRole();
        GrantedAuthority gr = new SimpleGrantedAuthority(role.getRole());
        grantedAuthorities.add(gr);

        return new User(user.getUsername(), user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }
}


