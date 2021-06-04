package com.jgeekmz.management_app.configurations;

import com.jgeekmz.management_app.models.User;
import com.jgeekmz.management_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SaveUserConfig implements CommandLineRunner {

    @Autowired
	private UserRepository userRepository;

    public void saveAdminUser() {
    	User usr1 = new User();
        usr1.setEmail("martin.zlatkov1991@gmail.com");
        usr1.setPassword("$2a$10$bdwdTAXdhYUdeETnu/oDi.4KcUyI7dZaz3ZsLQpVCgXeB3T7Be/I6");
        usr1.setUsername("jgeek");
        usr1.setEnabled(true);
        usr1.setFirstname("Martin");
        usr1.setLastname("Zlatkov");
        userRepository.save(usr1);
    }

    @Override
    public void run(String... args) throws Exception {
        saveAdminUser();
    }
}
