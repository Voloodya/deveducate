package ru.cinimex.deveducate.configuration;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.rest.dto.SellerDto;

@Component
public class ConfigurableMapperOrika extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(CustomerEntity.class, CustomerDto.class)
                .field("customerId","id")
                .field("custFirstName","firstName")
                .field("custLastName","lastName")
                .field("phoneNumber1","phone1")
                .field("phoneNumber2","phone2")
                .field("custStreetAddress1","street1")
                .field("custStreetAddress2","street2")
                .field("custCity","city")
                .field("custPostalCode","postal")
                .field("custEmail","email")
                .register();
        factory.classMap(SellerEntity.class, SellerDto.class)
                .field("sellerId","id")
                .field("sellerName","name")
                .field("expiresOn","updateOn")
                .register();
        factory.classMap(SellerDto.class, SellerEntity.class)
                .field("id","sellerId")
                .field("name","sellerName")
                .field("updateOn", "expiresOn")
                .register();
        factory.classMap(OrderEntity.class, OrderDto.class)
                .field("orderId","id")
                .register();
    }

}
