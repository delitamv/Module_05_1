package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

public class Star extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    private double x;
    private double y;
    private double radius;

    public static final double CIRCLE = 360;
    public static final double SQUARE = 90;
    public static final int POLYGON = 5;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Star");
        primaryStage.setScene(scene);
        primaryStage.show();

        getInput();

        root.getChildren().addAll(generatingLines(x,y-radius,radius));
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the X coordinate of the center: ");
        x = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Y coordinate of the center: ");
        y = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Radius Star: ");
        radius = sc.nextInt();
        sc.nextLine();
    }
//    Star generation
    private Line[] generatingLines(double x, double y, double radius){
        Line[] lines = new Line[POLYGON];
        double deltaX;
        double deltaY;
        double x_old = x;
        double y_old = y;
        for (int i = 0; i < lines.length; i++){
            deltaX = radius * Math.cos(Math.toRadians(SQUARE + (i+1)*2*CIRCLE/POLYGON));
            deltaY = radius * (1 - Math.sin(Math.toRadians(SQUARE + (i+1)*2*CIRCLE/POLYGON)));

            lines[i] = new Line(x_old,
                    y_old,
                    x + deltaX,
                    y + deltaY);
            x_old = x + deltaX;
            y_old = y + deltaY;
        }
        return lines;
    }
}
