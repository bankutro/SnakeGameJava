public class Food {
    static int[] coordinates = new int[2];
    public static int gameSpeed = 200;

    public static void generateCoordinates(int[][] snakeCoordinates){
        boolean isValid;
        do {
            isValid = true;
            coordinates[0] = (int) (Math.random() * 25) * 20;
            coordinates[1] = (int) (Math.random() * 25) * 20;
            for (int[] snakeCoordinate : snakeCoordinates) {
                if (snakeCoordinate[0] == coordinates[0] && snakeCoordinate[1] == coordinates[1]) {
                    isValid = false;
                    break;
                }
            }
        } while (!isValid);
    }
}
