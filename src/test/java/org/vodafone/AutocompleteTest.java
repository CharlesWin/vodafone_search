package org.vodafone;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vodafone.http.api.SearchApi;
import org.vodafone.model.SearchAutocompleteResponseModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutocompleteTest extends BasicTest {
    @Test
    public void verifyUserGetAutocompleteSearchDataInRelativeOrder() {
        log("Verify www.vodafone.ua/api/search/autocomplete/vodafone");

        Response response = new SearchApi()
                .getSearchAutocomplete("vodafone");

        SearchAutocompleteResponseModel model = response.as(SearchAutocompleteResponseModel.class);

        verifyStatusCode(response, 200);
        assertThat("Data shouldn't be null", model.getData(), notNullValue());
        assertThat("Data should has size 3", model.getData(), hasSize(3));
        assertThat("Data should be in relative order", model.getData(), containsInRelativeOrder(
                "Vodafone Gigabit Net", "Vodafone Safety", "Vodafone Smart Routes"));
    }

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"123"},
                {""}
        };
    }

    @Test(dataProvider = "data")
    public void verifyUserGetAutocompleteSearchDataIsEmpty(String phrase) {
        log(String.format("Verify www.vodafone.ua/api/search/autocomplete/%s", phrase));

        Response response = new SearchApi()
                .getSearchAutocomplete(phrase);

        SearchAutocompleteResponseModel model = response.as(SearchAutocompleteResponseModel.class);

        verifyStatusCode(response, 200);
        assertThat("Data shouldn't be null", model.getData(), notNullValue());
        assertThat("Data should be empty", model.getData(), empty());
    }
}
