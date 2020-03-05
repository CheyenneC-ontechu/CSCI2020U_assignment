import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Histogram extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setMinSize(500,500);
        Canvas canvas = new Canvas(475,475);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        TextField filename = new TextField();
        Button view = new Button("View");
        int[] letters = new int[26];

        view.setOnAction(e -> {
            try {
                File file = new File(filename.getText());
                Scanner input = new Scanner(file);
                while(input.hasNext()) {
                    String s = input.next();
                    for (int i = 0; i < s.length();i++){
                        if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                            letters[s.charAt(i)-65] += 1;
                        }
                        else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                            letters[s.charAt(i)-97] += 1;
                        }
                    }
                }
                int max = 0;
                for(int i = 0; i < letters.length;i++) {
                    max = letters[i] > max ? letters[i] : max;
                }
                for(int i = 0; i < letters.length;i++) {
                    gc.strokeRect(10 + (i*12),max-letters[i]+1,10, letters[i]);
                }

                for (int i = 0; i < 26; i++) {
                    gc.fillText(String.valueOf(((char)(i+65))), 10 + (i*12),max+12,10);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        pane.add(canvas,0,0,2,2);
        pane.add(filename,0,2,2,1);
        pane.add(view,2,2,1,1);

        stage.setScene(new Scene(pane));
        stage.setTitle("Histogram");
        stage.show();
    }

}
