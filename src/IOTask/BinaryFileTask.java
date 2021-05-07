package IOTask;

import basicClass.*;
import java.io.*;
import java.util.*;


public class BinaryFileTask implements IOTaskWithItem{
    public static final String BINARY_FILEPATH_REGEX = "^([0-9a-zA-Z/]*)[0-9a-zA-Z]+\\.txt$";

    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    //set up singleton
    private static final BinaryFileTask binaryFileTask = new BinaryFileTask();
    private BinaryFileTask() {}
    public static BinaryFileTask getInstance(){
        return binaryFileTask;
    }


    @Override
    public void write(List<Item> list, String filepath){
        try {
            fos = new FileOutputStream(filepath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
            System.out.println("Export data into "+filepath+" successfully!");
        } catch (IOException e){
            System.err.println(FILE_ERROR_MESSAGE);
        }
    }


    @Override
    public List<Item> read(List<Item> originalList, String filepath){
        List<Item> newList;
        try {
            fis = new FileInputStream(filepath);
            ois = new ObjectInputStream(fis);
            newList = (ArrayList<Item>) ois.readObject();
            originalList.addAll(newList);
            ois.close();
            fis.close();
            System.out.println("Import data from "+filepath+" successfully!");
        } catch (IOException | ClassCastException e){
            System.err.println(FILE_ERROR_MESSAGE);
        } catch (ClassNotFoundException e){
            System.err.println("Class Item is missing!");
        }
        return originalList;
    }
}
