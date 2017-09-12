import java.awt.*;
/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * @author csc143, Olga Sirotinsky
 */
public class Square
{
    private Grid theGrid;      // the environment where this Square is
    private int row, col;       // the grid location of this Square
    private boolean ableToMove; // true if this Square can move
    private Color color;       // the color of this Square
    
    // possible move directions are defined by the Game class
    
    // dimensions of a Square
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    /**
     * Constructor for objects of class Square
     * @param g  the Grid for this Square
     * @param row the row of this Square in the Grid 
     * @param col the column of this Square in the Grid 
     * @param c the Color of this Square
     * @param mobile  true if this Square can move
     * 
     * @throws IllegalArgumentException if row and col not within the Grid
     */
    public Square(Grid g, int row, int col, Color c, boolean mobile){
       if (row < 0 || row > g.HEIGHT-1)
            throw new IllegalArgumentException("Invalid row =" + row);
       if (col < 0 || col > g.WIDTH-1)
            throw new IllegalArgumentException("Invalid column  = " + col);
       
       // initialize instance variables
       theGrid = g;
       this.row = row;
       this.col = col;
       color = c;
       ableToMove = mobile;
    }

    /**
     * Return the row for this Square
     */
    public int getRow() {
        return row;
    }
    
    /** 
     * Return the column for this Square
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Return true if this Square can move 1 spot in direction d
     * @param direction the direction to test for possible move
     * @throws IllegalArgumentException if direction is not valid
     */
    public boolean canMove(int direction){
        if (!ableToMove)
            return false;
        
        boolean move = true;
        // if the given direction is blocked, we can't move
        // remember to check the edges of the grid
        switch(direction) {
            case Game.DOWN:
                if (row == (theGrid.HEIGHT -1) || theGrid.isSet(row + 1, col))
                    move = false;
                break;
           case Game.LEFT:
                if (col == 0 || theGrid.isSet(row, col-1))
                    move = false;
                break;
           case Game.RIGHT:
                if (col == (theGrid.WIDTH -1) || theGrid.isSet(row, col+1))
                    move = false;
                break;
           case Game.UP:
                if (row == 0 || theGrid.isSet(row-1, col))
                    move = false;
                break;
           default:
                throw new IllegalArgumentException("Bad direction to Square.canMove()");
        }   
        return move;
    }
    
    /** move Square in the given direction if possible
     * Square will not move if direction is blocked, or Square is unable to move
     * If attempt to move DOWN and it can't, the Square is frozen
     * and cannot move anymore
     * @param direction the direction to move
     */
    public void move(int direction) {
        if (canMove(direction)) {
            switch(direction) {
                case Game.DOWN:
                    row = row + 1;
                    break;
                case Game.LEFT:
                    //System.out.println("left");
                    col = col - 1;
                    break;
                 case Game.RIGHT:
                    //System.out.println("right");
                    col = col + 1;
                    break;
                    case Game.UP:
                    //System.out.println("right");
                    row = row - 1;
                    break;
                // currently doesn't support moving LEFT or RIGHT
                // MODIFY so that the Square moves appropriately         
            }
        } 
    }
    
    /** Change the color of the Square
     * @param c the new color
     */
    public void setColor(Color c) {
        color = c;
    }
    
    /** Get the color of this Square
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Draw this square on the given Graphics context
     */
    public void draw(Graphics g) {
    
        // calculate the upper left (x,y) coordinate of this square
        int actualX = theGrid.LEFT + col * WIDTH;
        int actualY = theGrid.TOP + row * HEIGHT;
        g.setColor(color);
        g.fillRect(actualX, actualY, WIDTH, HEIGHT);
    }
}
