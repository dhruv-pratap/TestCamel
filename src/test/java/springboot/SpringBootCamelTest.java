package springboot;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCamel.class, webEnvironment = DEFINED_PORT)
public class SpringBootCamelTest {

    private static final String ACCOUNT_ID = "123";

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void testHello() throws Exception {


        given()
                .get("/hello?accountId={accountId}", ACCOUNT_ID)
                .then()
                .statusCode(200);
    }

    //@Test
    public void testDelay1() throws Exception {

        given()
                .get("/delay1?accountId={accountId}", ACCOUNT_ID)
                .then()
                .statusCode(200);
    }

}
