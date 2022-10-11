package product;

import bill.Visitor;

public interface Element {

    void accept(Visitor v);
}
