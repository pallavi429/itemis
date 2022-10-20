package bill;

import product.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReceiptGeneratorVisitor implements Visitor {

    private final DecimalFormat formatter = ((DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY));
    private final String pattern = "#.##";
    NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
    private Map<Item, Double> priceMap = new HashMap<>();
    private float salesTax = 0;

    public static double roundToHalf(double d) {
        return Math.round(d * 20.0) / 20.0;
    }

    @Override
    public void visitExemptedItem(ExemptedItem item) {
        try {
            formatter.applyPattern(pattern);
            priceMap.put(item, numberFormat.parse(formatter.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice())).doubleValue());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visitNonExemptedItem(NonExemptedItem item) {
        try {
            Double tax = roundToHalf(item.getShelfPrice() * item.getQuantity() * (item.getTaxRate()));
            formatter.applyPattern(pattern);
            priceMap.put(item, numberFormat.parse(formatter.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice() + tax)).doubleValue());
            salesTax += tax;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visitImportedItem(ImportedItem item) {
        try {
            Double tax = roundToHalf(item.getShelfPrice() * item.getQuantity() * (item.getTaxRate()));
            formatter.applyPattern(pattern);
            priceMap.put(item, numberFormat.parse(formatter.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice() + tax)).doubleValue());
            salesTax += tax;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calculateAndGenerateReceipt(List<Item> items) {
        for (Element element : items) {
            element.accept(this);
        }
        generateReceipt(items);
    }

    public void generateReceipt(List<Item> items) {
        formatter.applyPattern(pattern);
        items.forEach(item -> {
                    System.out.println(item.getItemDescription() + ":" + formatter.format(priceMap.get(item)));
                }
        );
        System.out.println("Sales taxes: " + (salesTax));
        System.out.println("Total: " + (
                formatter.format(priceMap.values().stream().reduce(0.0, (a, b) -> a + b))));

    }

    public Map<Item, Double> getPriceMap() {
        return priceMap;
    }

    public Float getTax() {
        return salesTax;
    }
}
