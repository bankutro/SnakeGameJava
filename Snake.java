import javax.swing.*;

public class Snake {
    int[][] coordinates;
    int movementDirection;
    int currentDirection;
    int score = 0;
    String snakeName;

    public Snake(int[][] coordinates, int movementDirection, String snakeName){
        this.coordinates = coordinates;
        this.movementDirection = movementDirection;
        this.snakeName = snakeName;
    }

    public void moveBody(){
        for (int i = coordinates.length - 1; i >= 0; i--){
            if (i == 0) {
                moveHead();
                checkIfFoodEaten();
                continue;
            }
            checkIfGameOver(coordinates[i][0], coordinates[i][1]);
            coordinates[i][0] = coordinates[i - 1][0];
            coordinates[i][1] = coordinates[i - 1][1];
        }
    }

    public void moveHead(){
        switch (movementDirection) {
            case 1 -> coordinates[0][0] += 20;
            case -1 -> coordinates[0][0] -= 20;
            case 2 -> coordinates[0][1] += 20;
            case -2 -> coordinates[0][1] -= 20;
        }
        if (coordinates[0][0] > 499)
            coordinates[0][0] = 0;
        else if (coordinates[0][0] < 0)
            coordinates[0][0] = 480;
        if (coordinates[0][1] > 499)
            coordinates[0][1] = 0;
        else if (coordinates[0][1] < 0)
            coordinates[0][1] = 480;
    }

    public void setDirection(int direction){
        movementDirection = direction;
    }

    public void checkIfFoodEaten(){
        if (Food.coordinates[0] == coordinates[0][0] && Food.coordinates[1] == coordinates[0][1]) {
            Food.gameSpeed = Food.gameSpeed > 50 ? Food.gameSpeed - 10: Food.gameSpeed;
            score += 100;
            int[][] newSnakeLength = new int[coordinates.length + 1][2];
            for (int j = 0; j < coordinates.length; j++){
                newSnakeLength[j][0] = coordinates[j][0];
                newSnakeLength[j][1] = coordinates[j][1];
            }
            newSnakeLength[coordinates.length][0] = coordinates[coordinates.length - 1][0];
            newSnakeLength[coordinates.length][1] = coordinates[coordinates.length - 1][1];
            coordinates = newSnakeLength;
            Food.generateCoordinates(coordinates);
        }
    }

    public void checkIfGameOver(int bodyCoordinateX, int bodyCoordinateY) {
        if (bodyCoordinateX == coordinates[0][0] && bodyCoordinateY == coordinates[0][1]) {
            JOptionPane.showMessageDialog(null, snakeName + " lost!");
            System.exit(0);
        }
    }

    public boolean checkIfCollision(int[][] otherSnakeCoordinates) {
        for (int[] otherSnakeCoordinate : otherSnakeCoordinates){
            if (otherSnakeCoordinate[0] == coordinates[0][0] && otherSnakeCoordinate[1] == coordinates[0][1]){
                return true;
            }
        }
        return false;
    }
}
