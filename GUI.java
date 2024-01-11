import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {
    BoardPanel panel = new BoardPanel();

    public GUI() {
        add(panel);
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake Game");
        addKeyListener(this);
        setVisible(true);
        setResizable(false);

        Food.generateCoordinates(panel.snake1.coordinates);
        moveSnake();
    }

    public void moveSnake() {
        while (true) {
            panel.moveSnakes();
            try {
                Thread.sleep(Food.gameSpeed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            panel.snake1.currentDirection = panel.snake1.movementDirection;
            panel.snake2.currentDirection = panel.snake2.movementDirection;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> {
                if (panel.snake1.currentDirection != 1)
                    panel.snake1.setDirection(-1);
            }
            case KeyEvent.VK_RIGHT -> {
                if (panel.snake1.currentDirection != -1)
                    panel.snake1.setDirection(1);
            }
            case KeyEvent.VK_UP -> {
                if (panel.snake1.currentDirection != 2)
                    panel.snake1.setDirection(-2);
            }
            case KeyEvent.VK_DOWN -> {
                if (panel.snake1.currentDirection != -2)
                    panel.snake1.setDirection(2);
            }
        }
        switch (key) {
            case KeyEvent.VK_A -> {
                if (panel.snake2.currentDirection != 1)
                    panel.snake2.setDirection(-1);
            }
            case KeyEvent.VK_D -> {
                if (panel.snake2.currentDirection != -1)
                    panel.snake2.setDirection(1);
            }
            case KeyEvent.VK_W -> {
                if (panel.snake2.currentDirection != 2)
                    panel.snake2.setDirection(-2);
            }
            case KeyEvent.VK_S -> {
                if (panel.snake2.currentDirection != -2)
                    panel.snake2.setDirection(2);
            }
        }
    }
}
