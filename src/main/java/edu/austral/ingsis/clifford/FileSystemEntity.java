package edu.austral.ingsis.clifford;

public abstract class FileSystemEntity {
  protected String name;
  protected Directory parent;

  public FileSystemEntity(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public Directory getParent() {
    return parent;
  }
}
