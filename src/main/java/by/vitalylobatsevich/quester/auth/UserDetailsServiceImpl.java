package by.vitalylobatsevich.quester.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;

import by.vitalylobatsevich.quester.repository.UserRepository;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return UserDetailsImpl.builder()
                              .user(
                                      userRepository.findByUsername(username)
                                                    .getOrElseThrow(() -> new UsernameNotFoundException(username))
                              )
                              .build();
    }

}
