package bill;

import product.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptGeneratorVisitor implements Visitor {

    DecimalFormat format = new DecimalFormat("0.00");
    private Map<Item, Double> priceMap = new HashMap<>();
    private float salesTax = 0;

    public static double roundToHalf(double d) {
        return Math.round(d * 20.0) / 20.0;
    }

    @Override
    public void visitExemptedItem(ExemptedItem item) {
        priceMap.put(item, Double.valueOf(format.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice())));
    }

    @Override
    public void visitNonExemptedItem(NonExemptedItem item) {
        Double tax = roundToHalf(item.getShelfPrice() * item.getQuantity() * (item.getTaxRate()));

        priceMap.put(item, Double.valueOf(format.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice() + tax)));
        salesTax += tax;
    }

    @Override
    public void visitImportedItem(ImportedItem item) {
        Double tax = roundToHalf(item.getShelfPrice() * item.getQuantity() * (item.getTaxRate()));
        priceMap.put(item, Double.valueOf(format.format(priceMap.getOrDefault(item, 0.0) + item.getQuantity() * item.getShelfPrice() + tax)));
        salesTax += tax;
    }

    public void calculateAndGenerateReceipt(List<Item> items) {
        for (Element element : items) {
            element.accept(this);
        }
        generateReceipt(items);
    }

    public void generateReceipt(List<Item> items) {
        items.forEach(item -> {
                    System.out.println(item.getItemDescription() + ":" + format.format(priceMap.get(item)));
                }
        );
        System.out.println("Sales taxes: " + (salesTax));
        System.out.println("Total: " + (
                format.format(priceMap.values().stream().reduce(0.0, (a, b) -> a + b))));

    }

    public Map<Item, Double> getPriceMap() {
        return priceMap;
    }

    public Float getTax() {
        return salesTax;
    }
}
