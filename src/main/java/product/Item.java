package product;

import bill.Visitor;

import java.util.Objects;

public abstract class Item implements Element {

    protected double shelfPrice;
    private int quantity;
    private String itemType;
    private String itemDescription;

    protected Item(int quantity, String itemType, String itemDescription, double shelfPrice) {

        this.quantity = quantity;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.shelfPrice = shelfPrice;

    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getShelfPrice() {
        return shelfPrice;
    }

    @Override
    public String toString() {
        return "Item{" + "quantity='" + quantity + '\'' + ", itemType='" + itemType + '\'' + ", itemDescription='" + itemDescription + '\'' + ", shelfPrice=" + shelfPrice + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Double.compare(item.getShelfPrice(), getShelfPrice()) == 0 && getQuantity() == item.getQuantity() && getItemType().equals(item.getItemType()) && getItemDescription().equals(item.getItemDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getItemType(), getItemDescription(), getShelfPrice());
    }

    public abstract double getTaxRate();

    public abstract void accept(Visitor v);
}
