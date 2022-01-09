package dtu.helloservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * Simple class representing a person
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String customerId;
    private String name;
    private String cpr;
    private String bankAccount;


    /**
     * Set name, cpr, bankAccount as optionals
     *
     * @param customerId
     */
    @Tolerate
    public Customer(String customerId) {
        this.customerId = customerId;
    }
}


