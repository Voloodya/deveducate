package ru.cinimex.deveducate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.dal.repository.SellerRepository;

import java.util.Date;

@Configuration
class LoadDatabase {

//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabaseCustomer(CustomerRepository repository) {
//
//        return args -> {
//            CustomerEntity customer = CustomerEntity.builder()
//                    .custFirstName("Ivan")
//                    .custLastName("Ivanovih")
//                    .creditLimit(10000)
//                    .custSity("Orenburg")
//                    .custStreetADddress1("adress 1")
//                    .phoneNumber1("89235789375").build();
//            log.info("Preloading customer " + repository.save(customer));
//            log.info("Preloading customer " + repository.save(CustomerEntity.builder()
//                    .custFirstName("Pety")
//                    .custLastName("Petrov")
//                    .creditLimit(15000)
//                    .custSity("Orenburg")
//                    .custStreetADddress1("adress 1")
//                    .phoneNumber1("89235789370").build()));
//        };
//    }
//
//    @Bean
//    CommandLineRunner initDatabaseSellers(SellerRepository sellerRepository){
//
//        return args -> {
//            log.info("Preloading customer " + sellerRepository.save(SellerEntity.builder()
//                    .adminUser("Admin")
//                    .createOn(new Date())
//                    .product("Product 1")
//                    .sellerName("Ivan")
//                    .quota(100)
//                    .expiresOn(new Date())
//                    .password("********")
//                    .build()));
//            log.info("Preloading customer " + sellerRepository.save(SellerEntity.builder()
//                    .adminUser("Admin")
//                    .createOn(new Date())
//                    .product("Product 2")
//                    .sellerName("Pety")
//                    .quota(111)
//                    .expiresOn(new Date())
//                    .password("********")
//                    .build()));
//        };
//    }
}
