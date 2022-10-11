package bill;

import product.ExemptedItem;
import product.ImportedItem;
import product.NonExemptedItem;

public interface Visitor {

    void visitExemptedItem(ExemptedItem item);

    void visitNonExemptedItem(NonExemptedItem item);

    void visitImportedItem(ImportedItem item);

}
