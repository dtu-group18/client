package dtu.helloservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple class representing a merchant
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private String merchantId;
}
