package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;


public class App extends Application {

    private final static int SQUARE_SIZE = 12;

    private Canvas canvas;
    private GraphicsContext g;

    private int width = 334;
    private int height = 410;

    private int rows;
    private int columns;

    private boolean[][] encountered;

    private boolean[][] finished;

    private Button abortButton;

    private Label message;

    private ComboBox<String> methodChoice;

    private final static int STACK=0, QUEUE=1, RANDOM = 2;

    private int method;

    private AnimationTimer timer;

    private Queue queue;
    private Stack stack;
    //private ArrayList<Location> randomList;


    @Override
    public void start(Stage stage) {

        rows = (height - 130) / SQUARE_SIZE;
        columns = (width - 20) / SQUARE_SIZE;

        encountered = new boolean[rows][columns];
        finished = new boolean[rows][columns];

        canvas = new Canvas(1 + columns * SQUARE_SIZE, 1 + rows * SQUARE_SIZE);
        g = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e -> mousePressed(e));

        message = new Label("Click any square to begin.");
        message.setTextFill(Color.DARKGREEN);
        message.setFont(Font.font(null, FontWeight.BOLD, 14));


        methodChoice = new ComboBox<String>();
        methodChoice.getItems().add("Stack");
        methodChoice.getItems().add("Queue");
        methodChoice.getItems().add("Random");
        methodChoice.setEditable(false);
        methodChoice.setValue("Queue");

        abortButton = new Button("Abort");
        abortButton.setDisable(true);
        abortButton.setOnAction(e -> doAbort());

        Label lb = new Label("Use:");
        lb.setTextFill(Color.BLACK);


        Pane root = new Pane(canvas, message, abortButton, methodChoice, lb);
        root.setStyle("-fx-background-color:#BBE; -fx-border-color:#00A; -fx-border-width:2px");

        canvas.relocate(10, 10);

        message.setManaged(false);
        message.relocate(15, height - 118);
        message.resize(width - 30, 25);
        message.setAlignment(Pos.CENTER);

        abortButton.setManaged(false);
        abortButton.relocate(75, height-85);
        abortButton.resize(width-150, 30);

        methodChoice.setManaged(false);
        methodChoice.relocate(75, height-42);
        methodChoice.resize(width-150, 30);

        lb.relocate(30, height-35);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stack and Queue Demo");
        draw();

        stage.show();
    }

    private void draw() {
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, 1 + columns * SQUARE_SIZE, 1 + rows * SQUARE_SIZE);
        g.setStroke(Color.BLACK);

        for (int i = 0; i <= rows; i++) {
            g.strokeLine(0.5, 0.5 + i * SQUARE_SIZE, columns * SQUARE_SIZE + 0.5, 0.5 + i * SQUARE_SIZE);
        }
        for (int i = 0; i <= columns; i++) {
            g.strokeLine(0.5 + i * SQUARE_SIZE, 0.5, 0.5 + i * SQUARE_SIZE, rows * SQUARE_SIZE + 0.5);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (finished[r][c]) {
                    g.setFill(Color.GRAY);
                    g.fillRect(1 + c * SQUARE_SIZE, 1 + r * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1);
                } else if (encountered[r][c]) {
                    g.setFill(Color.RED);
                    g.fillRect(1 + c * SQUARE_SIZE, 1 + r * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1);
                }
            }
        }
    }

    private void doAbort() {
    }

    private void mousePressed(MouseEvent e) {
    }

    public static void main(String[] args) {
        launch();
    }

}