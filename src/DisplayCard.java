import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class DisplayCard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ImageView imageView1, imageView2, imageView3;

        Random random = new Random();

        //choosing cards
        int card1 = random.nextInt(52) + 1;//random number 1-52
        imageView1 = new ImageView(new Image("/image/pics/" + card1 + ".png"));////images are named numbers 1-52

        int card2 = random.nextInt(52) + 1;
        while(card2 == card1) {card2 = random.nextInt(52) + 1;}//makes sure cards are different
        imageView2 = new ImageView(new Image("/image/pics/" + card2 + ".png"));

        int card3 = random.nextInt(52) + 1;
        while(card3 == card1||card3 == card2){card3 = random.nextInt(52) + 1;} //makes sure cards are different
        imageView3 = new ImageView(new Image("/image/pics/" + card3 + ".png"));

        //set image sizes
        imageView1.setFitHeight(100);
        imageView2.setFitHeight(100);
        imageView3.setFitHeight(100);
        imageView1.setFitWidth(50);
        imageView2.setFitWidth(50);
        imageView3.setFitWidth(50);

        GridPane pane = new GridPane();
        pane.add(imageView1, 0,0);
        pane.add(imageView2,1,0);
        pane.add(imageView3,2,0);


        stage.setScene(new Scene(pane));
        stage.setTitle("Display Cards");
        stage.show();


    }
}
