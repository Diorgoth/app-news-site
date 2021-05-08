package appnewssite.demo.controller;

import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.RegisterDTO;
import appnewssite.demo.payload.UserDTO;
import appnewssite.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_USERS')")
    @PostMapping
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
        ApiResponse apiResponse = userService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
