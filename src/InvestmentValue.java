import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestmentValue extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();

        TextField investment = new TextField();
        TextField years = new TextField();
        TextField interest = new TextField();
        TextField value = new TextField();

        pane.add(new Label("Investment Amount"),0,0);
        pane.add(investment,1,0);
        pane.add(new Label("Years"),0,1);
        pane.add(years, 1,1);
        pane.add(new Label("Annual Interest Rate"),0,2);
        pane.add(interest, 1,2);
        pane.add(new Label("Future Value"),0,3);
        pane.add(value,1,3);

        Button calculate = new Button("Calculate");
        pane.add(calculate,1,4);

        //action when calculate button is pressed
        calculate.setOnAction(e -> {
            //button pressed
            //calculate investment value
            float ftvalue = (float) (Float.valueOf(investment.getText()) *
                    Math.pow((1 + (Float.valueOf(interest.getText()))/100/12), Float.valueOf(years.getText()) * 12));
            value.setText(String.valueOf(ftvalue));

        });

        stage.setScene(new Scene(pane));
        stage.setTitle("Investment Value Calculator");
        stage.show();


    }
}
