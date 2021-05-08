package appnewssite.demo.service;

import appnewssite.demo.entity.User;
import appnewssite.demo.exceptions.ResourceNotFoundException;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.RegisterDTO;
import appnewssite.demo.repository.RoleRepository;
import appnewssite.demo.repository.UserRepository;
import appnewssite.demo.utils.Appconstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ApiResponse registerUser(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("Parollar mos emas",false);

        if (userRepository.existsByUsername(registerDTO.getUsername())){
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan",false);
        }

        User user = new User(registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                 roleRepository.findByName(Appconstants.USER).orElseThrow(() -> new ResourceNotFoundException("role","name",Appconstants.USER)),
                true);
        userRepository.save(user);

        return new ApiResponse("Muvafaqqiyatli ro'yxatdan o'tdingiz",true);
    }

    public UserDetails loadUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
