package pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import client.ConsumerClient;
import configuration.Constants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public class GetAllRecipesTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2(Constants.API_PROVIDER_NAME, Constants.MOCK_HOST, Constants.MOCK_HOST_PORT, this);

    @Pact(consumer = Constants.API_CONSUMER_NAME)
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("a Bay Cakes API provider with food receipts")
                .uponReceiving("a request to get all existing receipts")
                .path("/recipe")
                .method("GET")
                .headers(Constants.JWT_HEADER_NAME, Constants.JWT_HEADER_VALUE)
                .willRespondWith()
                .status(200)
                .body("[{\"id\":\"59881440c0d275035ac8817f\",\"recipeName\":\"cake\",\"ingredients\":[\"egg\",\"milk\"]}]")
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() throws IOException {
        String expectedResponse = constructExpectedResponse();
        Assert.assertEquals(new ConsumerClient(mockProvider.getUrl()).get("/recipe"), expectedResponse);
    }

    private String constructExpectedResponse() {
        return "[{\"id\":\"59881440c0d275035ac8817f\",\"recipeName\":\"cake\",\"ingredients\":[\"egg\",\"milk\"]}]";
    }

}

