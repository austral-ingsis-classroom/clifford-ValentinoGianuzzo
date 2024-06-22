package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Directory extends FileSystemEntity {
  private LinkedHashSet<FileSystemEntity> children;

  public Directory(String name, Directory parent) {
    super(name, parent);
    this.children = new LinkedHashSet<>();
  }

  public List<FileSystemEntity> getChildren() {
    return new ArrayList<>(children);
  }

  public void addDirectory(Directory newDirectory) {
    children.add(newDirectory);
    System.out.println("Added directory: " + newDirectory.getName() + " to " + this.getName());
  }

  public void addFile(File newFile) {
    children.add(newFile);
    System.out.println("Added file: " + newFile.getName() + " to " + this.getName());
  }

  public void remove(FileSystemEntity entity) {
    children.remove(entity);
  }

  public String getPath() {
    if (getParent() == null) {
      return "/";
    }
    String parentPath = getParent().getPath();
    if (parentPath.equals("/")) {
      return parentPath + getName();
    } else {
      return parentPath + "/" + getName();
    }
  }

  public String getDirectoryName() {
    return getName();
  }

  public FileSystemEntity find(String name) {
    for (FileSystemEntity child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  public Directory getSubdirectory(String path) {
    String[] parts = path.split("/", 2);
    for (FileSystemEntity child : children) {
      if (child instanceof Directory && child.getName().equals(parts[0])) {
        if (parts.length > 1) {
          // If the path contains "/", search in the subdirectories
          Directory subdirectory = ((Directory) child).getSubdirectory(parts[1]);
          if (subdirectory == null) {
            System.out.println(
                "Subdirectory '" + parts[1] + "' not found in '" + child.getName() + "'");
          }
          return subdirectory;
        } else {
          return (Directory) child;
        }
      }
    }
    System.out.println("Directory '" + parts[0] + "' not found");
    return null;
  }
}
