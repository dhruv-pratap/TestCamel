
package springboot;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springboot.model.IssueAuthTokenRequest;

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootCamelTest {

    private static final String ACCOUNT_ID = "123";
    private static final String PARTNER_USER_ID = "DuetTestUser";
    private static final String PARTNER_USER_ID_WITH_EXPIRED_TOKEN = "DuetTestUserWithExpiredToken";

    public RequestSpecification spec;

    @Value("${local.server.port}")
    public int port;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        RestAssured.port = port;
        this.spec = new RequestSpecBuilder().addFilter(
                documentationConfiguration(this.restDocumentation))
                .build();

    }

    @Test
    public void testHello() throws Exception {

        RestAssured.given(this.spec)
                .filter(RestAssuredRestDocumentation.document("hello"))
                .accept("application/json")
                .get("/")
                .then()
                .statusCode(200);
    }


    @Test
    public void testDelay1() throws Exception {
        String accountId = "1234";
        RestAssured.given(this.spec)
                .filter(RestAssuredRestDocumentation.document("delay1"))
                .accept("application/json")
                .get("/delay1?accountId={accountId}",  accountId)
                .then()
                .statusCode(200);
    }

    @Test
    public void issueAuthToken_Success(){

        IssueAuthTokenRequest issueAuthTokenRequest = getIssueAuthTokenRequest();

        RestAssured.given(this.spec)
                .filter(RestAssuredRestDocumentation.document("duet_valid"))
                .contentType("application/json")
                .body(issueAuthTokenRequest)
                .when()
                .post("/issueAuthToken?partnerUserId={partnerUserId}",PARTNER_USER_ID_WITH_EXPIRED_TOKEN )
                .then()
                .statusCode(201);

    }

    private IssueAuthTokenRequest getIssueAuthTokenRequest() {
        IssueAuthTokenRequest issueAuthTokenRequest = new IssueAuthTokenRequest();
        issueAuthTokenRequest.setPartnerdeviceIdentifier("duetUserDeviceId");
        issueAuthTokenRequest.setPartnerIPAddress("testIpAddress");
        issueAuthTokenRequest.setAccountNumber("3214534567834231");
        issueAuthTokenRequest.setUserFraudScore("720");
        return issueAuthTokenRequest;
    }

    @Test
    public void issueAuthToken_ExpiredToken(){

        IssueAuthTokenRequest issueAuthTokenRequest = getIssueAuthTokenRequest();

        RestAssured.given(this.spec)
                .filter(RestAssuredRestDocumentation.document("duet_invalid"))
                .contentType("application/json")
                .body(issueAuthTokenRequest)
                .when()
                .post("tokenservice/issueAuthToken?partnerUserId={partnerUserId}",PARTNER_USER_ID )
                .then()
                .statusCode(201);

    }

}

