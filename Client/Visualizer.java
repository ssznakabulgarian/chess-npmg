package Client;
import Common.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Visualizer extends JFrame {
    JFrame frame = new JFrame("Chess");
    JPanel panel = new JPanel();
    private final int WindowUpperMargin = 30;
    private Position selectedAt;

    public Visualizer() {
        super("Chess");

        setSize(800, 800+WindowUpperMargin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onFieldClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void onFieldClicked(MouseEvent e){
        Position clickPosition = new Position(e.getX()/100, (e.getY()-WindowUpperMargin)/100);
        if (selectedAt != null) {
            repaint(selectedAt.getX()*100, (selectedAt.getY()*100)+WindowUpperMargin, 100, 100);
            if(selectedAt.equals(clickPosition)) selectedAt=null;
        } else if (!selectedAt.equals(clickPosition)){ //the first click doesn't work
            selectedAt = clickPosition;
            repaint(selectedAt.getX() * 100, (selectedAt.getY() * 100) + WindowUpperMargin, 100, 100);
        }
    }

    void drawBoard(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j)%2==0) graphics.setColor(Color.WHITE);
                else {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(i*100, j*100+WindowUpperMargin, 100, 100);
            }
        }
    }


    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paint(g);
        drawBoard(g);
        if (selectedAt != null) {
            graphics.setColor(Color.RED);
            graphics.fillRect(selectedAt.getX()*100, (selectedAt.getY()*100)+WindowUpperMargin, 100, 100);
        }
        if (selectedAt == selectedAt) {

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Visualizer().setVisible(true);
            }
        });
    }
}
