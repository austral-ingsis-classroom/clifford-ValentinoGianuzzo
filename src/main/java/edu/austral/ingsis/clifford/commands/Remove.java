package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;

public class Remove implements Command {
  private FileSystem fileSystem;

  public Remove(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    boolean recursive = false;
    String name;

    if (args.length > 2 && args[1].equals("--recursive")) {
      recursive = true;
      name = args[2];
    } else if (args.length > 1) {
      name = args[1];
    } else {
      return "No file or directory specified";
    }

    try {
      fileSystem.remove(name, recursive);
      return "'" + name + "' removed";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
