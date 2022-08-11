package io.gbloch.mower.parser;

import io.gbloch.mower.utils.FieldConfiguration;
import io.gbloch.mower.utils.MowerInstruction;
import io.gbloch.mower.utils.MowerPosition;
import io.gbloch.mower.utils.Orientation;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class FieldConfigurationFileParser {

  private final File file;

  public FieldConfigurationFileParser(String filePath) {
    this.file = new File(filePath);
  }

  public FieldConfiguration parseConfiguration() throws IOException {
    int i = 0;
    int length = 0;
    int height = 0;
    List<MowerInstruction> instructions = new LinkedList<>();
    try (Scanner scanner = new Scanner(file)) {
      if (scanner.hasNextLine()) {
        // Handle the surface dimensions of the field
        // We consume the first line of the file and get the surface
        String firstLine = scanner.nextLine();
        length = Integer.parseInt(firstLine.charAt(0) + "");
        // We skip the space in between
        height = Integer.parseInt(firstLine.charAt(2) + "");
      }
      while (scanner.hasNextLine()) {
        if (scanner.hasNextInt()) {
          // This check is to ensure that the next character is an integer
          // (skipping spaces and returns)
          int initialX = scanner.nextInt();
          int initialY = scanner.nextInt();
          // Patterns (regex) could be extracted for only one time compilation
          String initialOrientation = scanner.next("[NESW]");
          String movements = scanner.next("[DGA]*");
          instructions.add(
              new MowerInstruction(
                  new MowerPosition(initialX, initialY, Orientation.valueOf(initialOrientation)),
                  movements));
        } else {
          // Breaking the loop is not always the best solution especially
          // for debugging but in this case, it is pretty obvious
          // since we don't want to continue to read a file that is not properly formatted
          break;
        }
      }
      return new FieldConfiguration(length, height, instructions);
    }
  }
}
