import bill.ReceiptGeneratorVisitor;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import product.ExemptedItem;
import product.ImportedItem;
import product.Item;
import product.NonExemptedItem;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestApplication {

    private ReceiptGeneratorVisitor visitor;


    @Before
    public void setup() {
        visitor = new ReceiptGeneratorVisitor();
    }

    @Test()
    public void testImportedNonExemptedItem() {
        Item nonExemptedItem = new ImportedItem(new NonExemptedItem(2, "Perfume", "Perfume Type 1 ", 11.0));
        visitor.calculateAndGenerateReceipt(List.of(nonExemptedItem));
        Assert.assertThat(visitor.getTax(), CoreMatchers.is(3.3F));

    }

    @Test
    public void testExemptedItem() {
        Item exemptedItem = new ExemptedItem(2, "food", "Food Type 1 ", 11.0);
        visitor.calculateAndGenerateReceipt(List.of(exemptedItem));
        Assert.assertThat(visitor.getPriceMap().size(), CoreMatchers.is(1));
        Assert.assertThat(visitor.getTax(), CoreMatchers.is(0.0F));

    }

    @Test
    public void testNonExemptedItem() {
        Item exemptedItem = new NonExemptedItem(2, "Perfume", "Perfume Type 1 ", 11.0);
        visitor.calculateAndGenerateReceipt(List.of(exemptedItem));
        Assert.assertThat(visitor.getTax(), CoreMatchers.is(2.2F));

    }

    @Test
    public void testImportedExemptedItem() {
        Item exemptedItem = new ImportedItem(new ExemptedItem(2, "chocolate", "five star bar ", 5.0));
        visitor.calculateAndGenerateReceipt(List.of(exemptedItem));
        Assert.assertThat(visitor.getTax(), CoreMatchers.is(0.5F));

    }

}
