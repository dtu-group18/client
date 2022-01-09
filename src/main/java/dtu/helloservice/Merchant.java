package dtu.helloservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * Simple class representing a merchant
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private String merchantId;
    private String name;
    private String cpr;
    private String bankAccount;

    /**
     * Set name, cpr, bankAccount as optionals
     *
     * @param merchantId
     */
    @Tolerate
    public Merchant(String merchantId) {
        this.merchantId = merchantId;
    }
}
