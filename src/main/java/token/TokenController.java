package token;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Nguyen on 5/31/16.
 */

@RestController
public class TokenController {

    private final static Logger LOGGER = Logger.getLogger(TokenController.class.getName());

    @RequestMapping(value = "/processToken",method = RequestMethod.POST)
    public ResponseEntity<Token> processToken(@RequestBody Token token) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {

        Stripe.apiKey = "sk_test_8nznmpwTfFnnjgBlcfxqyhgR";

        try{
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", token.getPrice()); // amount in cents, again
            chargeParams.put("currency", "usd");
            chargeParams.put("source", token.getToken());
            chargeParams.put("description", "Example charge");

            Charge charge = Charge.create(chargeParams);
        }catch(CardException e){
            LOGGER.log(Level.SEVERE, "Token Payment Process Error");

        }

        return new ResponseEntity<Token>(token, HttpStatus.OK);
    }
}
