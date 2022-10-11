package product;

import bill.Visitor;

public class NonExemptedItem extends Item {
    public NonExemptedItem(int quantity, String itemType, String itemDescription, double shelfPrice) {
        super(quantity, itemType, itemDescription, shelfPrice);
    }

    @Override
    public double getTaxRate() {
        return 0.1;
    }

    @Override
    public void accept(Visitor v) {
        v.visitNonExemptedItem(this);
    }

}
