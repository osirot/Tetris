
import java.awt.*;
/**
 * An IPiece item in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration
 *             
 *        Sq   Sq  Sq   Sq<br>
 *          
 * The game piece "floats above" the Grid.  The (row, col) coordinates 
 * are the location of the middle Square on the side within the Grid
 * 
 * @author Olga Sirotinsky
 */
public class IPiece extends Piece
{
    private int rotatePos; //keep track of which current rotate has been used

    /**
     * Create an I-Shape piece.  See class description for actual location
     * of r and c
     * @param r row location for this piece
     * @param c column location for this piece
     * @param g the grid for this game piece
     * 
     */
    public IPiece(int r, int c, Grid g)
    {
        super(r, c, g); 
        rotatePos = 0; 

        // Create the squares
        square[0] = new Square(g, r, c - 2, Color.cyan, true);
        square[1] = new Square(g, r, c - 1 , Color.cyan, true);
        square[2] = new Square(g, r , c, Color.cyan, true);
        square[3] = new Square(g, r, c + 1, Color.cyan, true);
    }

    /**
     * depending on what value rotatePos holds
     * call rotate method and return. 
     */
    public void rotate(){
        if(rotatePos==0){
            // System.out.println("rotate 1= posstart = " + rotatePos);//test
            rotate1();           
            ///////use for testing--prints location of squares////////
            //int sqrC= square[0].getCol();
            //int sgrR= square[0].getRow();
            //System.out.println("square[0] row = " + sqrC);
            //System.out.println("square[0] col = " + sgrR);  
            //int sqrC1= square[1].getCol();
            //int sgrR1= square[1].getRow();
            // System.out.println("square[1] row = " + sqrC1);
            //System.out.println("square[1] col = " + sgrR1); 
            // int sqrC3= square[3].getCol();
            //int sgrR3= square[3].getRow();
            //System.out.println("square[3] row = " + sqrC3);
            //System.out.println("square[3] col = " + sgrR3); 
            /////////////////////////////////////////////////           
            return;
        } else {
            //  System.out.println("rotate 2 posstart = " + rotatePos);//test
            rotate2();        
            return;
        }
    }

    /**
     * if rotatePos==1 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from current
     * position to 90 degrees clockwise. 
     *    
     *           Sq<br>
     *           Sq<br>
     *           Sq<br>
     *           Sq<br>
     *               
     */
    private void rotate1(){
        //check square[0] if can move up 2 places if so move if not return to previous state
        if(!square[0].canMove(Game.UP)){
            return;
        }
        square[0].move(Game.UP);
        if(!square[0].canMove(Game.UP)){
            square[0].move(Game.DOWN);
            return;
        }
        square[0].move(Game.UP);
        //check square[0] if can move right 2 places, if so move, if not return to previous state
        if(!square[0].canMove(Game.RIGHT)){
            square[0].move(Game.DOWN);
            square[0].move(Game.DOWN);
            return;
        }
        square[0].move(Game.RIGHT);     
        if(!square[0].canMove(Game.RIGHT)){
            square[0].move(Game.LEFT); 
            square[0].move(Game.DOWN);
            square[0].move(Game.DOWN);
            return;
        }
        square[0].move(Game.RIGHT); 
        
        //update rotatePos only after  piece can make all moves
         rotatePos=1;

        //check square[1] if can move up 1 then right 1, if so move, if not return to previous state
        if(!square[1].canMove(Game.UP)){
            return;
        }
        square[1].move(Game.UP);
        if(!square[1].canMove(Game.RIGHT)){
            square[1].move(Game.DOWN);
            return;
        }
        square[1].move(Game.RIGHT); 

        //check square[3] if can move down 1 then left 1, if so move, if not return to previous state
        if(!square[3].canMove(Game.DOWN)){
            return;
        }
        square[3].move(Game.DOWN);
        if(!square[3].canMove(Game.LEFT)){
            square[3].move(Game.UP);
            return;
        }
        square[3].move(Game.LEFT); 

    }

    
    /**
     * if rotatePos==0 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from current
     * position to 90 degrees clockwise. 
     *    
     *           Sq  Sq  Sq  Sq<br>
     *               
     */private void rotate2(){
        //check square[0] if can move left 2 places, if so move, if not return to previous state
        if(!square[0].canMove(Game.LEFT)){
            return;
        }
        square[0].move(Game.LEFT);
        if(!square[0].canMove(Game.LEFT)){
            square[0].move(Game.RIGHT);
            return;
        }
        square[0].move(Game.LEFT);
        //check square[0] if can move down 2 places, if so move, if not return to previous state
        if(!square[0].canMove(Game.DOWN)){
            square[0].move(Game.RIGHT);
            square[0].move(Game.RIGHT);
            return;
        }
        square[0].move(Game.DOWN);     
        if(!square[0].canMove(Game.DOWN)){
            square[0].move(Game.UP); 
            square[0].move(Game.RIGHT);
            square[0].move(Game.RIGHT);
            return;
        }
        square[0].move(Game.DOWN); 
        
        //update rotatePos only after first piece can make all moves
         rotatePos=0;

        //check square[1] if can move left 1 then down 1, if so move, if not return to previous state
        if(!square[1].canMove(Game.LEFT)){
            return;
        }
        square[1].move(Game.LEFT);
        if(!square[1].canMove(Game.DOWN)){
            square[1].move(Game.RIGHT);
            return;
        }
        square[1].move(Game.DOWN); 

        //check square[3] if can move right 1 then up 1, if so move, if not return to previous state
        if(!square[3].canMove(Game.RIGHT)){
            return;
        }
        square[3].move(Game.RIGHT);
        if(!square[3].canMove(Game.UP)){
            square[3].move(Game.LEFT);
            return;
        }
        square[3].move(Game.UP); 

    }
}