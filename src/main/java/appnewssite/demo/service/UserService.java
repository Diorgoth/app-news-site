package appnewssite.demo.service;

import appnewssite.demo.entity.User;
import appnewssite.demo.exceptions.ResourceNotFoundException;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.UserDTO;
import appnewssite.demo.repository.RoleRepository;
import appnewssite.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    public ApiResponse addUser(UserDTO userDto) {

        User user = new User(
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Not found role with id = " + userDto.getRoleId())),
                true
        );

        userRepository.save(user);
        return new ApiResponse("User saved!", true);


    }
}
