package appnewssite.demo.component;

import appnewssite.demo.entity.Role;
import appnewssite.demo.entity.User;
import appnewssite.demo.entity.enums.Huquq;
import appnewssite.demo.repository.RoleRepository;
import appnewssite.demo.repository.UserRepository;
import appnewssite.demo.utils.Appconstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {
            Huquq[] huquqs = Huquq.values();


            Role admin = roleRepository.save(
                    new Role(Appconstants.ADMIN, Arrays.asList(huquqs),"Sistema egasi")

            );

            Role user = roleRepository.save(new Role(Appconstants.USER,
                    Arrays.asList(Huquq.ADD_COMMENT, Huquq.EDIT_COMMENT, Huquq.DELETE_MY_COMMENT),"Oddiy foydalanuvchi"));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin, true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user, true
            ));
        }
    }
}
