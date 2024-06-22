package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;

public class Pwd implements Command {
  private FileSystem fileSystem;

  public Pwd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    String currentDirectory = fileSystem.printWorkingDirectory();
    return currentDirectory;
  }
}
