package product;

import bill.Visitor;

public class ImportedItem extends Item {

    private final double importTax = 0.05;

    private final Item item;

    // DECORATOR PATTERN USED HERE. Imported Item is an item but also has an item on which it decorates by adding extra tax.

    public ImportedItem(Item item) {
        super(item.getQuantity(), item.getItemType(), item.getItemDescription(), item.shelfPrice);
        this.item = item;

    }

    // DECORATOR PATTERN USED HERE. Imported Item is an item but also has an item on which it decorates by adding extra tax.
    @Override
    public double getTaxRate() {
        return item.getTaxRate() + importTax;
    }

    @Override
    public void accept(Visitor v) {
        v.visitImportedItem(this);
    }


}
