package Client;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class Visualizer extends JFrame {
    JFrame frame = new JFrame("Chess");
    JPanel panel = new JPanel();
    private final int WindowUpperMargin = 30;


    public Visualizer() {
        super("Chess");

        setSize(800, 800+WindowUpperMargin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    void drawLines(Graphics g) {
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
        super.paint(g);
        drawLines(g);
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
