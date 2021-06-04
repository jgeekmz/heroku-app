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


    public void saveAdminUser() {

        Role rl = new Role();
        rl.setId(1);
        rl.setName("ROLE_ADMIN");
        roleRepository.save(rl);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(rl.getName()));

    	User usr1 = new User();
    	usr1.setId(1);
        usr1.setEmail("martin.zlatkov1991@gmail.com");
        usr1.setPassword("$2a$10$bdwdTAXdhYUdeETnu/oDi.4KcUyI7dZaz3ZsLQpVCgXeB3T7Be/I6");
        usr1.setUsername("jgeek");
        usr1.setEnabled(true);
        usr1.setFirstname("Martin");
        usr1.setLastname("Zlatkov");
        usr1.setRoles(roles);
        userRepository.save(usr1);
    }

    @Override
    public void run(String... args) throws Exception {
        saveAdminUser();
        log.debug("Admin was successfully created and saved into DB!");
    }
}
