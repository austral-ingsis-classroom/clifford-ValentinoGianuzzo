package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.*;

public class CommandFactory {
  private FileSystem fileSystem;

  public CommandFactory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  public Command createCommand(String command) {
    String[] parts = command.split(" ");
    switch (parts[0]) {
      case "ls":
        return new ListDirectory(fileSystem);
      case "mkdir":
        return new MKDir(fileSystem);
      case "touch":
        return new Touch(fileSystem);
      case "cd":
        return new ChangeDirectory(fileSystem);
      case "rm":
        return new Remove(fileSystem);
      case "pwd":
        return new Pwd(fileSystem);
      default:
        throw new IllegalArgumentException("Unknown command: " + parts[0]);
    }
  }
}
