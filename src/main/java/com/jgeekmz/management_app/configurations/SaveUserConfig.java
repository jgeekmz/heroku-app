package com.jgeekmz.management_app.configurations;

import com.jgeekmz.management_app.models.Role;
import com.jgeekmz.management_app.models.User;
import com.jgeekmz.management_app.repositories.RoleRepository;
import com.jgeekmz.management_app.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class SaveUserConfig implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(SaveUserConfig.class);
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    private void saveAdminUser() {

        Role checkRole = roleRepository.findByName("ROLE_ADMIN");
        Role rl = new Role();
        if(checkRole!=null) {
            log.debug("User Admin role already created in database.");
        } else {
            // Create Admin Role in Database
            rl.setId(1);
            rl.setName("ROLE_ADMIN");
            roleRepository.save(rl);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(rl.getName()));
        //roles.add(rl);

        User usr1 = new User();
        usr1.setId(1);
        usr1.setEmail("martin.zlatkov1991@gmail.com");
        usr1.setPassword("$2a$10$bdwdTAXdhYUdeETnu/oDi.4KcUyI7dZaz3ZsLQpVCgXeB3T7Be/I6");
        usr1.setUsername("jgeek");
        usr1.setEnabled(true);
        usr1.setAdmin(true);
        usr1.setFirstname("Martin");
        usr1.setLastname("Zlatkov");
        usr1.setRoles(roles);

        User checkAdminInDatenbank = userRepository.findByUsername("jgeek");
        if (checkAdminInDatenbank != null) {
            log.debug("Admin user was found in database.");
        } else {
            userRepository.save(usr1);
        }
    }

    private void createRoles() {
        Role rl2 = new Role();
        rl2.setId(2);
        rl2.setName("ROLE_USER");

        Role rl3 = new Role();
        rl3.setId(3);
        rl3.setName("ROLE_EDITOR");

        Role rl4 = new Role();
        rl4.setId(4);
        rl4.setName("ROLE_CREATOR");

        Role checkR1 = roleRepository.findByName("ROLE_USER");
        Role checkR2 = roleRepository.findByName("ROLE_EDITOR");
        Role checkR3 = roleRepository.findByName("ROLE_CREATOR");

        if (checkR1 != null && checkR2 != null && checkR3 != null) {
            log.debug("User's roles are active in database.");
        } else {
            roleRepository.save(rl2);
            roleRepository.save(rl3);
            roleRepository.save(rl4);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        saveAdminUser();
        createRoles();
        log.debug("Admin was successfully created and saved into DB!");
        log.debug("Additional Roles were successfully created in DB! >>> Editor, Creator and User roles");
    }
}
