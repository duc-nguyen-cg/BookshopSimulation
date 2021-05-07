package IOTask;

import basicClass.*;
import java.util.*;

public interface IOTaskWithItem {
    String FILE_ERROR_MESSAGE = "The file is missing, cannot be opened or cannot be used!";

    void write(List<Item> list, String filepath);

    List<Item> read(List<Item> list, String filepath);
}
