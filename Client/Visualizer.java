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
    private final int fieldSize = 100;
    private Position selectedAt;
    private static Board board=new Board();
    private final BufferedImage whiteBishop, whiteRook, whiteKnight, whiteKing, whiteQueen, whitePawn, blackBishop, blackRook, blackKnight, blackKing, blackQueen, blackPawn;

    public Visualizer(Board board) throws IOException {
        super("Chess");
        Visualizer.board = board;

        setSize(800+WindowLeftMargin, 800+WindowUpperMargin);
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

        URL blackBishopImageURL = getClass().getResource("/sources/figures/bishopB.png");
        blackBishop = ImageIO.read(Objects.requireNonNull(blackBishopImageURL));
        URL blackRookImageURL = getClass().getResource("/sources/figures/rookB.png");
        blackRook = ImageIO.read(Objects.requireNonNull(blackRookImageURL));
        URL blackKnightImageURL = getClass().getResource("/sources/figures/knightB.png");
        blackKnight = ImageIO.read(Objects.requireNonNull(blackKnightImageURL));
        URL blackKingImageURL = getClass().getResource("/sources/figures/kingB.png");
        blackKing = ImageIO.read(Objects.requireNonNull(blackKingImageURL));
        URL blackQueenImageURL = getClass().getResource("/sources/figures/queenB.png");
        blackQueen = ImageIO.read(Objects.requireNonNull(blackQueenImageURL));
        URL blackPawnImageURL = getClass().getResource("/sources/figures/pawnB.png");
        blackPawn = ImageIO.read(Objects.requireNonNull(blackPawnImageURL));

        URL whiteBishopImageURL = getClass().getResource("/sources/figures/bishopW.png");
        whiteBishop = ImageIO.read(Objects.requireNonNull(whiteBishopImageURL));
        URL whiteRookImageURL = getClass().getResource("/sources/figures/rookW.png");
        whiteRook = ImageIO.read(Objects.requireNonNull(whiteRookImageURL));
        URL whiteKnightImageURL = getClass().getResource("/sources/figures/knightW.png");
        whiteKnight = ImageIO.read(Objects.requireNonNull(whiteKnightImageURL));
        URL whiteKingImageURL = getClass().getResource("/sources/figures/kingW.png");
        whiteKing = ImageIO.read(Objects.requireNonNull(whiteKingImageURL));
        URL whiteQueenImageURL = getClass().getResource("/sources/figures/queenW.png");
        whiteQueen = ImageIO.read(Objects.requireNonNull(whiteQueenImageURL));
        URL whitePawnImageURL = getClass().getResource("/sources/figures/pawnW.png");
        whitePawn = ImageIO.read(Objects.requireNonNull(whitePawnImageURL));
    }

    private void onFieldClicked(MouseEvent e){
        Position clickPosition = new Position((e.getX()-WindowLeftMargin)/fieldSize, (e.getY()-WindowUpperMargin)/fieldSize);
        try {
            board.ClickAt(clickPosition);
        } catch (InvalidMoveException | OperationNotSupportedException ex) {
            ex.printStackTrace();
        }
        if (selectedAt == null) {
            selectedAt = clickPosition;
            repaint(selectedAt.getX() * fieldSize + WindowLeftMargin, (selectedAt.getY() * fieldSize) + WindowUpperMargin, fieldSize, fieldSize);
            return;
        }
        if (selectedAt.equals(clickPosition)){
            selectedAt = null;
            repaint(clickPosition.getX() * fieldSize + WindowLeftMargin, (clickPosition.getY() * fieldSize) + WindowUpperMargin, fieldSize, fieldSize);
            return;
        }
        Position oldSelection = new Position(selectedAt);
        selectedAt=clickPosition;
        repaint(selectedAt.getX()*fieldSize+WindowLeftMargin, (selectedAt.getY()*fieldSize)+WindowUpperMargin, fieldSize, fieldSize);
        repaint(oldSelection.getX()*fieldSize + WindowLeftMargin, (oldSelection.getY()*fieldSize)+WindowUpperMargin, fieldSize, fieldSize);
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

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paint(g);
        drawBoard(g);
        if (selectedAt != null) {
            graphics.setColor(Color.RED);
            graphics.fillRect(selectedAt.getX()*100 + WindowLeftMargin, (selectedAt.getY()*100)+WindowUpperMargin, fieldSize, fieldSize);
        }
        drawFigures(graphics);
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
