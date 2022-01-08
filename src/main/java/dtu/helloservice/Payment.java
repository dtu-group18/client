package dtu.helloservice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Simple class representing a payment
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    public String costumerId, merchantId, amount;

}



