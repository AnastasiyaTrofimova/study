package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collection;


public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }


        user.getUsername();
        user.getPassword();
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        //  userRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }
}
