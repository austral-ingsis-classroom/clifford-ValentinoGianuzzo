package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.FileSystem;
import java.util.Comparator;
import java.util.List;

public class ListDirectory implements Command {
  private FileSystem fileSystem;

  public ListDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    List<String> contents = fileSystem.listDirectory();

    if (args.length > 1) {
      if (args[1].equals("--ord=asc")) {
        contents.sort(String::compareTo);
      } else if (args[1].equals("--ord=desc")) {
        contents.sort(Comparator.reverseOrder());
      }
    }
    // No need to sort if no order is specified, keep the elements in the order they arrived

    return String.join(" ", contents);
  }
}
