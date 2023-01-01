package org.vodafone;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vodafone.http.api.SearchApi;
import org.vodafone.model.SearchDataResponseModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class SearchTest extends BasicTest {
    @DataProvider
    public Object[][] searchData() {
        return new Object[][]{
                {"Послуги"},
                {"Тарифи"}
        };
    }

    @Test(dataProvider = "searchData")
    public void verifyUserGetSearchDataResult(String phrase) {
        log(1, String.format("Verify www.vodafone.ua/api/search/%s/page=1", phrase));
        verifySearchPageResult(phrase, 1);

        log(2, String.format("Verify www.vodafone.ua/api/search/%s/page=2", phrase));
        verifySearchPageResult(phrase, 2);
    }

    private void verifySearchPageResult(String phrase, int page) {
        Response response = new SearchApi()
                .getSearchResult(phrase, page);

        SearchDataResponseModel model = response.as(SearchDataResponseModel.class);

        verifyStatusCode(response, 200);
        assertThat("Data shouldn't be null", model.getData(), notNullValue());
        assertThat("Data should has size 10", model.getData(), hasSize(10));
        model.getData().forEach(m -> {
            assertThat("Id should not be null", m.getId(), notNullValue());
            assertThat("Title should not be null", m.getTitle(), notNullValue());
            assertThat("Short Description should not be null", m.getShortDescription(), notNullValue());
            assertThat("Url should not be null", m.getUrl(), notNullValue());
            assertThat("Breadcrumbs should not be null", m.getBreadcrumbs(), notNullValue());
        });
    }

    @Test
    public void verifyUserCannotGetSearchDataWithoutPhrase() {
        log("Verify www.vodafone.ua/api/search/%s/page=1");

        Response response = new SearchApi()
                .getSearchResult("", 1);

        verifyStatusCode(response, 404);
    }
}
