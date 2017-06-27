package springboot.model;

/**
 * Created by dhruvpratap on 6/26/17.
 */
public class IssueAuthTokenRequest {

    private String accountNumber;
    private String partnerdeviceIdentifier;
    private String partnerIPAddress;
    private String userFraudScore;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPartnerdeviceIdentifier() {
        return partnerdeviceIdentifier;
    }

    public void setPartnerdeviceIdentifier(String partnerdeviceIdentifier) {
        this.partnerdeviceIdentifier = partnerdeviceIdentifier;
    }

    public String getPartnerIPAddress() {
        return partnerIPAddress;
    }

    public void setPartnerIPAddress(String partnerIPAddress) {
        this.partnerIPAddress = partnerIPAddress;
    }

    public String getUserFraudScore() {
        return userFraudScore;
    }

    public void setUserFraudScore(String userFraudScore) {
        this.userFraudScore = userFraudScore;
    }
}
