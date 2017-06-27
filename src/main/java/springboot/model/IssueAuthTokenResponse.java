package springboot.model;

/**
 * Created by dhruvpratap on 6/26/17.
 */
public class IssueAuthTokenResponse {

    private String responseMessage;
    private String bcusAccountId;
    private String authToken;


    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getBcusAccountId() {
        return bcusAccountId;
    }

    public void setBcusAccountId(String bcusAccountId) {
        this.bcusAccountId = bcusAccountId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
