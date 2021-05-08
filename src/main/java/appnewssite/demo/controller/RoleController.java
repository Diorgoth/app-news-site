package appnewssite.demo.controller;

import appnewssite.demo.aop.CheckPermission;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.RoleDTO;
import appnewssite.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_LAVOZIM')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO){
        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


//    @PreAuthorize(value = "hasAnyAuthority('EDIT_LAVOZIM')")
    @CheckPermission(value = "EDIT_LAVOZIM")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO){
        ApiResponse apiResponse = roleService.editRole(id,roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
