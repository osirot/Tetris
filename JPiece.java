
import java.awt.*;
/**
 * A JPiece item in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration
 *              Sq<br>
 *              Sq<br>
 *           Sq Sq<br>
 * 
 * The game piece "floats above" the Grid.  The (row, col) coordinates 
 * are the location of the middle Square on the side within the Grid
 * 
 * @author Olga Sirotinsky
 */
public class JPiece extends Piece
{
    private int rotatePos; //keep track of which current rotate has been used

    /**
     * Create a J-Shape piece.  See class description for actual location
     * of r and c
     * @param r row location for this piece
     * @param c column location for this piece
     * @param g the grid for this game piece
     * 
     */
    public JPiece(int r, int c, Grid g)
    {
        super(r, c, g); 
        rotatePos = 0;
        // Create the squares
        square[0] = new Square(g, r + 1, c - 1, Color.blue, true);
        square[1] = new Square(g, r + 1, c , Color.blue, true);
        square[2] = new Square(g, r, c, Color.blue, true);
        square[3] = new Square(g, r - 1, c, Color.blue, true);
    }

    /**
     * depending on what value rotatePos holds
     * call rotate method and return
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
        } else if(rotatePos==1){
            //  System.out.println("rotate 2 posstart = " + rotatePos);//test
            rotate2();             
            return;
        } else if(rotatePos==2){
            //System.out.println("rotate 3 pos start = " + rotatePos); //test           
            rotate3();            
            return;
        } else {
            //System.out.println("rotate 4 posstart = " + rotatePos); //test           
            rotate4();          
            return;
        }
    }

    /**
     * if rotatePos==0 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from starting 
     * position to 90 degrees clockwise. 
     *    
     *              Sq<br>
     *              Sq  Sq  Sq<br>
     */
    private void rotate1(){
        //check square[3] if can move right then down, if so move, if not return to previous state
        if(!square[3].canMove(Game.RIGHT)){
            return;
        }
        square[3].move(Game.RIGHT);     
        if(!square[3].canMove(Game.DOWN)){
            square[3].move(Game.LEFT); //undo move right
            return;
        }
        square[3].move(Game.DOWN); 

        //check square[0] if can move up two places if so move if not return to previous state
        if(!square[0].canMove(Game.UP)){
            return;
        }
        square[0].move(Game.UP);
        if(!square[0].canMove(Game.UP)){
            square[0].move(Game.DOWN); //undo move up
            return;
        }
        square[0].move(Game.UP);

        //update rotatePos only after piece can move twice 
        rotatePos=1;

        //check square[1] if can move left then up, if so move, if not return to previous state
        if(!square[1].canMove(Game.LEFT)){
            return;
        }
        square[1].move(Game.LEFT);     
        if(!square[1].canMove(Game.UP)){
            square[1].move(Game.RIGHT); //undo move left
            return;
        }
        square[1].move(Game.UP); 

    }
    /**
     * if rotatePos==1 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from starting 
     * position to 90 degrees clockwise. 
     *    
     *              Sq  Sq<br>
     *              Sq<br>
     *              Sq<br>
     */
    private void rotate2(){
        //check square[3] if can move down then left, if so move, if not return to previous state
        if(!square[3].canMove(Game.DOWN)){
            return;
        }
        square[3].move(Game.DOWN);     
        if(!square[3].canMove(Game.LEFT)){
            square[3].move(Game.UP); //undo move down
            return;
        }
        square[3].move(Game.LEFT); 

        //check square[0] if can move right two places if so move if not return to previous state
        if(!square[0].canMove(Game.RIGHT)){
            return;
        }
        square[0].move(Game.RIGHT);
        if(!square[0].canMove(Game.RIGHT)){
            square[0].move(Game.LEFT); //undo move right
            return;
        }
        square[0].move(Game.RIGHT);

        //update rotatePos only after piece can move twice 
        rotatePos=2;

        //check square[1] if can move up then right, if so move, if not return to previous state
        if(!square[1].canMove(Game.UP)){
            return;
        }
        square[1].move(Game.UP);     
        if(!square[1].canMove(Game.RIGHT)){
            square[1].move(Game.DOWN); //undo move up
            return;
        }
        square[1].move(Game.RIGHT); 

    }
    /**
     * if rotatePos==2 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from starting 
     * position to 90 degrees clockwise. 
     *               
     *              Sq  Sq  Sq<br>
     *                      Sq<br>
     */
    private void rotate3(){
        //check square[3] if can move left then up, if so move, if not return to previous state
        if(!square[3].canMove(Game.LEFT)){
            return;
        }
        square[3].move(Game.LEFT);     
        if(!square[3].canMove(Game.UP)){
            square[3].move(Game.RIGHT); //undo move left
            return;
        }
        square[3].move(Game.UP); 

        //check square[0] if can move down two places if so move if not return to previous state
        if(!square[0].canMove(Game.DOWN)){
            return;
        }
        square[0].move(Game.DOWN);
        if(!square[0].canMove(Game.DOWN)){
            square[0].move(Game.UP); // undo move down
            return;
        }
        square[0].move(Game.DOWN);

        //update rotatePos only after piece can move twice 
        rotatePos=3; 

        //check square[1] if can move right then down, if so move, if not return to previous state
        if(!square[1].canMove(Game.RIGHT)){
            return;
        }
        square[1].move(Game.RIGHT);     
        if(!square[1].canMove(Game.DOWN)){
            square[1].move(Game.LEFT); //undo move right
            return;
        }
        square[1].move(Game.DOWN); 

    }

    /**
     * if rotatePos==3 this method will be called to rotate the piece 
     * (each individual square is checked and moved) from starting 
     * position to 90 degrees clockwise. 
     *    
     *              Sq<br>
     *              Sq<br>
     *          Sq  Sq<br>
     */
    private void rotate4(){
        //check square[3] if can move up then right, if so move, if not return to previous state
        if(!square[3].canMove(Game.UP)){
            return;
        }
        square[3].move(Game.UP);     
        if(!square[3].canMove(Game.RIGHT)){
            square[3].move(Game.DOWN); //undo move up
            return;
        }
        square[3].move(Game.RIGHT); 

        //update rotatePos only after piece can move twice 
        rotatePos=0; 

        //check square[0] if can move left two places if so move if not return to previous state
        if(!square[0].canMove(Game.LEFT)){
            return;
        }
        square[0].move(Game.LEFT);
        if(!square[0].canMove(Game.LEFT)){
            square[0].move(Game.RIGHT);//undo move left
            return;
        }
        square[0].move(Game.LEFT);

        //check square[1] if can move down then left, if so move, if not return to previous state
        if(!square[1].canMove(Game.DOWN)){
            return;
        }
        square[1].move(Game.DOWN);     
        if(!square[1].canMove(Game.LEFT)){
            square[1].move(Game.UP); //undo move down
            return;
        }
        square[1].move(Game.LEFT); 

    }
}
