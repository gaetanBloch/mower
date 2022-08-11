package io.gbloch.mower.parser;

import static org.assertj.core.api.Assertions.assertThat;

import io.gbloch.mower.utils.FieldConfiguration;
import io.gbloch.mower.utils.Orientation;
import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Test;

class FieldConfigurationFileParserTest {
  private FieldConfigurationFileParser fieldConfigurationFileParser;

  @Test
  void when_fileParsed_then_configurationIsCorrect() throws IOException {
    // Given
    URL url = this.getClass().getResource("/sample.txt");
    fieldConfigurationFileParser = new FieldConfigurationFileParser(url.getPath());

    // When
    FieldConfiguration configuration = fieldConfigurationFileParser.parseConfiguration();

    // Then
    assertThat(configuration.height()).isEqualTo(5);
    assertThat(configuration.length()).isEqualTo(5);
    assertThat(configuration.instructions().get(0).initialPosition().getX()).isEqualTo(1);
    assertThat(configuration.instructions().get(0).initialPosition().getY()).isEqualTo(2);
    assertThat(configuration.instructions().get(0).movements()).isEqualTo("GAGAGAGAA");
    assertThat(configuration.instructions().get(0).initialPosition().getOrientation())
            .isEqualTo(Orientation.N);
    assertThat(configuration.instructions().get(1).initialPosition().getX()).isEqualTo(3);
    assertThat(configuration.instructions().get(1).initialPosition().getY()).isEqualTo(3);
    assertThat(configuration.instructions().get(1).movements()).isEqualTo("AADAADADDA");
    assertThat(configuration.instructions().get(1).initialPosition().getOrientation())
            .isEqualTo(Orientation.E);
  }
}
