import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JComponent{
    Snake snake1 = new Snake(new int[][]{ {240,240}, {240,260}, {240,280}}, -2, "Snake 1");
    Snake snake2 = new Snake(new int[][]{ {200,240}, {200,260}, {200,280}}, -2, "Snake 2");

    public BoardPanel(){
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        drawSnakes(g);
        drawFood(g);
        drawScore(g);
    }
    public void drawGrid(Graphics g){
        for (int i = 1; i <= 25; i++) {
            g.drawLine(0, 20 * i, 500, 20 * i);
            g.drawLine(20 * i, 0, 20 * i, 500);
        }
    }

    public void drawSnakes(Graphics g){
        for (int[] coordinates: snake1.coordinates)
            g.fillRect(coordinates[0], coordinates[1], 20, 20);
        for (int[] coordinates: snake2.coordinates)
            g.fillRect(coordinates[0], coordinates[1], 20, 20);
    }

    public void drawFood(Graphics g){
        g.fillOval(Food.coordinates[0], Food.coordinates[1], 20, 20);
    }

    public void drawScore(Graphics g){
        g.drawString("Score Snake 1: " + snake1.score, 520, 20);
        g.drawString("Score Snake 2: " + snake2.score, 520, 40);
    }

    public void moveSnakes(){
        snake1.moveBody();
        boolean snake1Collision = snake1.checkIfCollision(snake2.coordinates);
        snake2.moveBody();
        boolean snake2Collision = snake2.checkIfCollision(snake1.coordinates);
        repaint();
        if (snake1Collision && snake2Collision){
            JOptionPane.showMessageDialog(null, "It's DRAW!");
            System.exit(0);
        } else if (snake1Collision) {
            JOptionPane.showMessageDialog(null, snake1.snakeName + " lost!");
            System.exit(0);
        } else if (snake2Collision) {
            JOptionPane.showMessageDialog(null, snake2.snakeName + " lost!");
            System.exit(0);
        }
    }
}
