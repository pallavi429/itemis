package product;

import bill.Visitor;

public class ExemptedItem extends Item {

    public ExemptedItem(int quantity, String itemType, String itemDescription, double shelfPrice) {
        super(quantity, itemType, itemDescription, shelfPrice);
    }

    @Override
    public double getTaxRate() {
        return 0;
    }

    @Override
    public void accept(Visitor v) {
        v.visitExemptedItem(this);
    }

}
