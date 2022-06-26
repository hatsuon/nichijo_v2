package org.cadmium.nichijo;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NichijoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NichijoApplication.class, args);
    }

}
