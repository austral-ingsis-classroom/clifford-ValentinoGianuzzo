package edu.austral.ingsis.clifford;

import java.util.List;

public interface FileSystem {
  String changeDirectory(String path);

  void createFile(String name);

  void createDirectory(String name);

  void remove(String name, boolean recursive);

  String getCurrentPath();

  String printWorkingDirectory();

  List<String> listDirectory();
}
