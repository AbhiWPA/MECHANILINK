package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.persistence.UserRepo;
import lk.ijse.userservice.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Title: mechani-link
 * Description: CustomUserService Class
 * Created by Abhishek Ashinsa on 6/13/2026
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().name())
                .build();
    }
}
