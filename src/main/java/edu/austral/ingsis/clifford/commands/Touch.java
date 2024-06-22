package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;

public class Touch implements Command {
  private FileSystem fileSystem;

  public Touch(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length > 1) {
      String fileName = args[1];
      fileSystem.createFile(fileName);
      return "'" + fileName + "' file created";
    }
    return "No file name specified";
  }
}
