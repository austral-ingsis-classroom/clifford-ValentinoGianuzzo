package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;

public class ChangeDirectory implements Command {
  private FileSystem fileSystem;

  public ChangeDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length > 1) {
      String directoryName = args[1];
      String result = fileSystem.changeDirectory(directoryName);
      if (result.contains("does not exist")) {
        return result;
      } else if (result.equals("/")) {
        return "moved to directory '/'";
      } else if (result.equals("")) {
        return "moved to directory '/'"; // Return "moved to directory '/'" instead of "moved to
        // directory ''"
      } else if (result.contains("'") && result.contains("directory does not exist")) {
        return result;
      } else {
        String[] pathParts = result.split("/");
        return "moved to directory '" + pathParts[pathParts.length - 1] + "'";
      }
    }
    return "No directory specified";
  }
}
