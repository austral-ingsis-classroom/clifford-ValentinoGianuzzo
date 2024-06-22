package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cli {
  private Map<String, Command> commands;

  public Cli(FileSystem fileSystem) {
    commands = new HashMap<>();
    commands.put("ls", new ListDirectory(fileSystem));
    commands.put("cd", new ChangeDirectory(fileSystem));
    commands.put("mkdir", new MKDir(fileSystem));
    commands.put("touch", new Touch(fileSystem));
    commands.put("rm", new Remove(fileSystem));
    commands.put("pwd", new Pwd(fileSystem));
  }

  public void runCommand(String input) {
    String[] parts = input.split(" ");
    String commandName = parts[0];
    String[] args = Arrays.copyOfRange(parts, 1, parts.length);
    Command command = commands.get(commandName);
    if (command != null) {
      command.execute(args);
    } else {
      System.out.println("Unknown command: " + commandName);
    }
  }
}
