package io.gbloch.mower.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Test;

class MowingHandlerTest {
    private static final String EXPECTED_RESULT =
            """
            1 3 N
            5 1 E""";

    private MowingHandler mowingHandler;

    @Test
    void when_fileInputIsCorrect_then_returnCorrectMowingOutput() throws IOException {
        // Given
        URL url = this.getClass().getResource("/sample.txt");
        mowingHandler = new MowingHandler(url.getPath());

        // When
        String result = mowingHandler.handle();

        // Then
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }
}
