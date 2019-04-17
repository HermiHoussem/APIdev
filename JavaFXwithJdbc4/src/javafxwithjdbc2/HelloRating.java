package javafxwithjdbc2;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class HelloRating implements Initializable {

	@FXML
	Rating rating;
	@FXML 
	Label msg;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              msg.setText("Rating : "+ t1.toString());
            }
        });
        		
	}

}
