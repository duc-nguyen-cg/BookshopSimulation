package IOTask;

import basicClass.*;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.*;

import static basicClass.Item.*;

//impossible to clean code lol

public class CSVFileTask implements IOTaskWithItem{
    public static final String CSV_FILEPATH_REGEX = "^([0-9a-zA-Z/]*)[0-9a-zA-Z]+\\.csv$";
    public static final String COMMA = ",";

    private final int ID_COL = 0, NAME_COL = 1, QUANTITY_COL = 2, PRICE_COL = 3, PUBLISHER_COL = 4, AUTHOR_COL = 5, PAGE_NUM_COL = 6,
             CATEGORY_COL = 7, NO_COL = 8, MONTH_COL = 9, DATE_COL = 10, PRODUCER_COL = 11, TYPE_COL = 12 ;


    private BufferedWriter writer;
    private BufferedReader reader;

    //set up singleton
    private static final CSVFileTask csvFileTask = new CSVFileTask();
    private CSVFileTask() {}
    public static CSVFileTask getInstance(){
        return csvFileTask;
    }


    @Override
    public void write(List<Item> list, String filepath) {
        File file = new File(filepath);
        try {
            writer = new BufferedWriter(new FileWriter(file));
            //write header
            writer.write("ID, Name, Quantity, Price, Publisher, Author, Number of pages, Category, No, Released Month, Released Date, Producer, Type\n");

            //write content
            Iterator<Item> iterator = list.iterator();
            while (iterator.hasNext()){
                Item item = iterator.next();
                writer.write(item.getId() + COMMA + item.getName() + COMMA + item.getQuantity() + COMMA +  item.getPrice() + COMMA);
                if (item instanceof Book){
                    writer.write(((Book) item).getPublisher()+ COMMA +((Book) item).getAuthor()+ COMMA +((Book) item).getPageNum()+ COMMA +((Book) item).getCategory());
                } else if (item instanceof Magazine){
                    writer.write(((Magazine) item).getPublisher()+", , , ,"+((Magazine) item).getNo()+ COMMA +((Magazine) item).getReleaseMonth());
                } else if (item instanceof Newspaper){
                    writer.write(((Newspaper) item).getPublisher()+", , , , , ,"+((Newspaper) item).getReleaseDate());
                } else if (item instanceof Stationery){
                    writer.write(" , , , , , , ,"+((Stationery) item).getProducer()+ COMMA +((Stationery) item).getType());
                }
                writer.write("\n");
            }

            //close file and inform
            writer.close();
            System.out.println("Write data into "+ filepath+" successfully!");
        } catch (IOException e) {
            System.err.println(FILE_ERROR_MESSAGE);
        }
    }


    @Override
    public List<Item> read(List<Item> originalList, String filepath){
        File file = new File(filepath);
        try {
            if (!file.exists()){
                throw new FileNotFoundException();
            }
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            List<Item> newList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(COMMA);
                //skip the header
                if (row[ID_COL].equals("ID")){
                    continue;
                }
                //read content, then add to newList
                Item newItem = null;
                String id = row[ID_COL].trim();
                String name = row[NAME_COL].trim();
                int quantity = Integer.parseInt(row[QUANTITY_COL].trim());
                double price = Double.parseDouble(row[PRICE_COL].trim());

                if (id.matches(BOOK_ID_REGEX)){
                    String publisher = row[PUBLISHER_COL].trim();
                    String author = row[AUTHOR_COL].trim();
                    int pageNum = Integer.parseInt(row[PAGE_NUM_COL].trim());
                    String category = row[CATEGORY_COL].trim();
                    newItem = new Book(name, quantity, price, publisher, author, pageNum, category);
                    newItem.setId(id);

                } else if (id.matches(MAGAZINE_ID_REGEX)){
                    String publisher = row[PUBLISHER_COL].trim();
                    int no = Integer.parseInt(row[NO_COL].trim());
                    String month = row[MONTH_COL].trim();
                    newItem = new Magazine(name, quantity, price, publisher, no, month);
                    newItem.setId(id);

                } else if (id.matches(NEWSPAPER_ID_REGEX)){
                    String publisher = row[PUBLISHER_COL].trim();
                    String date = row[DATE_COL].trim();
                    newItem = new Newspaper(name, quantity, price, publisher, date);
                    newItem.setId(id);

                } else if (id.matches(STATIONERY_ID_REGEX)){
                    String producer = row[PRODUCER_COL].trim();
                    String type = row[TYPE_COL].trim();
                    newItem = new Stationery(name, quantity, price, producer, type);
                    newItem.setId(id);
                }

                newList.add(newItem);
            }

            //add, close file, and inform
            originalList.addAll(newList);
            reader.close();
            System.out.println("Import data from "+filepath+" successfully!");
        } catch (IOException | InputMismatchException e){
            System.err.println(FILE_ERROR_MESSAGE);
        }
        return originalList;
    }

}
