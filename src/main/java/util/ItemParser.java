package util;

import product.ExemptedItem;
import product.ImportedItem;
import product.Item;
import product.NonExemptedItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses the input into specific type like ex: Imported, Exempted
 *
 * @author Pallavi Ambali
 */
public class ItemParser {

    private final Map<String, String> exemptedProductDict = new HashMap<>();

    /**
     *
     * @param is: file input stream object
     * @return List of parsed items categorized as follows:
     * 1. Exempted : Products exempted from Basic Sales Tax (Books, Medicine, Food)
     * 2. Imported : Products imported with 5% Import duty.
     * 3. NonExempted : Products with 10% Basic Sales Tax excluding (Books, Medicine, Food)
     *                  with additional 5% import duty if imported.
     * @throws FileNotFoundException : when "is/reader" is null
     * @throws NumberFormatException : When "at"  is missing from the input line.
     */
    public List<Item> parse(InputStream is) throws FileNotFoundException, NumberFormatException {

        exemptedProductDict.put("textbooks", "book");
        exemptedProductDict.put("chocolates", "food");
        exemptedProductDict.put("chocolate", "food");
        exemptedProductDict.put("pills", "medicine");
        exemptedProductDict.put("book", "book");

        List<Item> itemList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {

                String inputLine = line.toLowerCase();
                String[] tokenizedString = inputLine.split(" at ");
                double price = Double.parseDouble(tokenizedString[tokenizedString.length - 1]);
                int quantity = Integer.parseInt(tokenizedString[0].split("")[0]);
                String description = tokenizedString[0];
                String itemType = "Other";
                boolean isExempted = false;

                for (String key : exemptedProductDict.keySet()) {
                    if (inputLine.contains(key)) {
                        isExempted = true;
                        itemType = exemptedProductDict.get(key);
                        break;
                    }
                }

                Item item;
                if (isExempted) {
                    item = new ExemptedItem(quantity, itemType, description, price);
                } else {
                    item = new NonExemptedItem(quantity, itemType, description, price);
                }
                boolean isImported = inputLine.contains("imported");

                if (isImported)
                    itemList.add(new ImportedItem(item));
                else itemList.add(item);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return itemList;
    }
}
