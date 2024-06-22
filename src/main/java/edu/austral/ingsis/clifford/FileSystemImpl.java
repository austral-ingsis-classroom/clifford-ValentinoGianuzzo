package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class FileSystemImpl implements FileSystem {
  private Directory currentDirectory;

  public FileSystemImpl(Directory rootDirectory) {
    this.currentDirectory = rootDirectory;
  }

  @Override
  public String changeDirectory(String path) {
    if (path.equals("..")) {
      Directory parentDirectory = currentDirectory.getParent();
      if (parentDirectory != null) {
        currentDirectory = parentDirectory;
        return getCurrentPath();
      } else {
        return "/"; // Return "/" instead of ".."
      }
    } else if (path.equals("/")) {
      while (currentDirectory.getParent() != null) {
        currentDirectory = currentDirectory.getParent();
      }
      return getCurrentPath();
    } else if (path.contains("/")) {
      String[] directories = path.split("/");
      Directory newDirectory = currentDirectory;
      for (String directory : directories) {
        newDirectory = newDirectory.getSubdirectory(directory);
        if (newDirectory != null) {
          currentDirectory = newDirectory;
        } else {
          return "'" + directory + "' directory does not exist";
        }
      }
      return getCurrentPath();
    } else {
      Directory newDirectory = currentDirectory.getSubdirectory(path);
      if (newDirectory != null) {
        currentDirectory = newDirectory;
        return getCurrentPath();
      } else {
        return "'" + path + "' directory does not exist";
      }
    }
  }

  @Override
  public void createFile(String name) {
    File newFile = new File(name, currentDirectory);
    currentDirectory.addFile(newFile);
  }

  @Override
  public void createDirectory(String name) {
    Directory newDirectory = new Directory(name, currentDirectory);
    currentDirectory.addDirectory(newDirectory);
  }

  @Override
  public void remove(String name, boolean recursive) {
    FileSystemEntity entity = currentDirectory.find(name);
    if (entity == null) {
      throw new IllegalArgumentException("File or directory not found: " + name);
    }
    if (entity instanceof Directory && !recursive) {
      throw new IllegalArgumentException("cannot remove '" + name + "', is a directory");
    }
    currentDirectory.remove(entity);
  }

  @Override
  public String getCurrentPath() {
    String path = currentDirectory.getPath();
    if (path.startsWith("/")) {
      path = path.substring(1);
    }
    return path;
  }

  public String printWorkingDirectory() {
    return currentDirectory.getPath();
  }

  @Override
  public List<String> listDirectory() {
    List<String> entities = new ArrayList<>();
    for (FileSystemEntity child : currentDirectory.getChildren()) {
      entities.add(child.getName());
    }
    return entities;
  }
}
