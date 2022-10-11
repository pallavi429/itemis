import bill.ReceiptGeneratorVisitor;
import product.Item;
import util.ItemParser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * The itemis Challenge Application
 *
 * @author Pallavi Ambali
 */

public class ItemIsApplication {

    public static void main(String[] args) {

        ItemParser itemParser = new ItemParser();
        final String INPUT = "input_file.txt";
        InputStream is = ItemIsApplication.class.getClassLoader().getResourceAsStream(INPUT);
        try {
            List<Item> items = itemParser.parse(is);
            ReceiptGeneratorVisitor visitor = new ReceiptGeneratorVisitor();
            visitor.calculateAndGenerateReceipt(items);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
