package io.gbloch.mower;

import io.gbloch.mower.handler.MowingHandler;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Error: You must provide a field configuration file.");
      return;
    }

    // The first argument of the command line is the path
    // to the field configuration file
    String filePath = args[0];
    System.out.println("File path: " + filePath);
    MowingHandler mowingHandler = new MowingHandler(filePath);
    try {
      System.out.println(mowingHandler.handle());
    } catch (IOException e) {
      System.err.println("Error while calculating the mower positions: " + e.getMessage());
    }
  }
}
