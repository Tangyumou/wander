package cn.ebbandflow.taco.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class Order {
    @NotBlank(message = "name must not be blank")
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    @CreditCardNumber
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
