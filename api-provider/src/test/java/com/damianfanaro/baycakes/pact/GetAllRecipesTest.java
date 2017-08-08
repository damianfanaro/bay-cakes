package com.damianfanaro.baycakes.pact;

import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@RunWith(PactRunner.class)
@Provider("bay-cakes-api-provider")
@PactFolder("pacts")
@IgnoreNoPactsToVerify
public class GetAllRecipesTest {

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @State("a Bay Cakes API provider with food receipts")
    public void aBayCakesApiProviderWithFoodReceipts() {
        System.out.println("Now service in default state");
    }

}
