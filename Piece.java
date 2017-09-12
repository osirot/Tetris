import java.awt.*;
/**
 *Superclass of all tetris pieces
  move piece with right, left, down arrows, space bar for down direction, and r for rotate
 * 
 * @author cs 143, Olga Sirotinsky 
 * @version (1/15/2016)
 */
public class Piece
{
    private boolean ableToMove;    // can this piece move
    protected Square[] square;       // the Squares that make up this piece
    // Made up of PIECE_COUNT squares
    private Grid theGrid;         // the board this piece is on    
    // number of squares in 1 Tetris game piece
    private static final int PIECE_COUNT = 4;

    /**
     * Constructor for objects of class Piece
     */
    public Piece(int r, int c, Grid g)
    {
        theGrid = g;
        square = new Square[PIECE_COUNT];
        ableToMove = true;      
    }

    /**
     * Draw the piece on the given Graphics context
     */
    public void draw(Graphics g){
        for (int i = 0; i < PIECE_COUNT; i++)
            square[i].draw(g);
    }

    /**
     * Move the piece if possible
     * Freeze the piece if it cannot move down anymore
     * @param direction the direction to move
     * @throws IllegalArgumentException if direction is not Square.DOWN, 
     * Square.LEFT, or Square.RIGHT
     */
    public void move(int direction){
        if (canMove(direction)){
            for (int i = 0; i < PIECE_COUNT; i++)
                square[i].move(direction);
        }       
        // if we couldn't move, see if because we're at the bottom
        else if (direction == Game.DOWN){
            ableToMove = false;
        }
    }

    /**
     *  null rotate method
     *  to be over ridden by each subclass piece
     */
    public void rotate(){
        return;
    }

    /** 
     * Return the (row,col) grid coordinates occupied by this Piece
     * @return an Array of (row,col) Points
     */
    public Point[] getLocations(){
        Point[] points = new Point[PIECE_COUNT];
        for(int i = 0; i < PIECE_COUNT; i++) {
            points[i] = new Point(square[i].getRow(), square[i].getCol());
        }
        return points;
    }

    /** 
     * Return the color of this piece
     */
    public Color getColor() {
        // all squares of this piece have the same color
        return square[0].getColor();
    }

    /**
     * Returns if this piece can move in the given direction
     * @throws IllegalArgumentException if direction is not Square.DOWN, 
     * Square.LEFT, or Square.RIGHT
     */
    public boolean canMove(int direction) {
        if (!ableToMove)
            return false;

        //Each square must be able to move in that direction
        boolean answer = true;
        for (int i = 0; i < PIECE_COUNT; i++) {
            answer = answer && square[i].canMove(direction);
        }

        return answer;
    }
}
