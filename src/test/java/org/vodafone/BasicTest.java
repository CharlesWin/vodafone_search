package org.vodafone;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public abstract class BasicTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger("com.vodafone");

    protected void log(String value) {
        LOGGER.info(value);
    }

    protected void log(int step, String... value) {
        LOGGER.info(String.format("Step %d: %s", step, String.join(". ", value)));
    }

    protected void verifyStatusCode(Response response, int expectedStatusCode) {
        assertThat(String.format("Status code should be '%s'", expectedStatusCode),
                response.statusCode(), equalTo(expectedStatusCode));
    }
}
