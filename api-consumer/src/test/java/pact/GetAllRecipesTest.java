package pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import httpclient.ConsumerClient;
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

    private static final String API_PROVIDER_NAME = "all-recipes-provider";
    private static final String API_CONSUMER_NAME = "all-recipes-consumer";

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2(API_PROVIDER_NAME, Constants.MOCK_HOST, Constants.MOCK_HOST_PORT, this);

    @Pact(consumer = API_CONSUMER_NAME)
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("a Bay Cakes API provider with food recipes")
                .uponReceiving("a request to get all existing recipes")
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
        ConsumerClient consumerClient = new ConsumerClient(mockProvider.getUrl());
        Assert.assertEquals(consumerClient.get("/recipe"), expectedResponse);
    }

    private String constructExpectedResponse() {
        return "[{\"id\":\"59881440c0d275035ac8817f\",\"recipeName\":\"cake\",\"ingredients\":[\"egg\",\"milk\"]}]";
    }

}

