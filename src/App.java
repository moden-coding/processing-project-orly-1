import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        textSize(30);
    }

    int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    float randomY = random(0, 560);
    float carX = 800;
    boolean placeCone = false;
    boolean sinkholeContact = false;
    boolean coneContact = false;
    int score = 0;
    int speed = 1;
    float placeSinkhole = random(50, 700);
    float R = random(255);
    float G = random(255);
    float B = random(255);
    int scene = 1;

    public void carReset() {
        randomY = random(40, 560);
        carX = 800;
        score++;
        speed++;
        placeSinkhole = random(50, 700);
        R = random(255);
        G = random(255);
        B = random(255);
        sinkhole();
    }

    public void draw() {
        if (scene == 1) {
            background(0);
            fill(R, G, B);
            text("click anywhere to start", 100, 200);
            noStroke();
            rect(400, 300, 80, 20);
            rect(415, 280, 50, 20);
            fill(192, 192, 192);
            strokeWeight(5);
            stroke(53, 51, 49);
            ellipse((400 + 20), (300 + 18), 20, 20);
            ellipse((400 + 60), (300 + 18), 20, 20);
            fill(0, 162, 237);
            strokeWeight(1);
            stroke(0);
            rect((400 + 17), (300 - 17), 18, 18);
            rect((400 + 43), (300 - 17), 18, 18);
            fill(241, 211, 2);
            noStroke();
            ellipse((400), (300 + 13), 5, 8);
            fill(255, 20, 20);
            noStroke();
            ellipse((400 + 80), (300 + 13), 5, 8);
        }
        if (scene == 2) {
            background(0);
            // calls sinkhole
            sinkhole();
            // car color and body
            fill(R, G, B);
            noStroke();
            rect(carX, randomY, 80, 20);
            rect((carX + 15), (randomY - 20), 50, 20);
            // tires
            fill(192, 192, 192);
            strokeWeight(5);
            stroke(53, 51, 49);
            ellipse((carX + 20), (randomY + 18), 20, 20);
            ellipse((carX + 60), (randomY + 18), 20, 20);
            // car windows
            fill(0, 162, 237);
            strokeWeight(1);
            stroke(0);
            rect((carX + 17), (randomY - 17), 18, 18);
            rect((carX + 43), (randomY - 17), 18, 18);
            // headlight
            fill(241, 211, 2);
            noStroke();
            ellipse(carX, (randomY + 13), 5, 8);
            // tail light
            fill(255, 20, 20);
            noStroke();
            ellipse((carX + 80), (randomY + 13), 5, 8);
            carX = carX - speed;
            if (carX < 0) {
                carReset();
            }
            if (placeCone) {
                fill(255, 95, 21);
                noStroke();
                triangle(x1, y1, x2, y2, x3, y3);
            }
            int pixelValueConeBase = get((x1 + 1), (y1));
            int pixelValueConeTop = get((x3 + 1), (y3));
            if (pixelValueConeBase != -16777216 || pixelValueConeTop != -16777216) {
                carReset();
            }
            int pixelValueCarFront = get((int) (carX - 4), (int) (randomY + 13));
            if (pixelValueCarFront == -8355712 || speed > 50) {
                scene = 3;
            }
        }

        if (scene == 3) {
            gameOver();
        }
    }

    public void sinkhole() {
        fill(192, 192, 192);
        ellipse(placeSinkhole, (randomY + 10), 90, 50);
    }

    public void mousePressed() {
        if (scene == 1) {
            scene = 2;
        }
        if (scene == 2) {
            x1 = mouseX;
            y1 = mouseY - 30;
            x2 = mouseX - 20;
            y2 = mouseY + 20;
            x3 = mouseX + 20;
            y3 = mouseY + 20;
            fill(255, 95, 21);
            noStroke();
            triangle(x1, y1, x2, y2, x3, y3);
            placeCone = true;
        }
        if (scene == 3) {
            score = 0;
            scene = 2;
        }
    }

    public void gameOver() {
        background(255);
        fill(R, G, B);
        text("womp womp. here's your score: " + score, 200, 300);
        text("click anywhere to restart", 100, 200);
        speed = 1;

    }
}