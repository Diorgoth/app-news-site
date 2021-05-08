package appnewssite.demo.service;

import appnewssite.demo.entity.Role;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.RoleDTO;
import appnewssite.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public ApiResponse addRole(RoleDTO roleDTO) {

        if (roleRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("Bunday lavozim bor",false);

        Role role = new Role(
                roleDTO.getName(),
                roleDTO.getHuquqList(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);

        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editRole(Long id, RoleDTO roleDTO) {
        return new ApiResponse("", true);

    }
}
