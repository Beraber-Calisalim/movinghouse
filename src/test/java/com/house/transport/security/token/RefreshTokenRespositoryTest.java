package com.house.transport.security.token;

import com.house.transport.config.TestApplication;
import com.house.transport.model.Customer;
import com.house.transport.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = TestApplication.class)
public class RefreshTokenRespositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void deleteTokenByCustomer(){
        Customer customer = new Customer(null, "Muhammed","Kalabasi","muhammedkalabasi@gmail.com","5343343434","Password_1234");
        customerRepository.save(customer);
        Customer foundCustomer = customerRepository.findByEmail("muhammedkalabasi@gmail.com").orElseThrow();
        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(86400))
                .customer(foundCustomer).build();
        refreshTokenRepository.save(token);
        refreshTokenRepository.deleteByCustomer(customer);
        Optional<RefreshToken> foundRefreshToken = refreshTokenRepository.findById(1L);
        assertThat(foundRefreshToken).isNotPresent();
    }
}