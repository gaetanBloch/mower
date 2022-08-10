package io.gbloch.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MowerHandlerTest {
    private static final String SAMPLE = """
            5 5
            1 2 N
            GAGAGAGAA
            3 3 E
            AADAADADDA
            """;

    private static final String EXPECTED_RESULT = """
            1 3 N
            5 1 E
            """;

    private MowerHandler mowerHandler;

    @Test
    void handleMowingTest() {
        // Given
        mowerHandler = new MowerHandler();

        // When
        String result = mowerHandler.handle(SAMPLE);

        // Then
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }
}
