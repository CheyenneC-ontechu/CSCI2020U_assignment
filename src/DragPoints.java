import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragPoints extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPadding(new Insets(100,100,100,100));
        //make circle
        Circle circle = new Circle(200 ,200,100);
        circle.setFill(null);
        circle.setStroke(Color.BLACK);

        //declaration of lines, side lengths, angles, and text
        Line[] line = new Line[3];
        double a,b,c;
        int A,B,C;
        Text[] anglesDisplay = new Text[3];

        //initialize points at random location
        Circle[] points = new Circle[3];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Circle(7);

            double angle = Math.random() * 360; //get a random angle from 0-360 degrees
            double x = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
            double y = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));

            points[i].setCenterX(x);
            points[i].setCenterY(y);
            points[i].setFill(Color.RED);
            points[i].setStroke(Color.BLACK);

        }
        //INITIALIZATION
        //lines
        line[0] =
                new Line(points[0].getCenterX(),points[0].getCenterY(),points[1].getCenterX(),points[1].getCenterY());
        line[1] =
                new Line(points[1].getCenterX(),points[1].getCenterY(),points[2].getCenterX(),points[2].getCenterY());
        line[2] =
                new Line(points[2].getCenterX(),points[2].getCenterY(),points[0].getCenterX(),points[0].getCenterY());

        //distances
        a = calculateDistance(points[0], points[1]);
        b = calculateDistance(points[1], points[2]);
        c = calculateDistance(points[2], points[0]);

        //angles
        A = (int) Math.toDegrees(Math.acos((a*a - b*b - c*c) / (-2 * b * c)));
        B = (int) Math.toDegrees(Math.acos((b*b - a*a - c*c) / (-2 * a * c)));
        C = (int) Math.toDegrees(Math.acos((c*c - b*b - a*a) / (-2 * a * b)));

        //angles display
        anglesDisplay[0] = new Text(points[0].getCenterX() +5, points[0].getCenterY()+5, String.valueOf(B));
        anglesDisplay[1] = new Text(points[1].getCenterX()+5, points[1].getCenterY()+5,String.valueOf(C));
        anglesDisplay[2] = new Text(points[2].getCenterX()+5, points[2].getCenterY()+5, String.valueOf(A));

        //mouse dragging event
        for (int i = 0; i < points.length;i++) {
            final int ind = i; //lambda function requires final variables
            points[i].setOnMouseDragged(e -> {
                double angle = e.getSceneX() - e.getSceneY();
                //change point location
                double x = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
                double y = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));
                points[ind].setCenterX(x);
                points[ind].setCenterY(y);

                setNewValues(line, anglesDisplay, points);
            });
        }


        pane.getChildren().add(circle);
        for (int i = 0; i < 3;i++) {
            pane.getChildren().add(points[i]);
            pane.getChildren().add(line[i]);
            pane.getChildren().add(anglesDisplay[i]);
        }

        stage.setScene(new Scene(pane));
        stage.setTitle("Dragging Points");
        stage.show();
    }
    /*
    calculates distance between two points
    uses pythagorean theorem
    returns distance
     */
    private double calculateDistance(Circle point1, Circle point2) {
        double x = point1.getCenterX() - point2.getCenterX();
        double y = point1.getCenterY() - point2.getCenterY();

        return Math.sqrt(x*x + y*y);
    }
    /*
    called when a point is dragged
    moves lines, and calculates the new angles
     */
    private void setNewValues(Line[] line, Text[] angles, Circle[] points) {
        for (int i = 0; i < 3; i++) {
            //if line is
            int ind = i == 2 ? 0 : (i+1);
            line[i].setStartX(points[i].getCenterX());
            line[i].setEndX(points[ind].getCenterX());
            line[i].setStartY(points[i].getCenterY());
            line[i].setEndY(points[ind].getCenterY());

        }
        //distances
        double a,b,c;
        a = calculateDistance(points[0], points[1]);
        b = calculateDistance(points[1], points[2]);
        c = calculateDistance(points[2], points[0]);

        //angles
        int A,B,C;
        A = (int) Math.toDegrees(Math.acos((a*a - b*b - c*c) / (-2 * b * c)));
        B = (int) Math.toDegrees(Math.acos((b*b - a*a - c*c) / (-2 * a * c)));
        C = (int) Math.toDegrees(Math.acos((c*c - b*b - a*a) / (-2 * a * b)));

        angles[0].setText(String.valueOf(String.valueOf(B)));
        angles[1].setText(String.valueOf(String.valueOf(C)));
        angles[2].setText(String.valueOf(String.valueOf(A)));

        //set location of angle labels
        for(int i = 0; i < angles.length;i++) {
            angles[i].setX(points[i].getCenterX() + 10);
            angles[i].setY(points[i].getCenterY() + 10);

        }

    }
}
