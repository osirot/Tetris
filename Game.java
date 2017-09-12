import java.awt.*;
import java.util.*;
/**
 * Manages the game Tetris.  Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143, Olga Sirotinsky
 */
public class Game
{

    private Grid theGrid;       // the grid that makes up the Tetris board
    private Tetris theDisplay;  // the visual for the Tetris game
    private Piece piece;        // the current piece that is dropping
    private boolean isOver;     // has the game finished?
    //private Piece[] pieceType;  // hold  an instance of each piece object 
    // possible move directions
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int UP = 3;
    /**
     * Create a Tetris game
     * @param Tetris the display
     */
    public Game(Tetris display)
    {
        theGrid = new Grid();
        theDisplay = display;
        piece = findPiece();
        isOver = false;
    }

    /**
     * This method stores each game piece object in an array and
     * uses random to find the next Piece in the array.This method
     * is used to construct an initial game piece and to produce the
     * next piece in the game randomly. 
     * 
     * @return piece the next random piece generated for game
     */
    private Piece findPiece(){

        //find which piece using math.random * (# of pieces) 
        //assign each num (case)to one piece object and return instance of object 
        int whichPiece = (int)(Math.random()*7);  
        switch(whichPiece){
            case 0:             
            piece= new LPiece(1, Grid.WIDTH/2 -1, theGrid);   
            break;
            case 1: 
            piece = new JPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;
            case 2: 
            piece = new ZPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;
            case 3:
            piece = new TPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;
            case 4:
            piece = new IPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;
            case 5:
            piece = new SPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;
            default:
            piece = new SqrPiece(1, Grid.WIDTH/2 -1, theGrid);
            break;

        }
        return piece;

    }

    /** Draw the current state of the game
     * @param g the Graphics context on which to draw
     */
    public void draw(Graphics g)
    {
        theGrid.draw(g);
        if (piece != null)
            piece.draw(g);
    }

    /** Move the piece in the given direction
     * @param the direction to move
     * @throws IllegalArgumentException if direction is not legal
     */
    public void movePiece(int direction){
        if (piece != null)
            piece.move(direction);
        //System.out.println(direction);
        updatePiece();
        theDisplay.update();
        theGrid.checkRows();    
    }

    /**
     * move piece in clockwise rotation 
     */
    public void rotatePiece(){
        if (piece != null)
            piece.rotate();
        //System.out.println(direction);
        updatePiece();
        theDisplay.update();
        theGrid.checkRows();    
    }

    /**
     * Returns true if the game is over
     */
    public boolean isGameOver() {
        // game is over if the piece occupies the same space as some non-empty
        // part of the grid.  Usually happens when a new piece is made
        if (piece == null)
            return false;

        // check if game is already over
        if (isOver)
            return true;

        // check every part of the piece
        Point[] p = piece.getLocations();
        for (int i = 0; i <p.length; i++)
            if (theGrid.isSet((int) p[i].getX(), (int) p[i].getY()) ) {
                isOver = true;
                return true;
            }
        return false;
    }

    /**
     * Update the piece by creating new peice when previous 
     * piece is frozen
     */
    private void updatePiece() {
        if (piece == null) {
            //CREATE A NEW PIECE HERE, after previous piece frozen
            piece = findPiece();

        } 

        // set Grid positions corresponding to frozen piece
        // and then release the piece
        else if (!piece.canMove(Game.DOWN)) {
            Point [] p = piece.getLocations();
            Color c = piece.getColor();
            for (int i =0; i < p.length; i++){
                theGrid.set((int)p[i].getX(), (int)p[i].getY(), c);
            }
            piece = null;
        }

    }

}
