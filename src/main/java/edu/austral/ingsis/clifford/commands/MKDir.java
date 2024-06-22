package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;

public class MKDir implements Command {
  private FileSystem fileSystem;

  public MKDir(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Missing directory name");
    }
    String dirName = args[1];
    fileSystem.createDirectory(dirName);
    return "'" + dirName + "' directory created";
  }
}
