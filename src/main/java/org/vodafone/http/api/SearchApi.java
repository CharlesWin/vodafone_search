package org.vodafone.http.api;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;
import org.vodafone.http.Requester;

import java.util.Map;

public class SearchApi extends BaseApi<SearchApi> {
    public static final String VODAFONE_BASE_URL = "https://www.vodafone.ua/api";
    public static final String SEARCH = VODAFONE_BASE_URL + "/search";
    public static final String SEARCH_WITH_PHRASE = SEARCH + "/{phrase}";
    public static final String SEARCH_AUTOCOMPLETE = SEARCH + "/autocomplete";
    public static final String SEARCH_AUTOCOMPLETE_WITH_PHRASE = SEARCH_AUTOCOMPLETE + "/{phrase}";

    public static final String PAGE_QUERY_PARAM = "page";

    public static final String PHRASE_PATH_PARAM = "phrase";

    public Response getSearchAutocomplete(String phrase) {
        requestSpecBuilder.addPathParam(PHRASE_PATH_PARAM, phrase);
        return new Requester()
                .sendGetRequest(buildRequestSpecifications(), SEARCH_AUTOCOMPLETE_WITH_PHRASE);
    }

    public Response getSearchResult(String phrase, int page) {
        Map<String, Integer> queryParams = ImmutableMap.<String, Integer>builder()
                .put(PAGE_QUERY_PARAM, page).build();
        addQueryParams(queryParams);
        requestSpecBuilder.addPathParam(PHRASE_PATH_PARAM, phrase);
        return new Requester()
                .sendGetRequest(buildRequestSpecifications(), SEARCH_WITH_PHRASE);
    }

    @Override
    protected SearchApi instance() {
        return this;
    }
}
