package Client;

import Common.InvalidMoveException;
import Common.Position;
import Figures.Figure;
import GameLogic.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.naming.OperationNotSupportedException;
import javax.swing.*;

public class Visualizer extends JFrame {
    JFrame frame = new JFrame("Chess");
    JPanel panel = new JPanel();

    private final int WindowUpperMargin = 30;
    private final int WindowLeftMargin = 5;
    private int fieldSize = 100;
    private Position selectedAt;
    private static Board board=new Board();
    private final BufferedImage whiteBishop, whiteRook, whiteKnight, whiteKing, whiteQueen, whitePawn, blackBishop, blackRook, blackKnight, blackKing, blackQueen, blackPawn;

    public Visualizer(Board board) throws IOException {
        super("Chess");
        Visualizer.board = board;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;

        int height = screenSize.height-taskBarSize-WindowUpperMargin;
        int width = screenSize.height-taskBarSize-WindowUpperMargin;

        fieldSize = width / 8;

        setSize(fieldSize*8+WindowLeftMargin, fieldSize*8+WindowUpperMargin);

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

        URL blackBishopImageURL = getClass().getResource("/figures/bishopB.png");
        blackBishop = ImageIO.read(Objects.requireNonNull(blackBishopImageURL));
        URL blackRookImageURL = getClass().getResource("/figures/rookB.png");
        blackRook = ImageIO.read(Objects.requireNonNull(blackRookImageURL));
        URL blackKnightImageURL = getClass().getResource("/figures/knightB.png");
        blackKnight = ImageIO.read(Objects.requireNonNull(blackKnightImageURL));
        URL blackKingImageURL = getClass().getResource("/figures/kingB.png");
        blackKing = ImageIO.read(Objects.requireNonNull(blackKingImageURL));
        URL blackQueenImageURL = getClass().getResource("/figures/queenB.png");
        blackQueen = ImageIO.read(Objects.requireNonNull(blackQueenImageURL));
        URL blackPawnImageURL = getClass().getResource("/figures/pawnB.png");
        blackPawn = ImageIO.read(Objects.requireNonNull(blackPawnImageURL));

        URL whiteBishopImageURL = getClass().getResource("/figures/bishopW.png");
        whiteBishop = ImageIO.read(Objects.requireNonNull(whiteBishopImageURL));
        URL whiteRookImageURL = getClass().getResource("/figures/rookW.png");
        whiteRook = ImageIO.read(Objects.requireNonNull(whiteRookImageURL));
        URL whiteKnightImageURL = getClass().getResource("/figures/knightW.png");
        whiteKnight = ImageIO.read(Objects.requireNonNull(whiteKnightImageURL));
        URL whiteKingImageURL = getClass().getResource("/figures/kingW.png");
        whiteKing = ImageIO.read(Objects.requireNonNull(whiteKingImageURL));
        URL whiteQueenImageURL = getClass().getResource("/figures/queenW.png");
        whiteQueen = ImageIO.read(Objects.requireNonNull(whiteQueenImageURL));
        URL whitePawnImageURL = getClass().getResource("/figures/pawnW.png");
        whitePawn = ImageIO.read(Objects.requireNonNull(whitePawnImageURL));
    }

    private void onFieldClicked(MouseEvent e){
        Position clickPosition = new Position((e.getX()-WindowLeftMargin)/fieldSize, (e.getY()-WindowUpperMargin)/fieldSize);
        try {
            board.ClickAt(clickPosition);
        } catch (InvalidMoveException | OperationNotSupportedException ex) {
            ex.printStackTrace();
        }
        if (board.getSelectedFigure() != null) {
            selectedAt = board.getSelectedFigure().getPosition();
        } else {

            selectedAt = null;
        }
        repaint();
    }

    void drawBoard(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j)%2==0) graphics.setColor(Color.WHITE);
                else {
                    graphics.setColor(Color.darkGray);
                }
                graphics.fillRect(i*fieldSize + WindowLeftMargin, j*fieldSize+WindowUpperMargin, fieldSize, fieldSize);
            }
        }
    }

   void drawFigures(Graphics2D g) {
       for (Figure figure : board.getFigures()){
           BufferedImage img = null;
           try {
               img = (BufferedImage) getClass().getDeclaredField(figure.getColour().toString().toLowerCase()+figure.getClass().getSimpleName()).get(this);
               g.drawImage(img, figure.getPosition().x * fieldSize + WindowLeftMargin,figure.getPosition().y * fieldSize+WindowUpperMargin,fieldSize,fieldSize,null);
           } catch (IllegalAccessException | NoSuchFieldException e) {
               e.printStackTrace();
           }
       }
    }

    void drawAllPossibleMoves(Graphics2D g) {
        if (board.getSelectedFigure() == null ) {
            return;
        }
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSelectedFigure().isMoveValid(new Position(i, j))) {
                    g.setColor(board.isSquareEmpty(new Position(i,j)) ? Color.green : Color.red);
                    g.drawOval(i*fieldSize + WindowLeftMargin, j*fieldSize+WindowUpperMargin, fieldSize, fieldSize);
                }
            }
        }
    }

    void openPromotionalDialogue(Graphics2D g) {
        
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paint(g);
        drawBoard(g);
        if (selectedAt != null) {
            graphics.setColor(Color.RED);
            graphics.fillRect(selectedAt.getX()*fieldSize + WindowLeftMargin, (selectedAt.getY()*fieldSize)+WindowUpperMargin, fieldSize, fieldSize);
        }

        drawFigures(graphics);
        drawAllPossibleMoves(graphics);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Visualizer(board).setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
