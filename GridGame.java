/**
 *
 * @author MHilmi
 */
public class GridGame {
    // defining itemTaken and item as static to use in the methods
    static int itemTaken = 0;
    static String item = "*";
    public static void main(String[] args) {
        //printing entress and take an input grid size from the user
        System.out.println("WELCOME TO THE GRID GAME\n");
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.print("Please enter a Grid size: ");
        //input type is string firstly, to avoid an error 
        String input = scan.next();
        //then input check method to check input is valid or not
        while(!inputCheck(input)){
            input = scan.next();
        }
        //if input is valid, then convert string input to integer grid size
        Integer grid = Integer.valueOf(input);
        System.out.printf("--Hello %d X %d Grid Game--\n",grid,grid);
        //define two dimensional gridArea array and first argument is "X"
        String gridArea [][] = new String [grid][grid]; gridArea[0][0] = "X";
        //move counter is max move count
        int moveCounter = (int)(grid * 2.5);
        //itemCount is number of items in the area and itemCounter to set them with correct number
        int itemCounter = 0, itemCount = grid/2;
        //randomRow and randomColum variables for item's random places
        int randomRow = 0,randomColumn = 0;
            //start the game with max move count till you win or game over
             while(moveCounter>= 0){
                 /* set items and preparing the area*/
            for (String[] gridArea1 : gridArea) {
                for (int column = 0; column < gridArea.length; column++) {
                    //setting items untill max item count
                    while(itemCounter < itemCount){
                        //picking a place randomly for items 
                        randomRow = (int)(Math.random() * grid);
                        randomColumn = (int)(Math.random() * grid);
                        //setting items to the array
                        gridArea[randomRow][randomColumn] = item;
                        //cause of in 0,0 location has "X", if item is in there, then print a message 'i got it' increase the number of taken items
                        if(randomRow == 0 && randomColumn == 0) {
                            gridArea[0][0] = "X";
                            System.out.println("first item was taken.."); itemTaken++;}
                        itemCounter++;}
                    //then set space to the rest area of the array
                    if (gridArea1[column] == null) gridArea1[column] = " ";
                }
            }
            //printing array
            for (String[] gridArea1 : gridArea) {
                System.out.print("|");
                for (int column = 0; column < gridArea.length; column++) {
                    System.out.print(gridArea1[column]);
                    //cause of no space next to the walls
                    if(column!=gridArea.length-1) System.out.print(" ");
                }
                System.out.print("|");
                System.out.println();
            }
            //printing count of moves and current place with currentCell method and taken items
        System.out.print("Moves:" + moveCounter); currentCell(gridArea);
                 System.out.println("\titem taken: " + itemTaken);
        //taking an direction input from the user to move to the X
        System.out.print("Choose your direction(R,L,U,D):"); 
                    String direction = scan.next();
                    //switch case situatinos with locationUpdate methods according to the directions
                    switch(Character.toUpperCase(direction.charAt(0))){
                        case 'R':locationUpdate(gridArea,'R'); break;
                        case 'L':locationUpdate(gridArea,'L'); break;
                        case 'U':locationUpdate(gridArea,'U'); break;
                        case 'D':locationUpdate(gridArea,'D'); break;
                        //input is invalid then print a message
                        default: System.out.println("Please enter a valid value!");
                        //increase the moveCounter in default situation to not changing to count of moves 
                        moveCounter++; break;
                    }
        //decrease the moveCounter for every step
        moveCounter--;
            //if all items are taken, then print a message and finish the game with win
             if(itemTaken == itemCount) {System.out.println("YOU WİN!!"); System.exit(0);}
             //if all items are not taken until move counter is 0, then finish the game with failure and print the message
            if(moveCounter == 0) {System.out.println("---GAME OVER---"); System.exit(0); }
    }
       }
    //takes array and the direction user choose
    static void locationUpdate(String gridArea[][],char direction){
        int i = 0; int j = 0;
        for(int row = 0; row < gridArea.length; row++)
            for(int column = 0; column < gridArea.length; column++)
                //find where x is and to avoid outofbounds condition 
                if(gridArea[row][column].equals("X") && row != gridArea.length && column!= gridArea.length){
                    i = row; j = column;
                }
        //move features according to the ways
        switch(Character.toUpperCase(direction)){
            /*right direction conditions and setting the array*/
            case 'R': if(j == gridArea.length - 1) { gridArea[i][j] = "+";
                j = 0; if(gridArea[i][j].equals(item)) itemTaken++; gridArea[i][j] = "X";break; // without wall situation (enter one side, exit the opposite side).
            }// {System.out.println("Invalid move, you cant do there!"); break;} // wall situation
            if(gridArea[i][j+1].equals(item)) itemTaken++;
            else if(gridArea[i][j+1].equals("+")){System.out.println("You can't go on yourself!"); break;}
                gridArea[i][j] = "+";gridArea[i][j + 1] = "X";
                 break;
            /*left direction conditions and setting the array*/
            case 'L': if(j == 0) {gridArea[i][j] = "+"; j = gridArea.length-1;
            if(gridArea[i][j].equals(item)) itemTaken++; gridArea[i][j] = "X"; break;//without wall situation 
            }//{System.out.println("Invalid move,you can't go there!"); break;}
             if(gridArea[i][j-1].equals(item)) itemTaken++;
            else if(gridArea[i][j-1].equals("+")){System.out.println("You can't go on yourself!"); break;}
                gridArea[i][j] = "+";gridArea[i][j - 1] = "X";
                 break;
            /*up direction conditions and setting the array*/
            case 'U': if(i == 0) {gridArea[i][j] = "+"; i = gridArea.length-1;
            if(gridArea[i][j].equals(item))itemTaken++; gridArea[i][j] = "X"; break; //without wall situation 
            }//{System.out.println("Invalid move,you can't go there!"); break;}
            if(gridArea[i-1][j].equals(item))itemTaken++;
            else if(gridArea[i-1][j].equals("+")){System.out.println("You can't go on yourself!"); break;}
                gridArea[i][j] = "+";gridArea[i-1][j] = "X";
                break;
            /*down direction conditions and setting the array*/
            case 'D': if(i == gridArea.length -1) {gridArea[i][j] = "+"; i = 0;
            if(gridArea[i][j].equals(item))itemTaken++; gridArea[i][j] = "X"; break; //without wall situation 
            }//{System.out.println("Invalid move,you can't go there!");break;}
            if(gridArea[i+1][j].equals(item))itemTaken++;
            else if(gridArea[i+1][j].equals("+")){ System.out.println("You can't go on yourself!");break;}
            gridArea[i][j] = "+";gridArea[i+1][j] = "X";
                break;
        }
    }
    //cuurent cell method to find the location of row and column
    static void currentCell(String gridArea[][]){
        for(int row = 0; row < gridArea.length; row++)
            for(int column = 0; column < gridArea.length; column++)
                if(gridArea[row][column].equals("X") && row != gridArea.length && column!= gridArea.length)
                    System.out.printf("\tCurrent Cell: (%d, %d) ",row,column);
    }
    //input check method to avoid an error and check whether the input is valid or not, if not print a message and ask again.  
    static boolean inputCheck(String input){
       try{
            Integer var = Integer.valueOf(input);
            if(var > 0)return true;
            else System.out.print("Please enter a positive grid size: ");
        }
        catch(NumberFormatException ex){
             System.out.print("Please enter an integer: ");
         }
        return false;
    }
}
