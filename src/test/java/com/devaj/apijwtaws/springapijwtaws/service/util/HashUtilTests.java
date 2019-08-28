package com.devaj.apijwtaws.springapijwtaws.service.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class HashUtilTests {

    @Test
    public void getSecureHashTest(){

        String hash = HashUtil.getSecureHash("123");
        System.out.println(hash);
        System.out.println(new BCryptPasswordEncoder().encode("123"));

        assertThat(hash.length()).isEqualTo(64);
    }
}
