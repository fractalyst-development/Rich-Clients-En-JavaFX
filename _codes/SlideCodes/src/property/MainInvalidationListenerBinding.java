package property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

class Bill2 {

    // Define the property
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    // Define a getter for the property's value
    public final double getAmountDue() {
        return amountDue.get();
    }

    // Define a setter for the property's value
    public final void setAmountDue(double value) {
        amountDue.set(value);
    }

    // Define a getter for the property itself
    public DoubleProperty amountDueProperty() {
        return amountDue;
    }

}

public class MainInvalidationListenerBinding {

    public static void main(String[] args) {

        Bill2 bill1 = new Bill2();
        Bill2 bill2 = new Bill2();
        Bill2 bill3 = new Bill2();

        NumberBinding total = Bindings.add(bill1.amountDueProperty().add(bill2.amountDueProperty()),
                bill3.amountDueProperty());
        total.addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                System.out.println("The binding is now invalid.");
            }
        });

        // First call makes the binding invalid
        bill1.setAmountDue(200.00);

        // The binding is now invalid
        bill2.setAmountDue(100.00);
        bill3.setAmountDue(75.00);

        // Make the binding valid...
        System.out.println(total.getValue());

        // Make invalid... 
        bill3.setAmountDue(150.00);

        // Make valid...
        System.out.println(total.getValue());
    }
}
