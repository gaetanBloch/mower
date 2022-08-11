package io.gbloch.mower.handler;

import io.gbloch.mower.parser.FieldConfigurationFileParser;
import io.gbloch.mower.utils.FieldConfiguration;
import io.gbloch.mower.utils.MovementType;
import io.gbloch.mower.utils.MowerInstruction;
import io.gbloch.mower.utils.MowerPosition;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Handle the movements and positions of the mowers based on the configuration
 * file and the instructions
 */
public final class MowingHandler {
  private final FieldConfigurationFileParser fieldConfigurationFileParser;

  public MowingHandler(String filePath) {
    this.fieldConfigurationFileParser = new FieldConfigurationFileParser(filePath);
  }

  public String handle() throws IOException {
    FieldConfiguration configuration = fieldConfigurationFileParser.parseConfiguration();
    List<String> results = new LinkedList<>();
    configuration
        .instructions()
        .forEach(
            instruction -> {
              MowerPosition position = calculatePosition(instruction, configuration);
              results.add(String.format("%s %s %s", position.getX(), position.getY(), position.getOrientation()));
            });
    return String.join("\n", results);
  }

  private MowerPosition calculatePosition(MowerInstruction instruction, FieldConfiguration configuration) {
      MowerPosition position = instruction.initialPosition();
      instruction
              .movements()
              .chars()
              .mapToObj(item -> (char) item)
              .toList()
              .forEach(movement -> moveMower(position, configuration, movement));
      return position;
  }

    /**
     * Change the current position or orientation of the mower based on the movement type
     */
  private void moveMower(MowerPosition position, FieldConfiguration configuration, Character movement) {
      MovementType movementType = MovementType.fromChar(movement);
      // Change the orientation of the mower if we rotate to right (D) or left (G)
      position.setOrientation(position.getOrientation().changeOrientation(movementType));
      if (movementType == MovementType.A) {
          // Since we need to advance (A) one position we need to do it based on the current orientation
          switch (position.getOrientation()) {
              case N -> {
                  if(position.getY() < configuration.height()) {
                      position.setY(position.getY() + 1);
                  }
              }
              case E -> {
                  if(position.getX() < configuration.length()) {
                      position.setX(position.getX() + 1);
                  }
              }
              case S -> {
                  if(position.getY() > 0) {
                      position.setY(position.getY() - 1);
                  }
              }
              case W -> {
                  if(position.getX() > 0) {
                      position.setX(position.getX() - 1);
                  }
              }
          }
      }
  }
}
