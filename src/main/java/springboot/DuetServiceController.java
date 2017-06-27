package springboot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springboot.model.IssueAuthTokenRequest;
import springboot.model.IssueAuthTokenResponse;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by dhruvpratap on 6/26/17.
 */
@RestController
public class DuetServiceController {

    private static final String PARTNER_USER_ID = "DuetTestUser";
    private static final String PARTNER_USER_ID_WITH_EXPIRED_TOKEN = "DuetTestUserWithExpiredToken";


    @RequestMapping(value= "tokenservice/issueAuthToken", method = POST, consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public IssueAuthTokenResponse issueAuthToken (@RequestParam(value="partnerUserId") String partnerUserId ,
                                                  @RequestBody @Valid IssueAuthTokenRequest issueAuthTokenRequest){


        IssueAuthTokenResponse issueAuthTokenResponse = new IssueAuthTokenResponse();
        if(partnerUserId.equals(PARTNER_USER_ID)){

            issueAuthTokenResponse.setAuthToken("{oauth}TestAuthToken@Duet_BCUS");
            issueAuthTokenResponse.setBcusAccountId("1234");
            issueAuthTokenResponse.setResponseMessage("AUTH_TOKEN_SUCCESS");
        }

        else
            issueAuthTokenResponse.setResponseMessage("AUTH_TOKEN_EXPIRED");
        return issueAuthTokenResponse;

    }
}
