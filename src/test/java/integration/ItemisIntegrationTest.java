package integration;

import bill.ReceiptGeneratorVisitor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import product.Item;
import util.ItemParser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ItemisIntegrationTest {

    private ItemParser itemParser;
    private ReceiptGeneratorVisitor receiptGeneratorVisitor;

    @Test()
    public void testExemptedNonExemptedItems() throws FileNotFoundException {
        final String INPUT = "input_file.txt";
        InputStream is = ItemisIntegrationTest.class.getClassLoader().getResourceAsStream(INPUT);
        itemParser = new ItemParser();
        List<Item> items = itemParser.parse(is);
        Assertions.assertEquals(3, items.size());
        receiptGeneratorVisitor = new ReceiptGeneratorVisitor();
        receiptGeneratorVisitor.calculateAndGenerateReceipt(items);
        Map<Item, Double> price = receiptGeneratorVisitor.getPriceMap();
        double total = 0.0;
        for (Item p : price.keySet()) {
            total += price.get(p);
        }
        Assertions.assertEquals(29.83, total);

    }

    @Test()
    public void testImportedItems() throws FileNotFoundException {
        final String INPUT = "input_file1.txt";
        InputStream is = ItemisIntegrationTest.class.getClassLoader().getResourceAsStream(INPUT);
        itemParser = new ItemParser();
        List<Item> items = itemParser.parse(is);
        Assertions.assertEquals(2, items.size());
        receiptGeneratorVisitor = new ReceiptGeneratorVisitor();
        receiptGeneratorVisitor.calculateAndGenerateReceipt(items);
        Map<Item, Double> price = receiptGeneratorVisitor.getPriceMap();
        double total = 0.0;
        for (Item p : price.keySet()) {
            total += price.get(p);
        }
        Assertions.assertEquals(65.15, total);

    }

    @Test()
    public void testAllItems() throws FileNotFoundException {
        final String INPUT = "input_file2.txt";
        InputStream is = ItemisIntegrationTest.class.getClassLoader().getResourceAsStream(INPUT);
        itemParser = new ItemParser();
        List<Item> items = itemParser.parse(is);
        Assertions.assertEquals(4, items.size());
        receiptGeneratorVisitor = new ReceiptGeneratorVisitor();
        receiptGeneratorVisitor.calculateAndGenerateReceipt(items);
        Map<Item, Double> price = receiptGeneratorVisitor.getPriceMap();
        double total = 0.0;
        for (Item p : price.keySet()) {
            total += price.get(p);
        }
        Assertions.assertEquals(74.63, total);

    }

    @Test(expected = NumberFormatException.class)
    public void testToValidate() throws FileNotFoundException {
        final String INPUT = "input_file4.txt";
        InputStream is = ItemisIntegrationTest.class.getClassLoader().getResourceAsStream(INPUT);
        itemParser = new ItemParser();
        itemParser.parse(is);

    }
}
