package com.damianfanaro.baycakes.pact;

import au.com.dius.pact.provider.junit.*;
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
@RunWith(RestPactRunner.class)
@Provider("all-recipes-service")
@PactFolder("pacts")
@IgnoreNoPactsToVerify
@VerificationReports("markdown")
public class GetAllRecipesTest {

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @State("a Bay Cakes API provider with food recipes")
    public void aBayCakesApiProviderWithFoodRecipes() {
        System.out.println("Now service in default state");
    }

}
