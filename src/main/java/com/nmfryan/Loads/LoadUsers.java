package com.nmfryan.Loads;

import com.nmfryan.Entities.User;
import com.nmfryan.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadUsers {

    private static final Logger log = LoggerFactory.getLogger(LoadUsers.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar", "Amazing")));
            log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief", "wow")));
        };
    }
}
