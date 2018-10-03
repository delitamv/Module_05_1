package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Snowman extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 700;
    public static final int EYES_AND_NOSE = 3;
    private static int count;
    private static int minRadius;
    private static int maxRadius;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        primaryStage.setTitle("Snowman");
        Pane root = new Pane();
        getInput();

        root.getChildren().addAll(generateCircles(count, minRadius,maxRadius));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of circles: ");
        count = input.nextInt();
        System.out.print("Enter the maximum radius: ");
        maxRadius = input.nextInt();
        System.out.print("Enter the minimum radius: ");
        minRadius = input.nextInt();
    }

    // Generating circles
    private Circle[] generateCircles(int count, int minRadius, int maxRadius) {
        Random random = new Random();

        Circle[] circles = new Circle[count+EYES_AND_NOSE];
        int radius;
        int previousRadius = 100;
        int x;
        int y = HEIGHT;
        int countCircles = 0;
        for(int i = 0; i < circles.length-EYES_AND_NOSE; i++) {

            radius = random.nextInt(maxRadius - minRadius) + minRadius;

            y -= radius + previousRadius;
            previousRadius = radius;

            circles[i] = new Circle(
                    (int)WIDTH/2,
                    y,
                    radius,
                    generateRandomColor());
            countCircles = i;
        }

        for (int i = 0; i < EYES_AND_NOSE; i++){
            circles[i + circles.length -EYES_AND_NOSE] = generateEyesAndNose(circles[countCircles])[i];
        }
        return circles;
    }

    // Generating eyes and nose
    private Circle[] generateEyesAndNose(Circle circle){
        final int CIRCLE = 3;
        int x_head = (int)circle.getCenterX();
        int y_head = (int)circle.getCenterY();
        int radius_head = (int)circle.getRadius();

        int radius = (int)radius_head/4;

        Circle[] circles = new Circle[CIRCLE];

        // Eye left
        circles[0] = new Circle(
                x_head - 2*radius,
                y_head - 2*radius,
                radius,
                generateRandomColor());

        // Eye right
        circles[1] = new Circle(
                x_head + 2*radius,
                y_head - 2*radius,
                radius,
                generateRandomColor());

        // Nose
        circles[2] = new Circle(
                x_head,
                y_head,
                radius,
                generateRandomColor());
        return circles;
    }



    private Color generateRandomColor() {
        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }

}