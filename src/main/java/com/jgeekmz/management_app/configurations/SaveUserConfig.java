package com.jgeekmz.management_app.configurations;

import com.jgeekmz.management_app.models.User;
import com.jgeekmz.management_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveUserConfig {

    @Autowired
	private UserRepository userRepository;

    public void saveUser() {
    	User usr1 = new User();
        usr1.setEmail("martin.zlatkov1991@gmail.com");
        usr1.setPassword("$2a$10$bdwdTAXdhYUdeETnu/oDi.4KcUyI7dZaz3ZsLQpVCgXeB3T7Be/I6");
        usr1.setUsername("jgeek");
        usr1.setEnabled(true);
        usr1.setFirstname("Martin");
        userRepository.save(usr1);
    }
}
