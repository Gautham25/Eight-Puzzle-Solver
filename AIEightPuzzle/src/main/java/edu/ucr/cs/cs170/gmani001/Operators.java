package edu.ucr.cs.cs170.gmani001;

public class Operators {
    int opPuzzle[][];
    int g, search;
    int temp1, temp2;
    private int puzzleTemp[][] = new int[3][3];

    //Initialize operators
    public Operators(int puzzle[][], int cost, int userChoice){
        duplicatePuzzle(puzzle);
        this.opPuzzle = puzzleTemp;
        this.g = cost;
        this.search = userChoice;
    }

    //Check if moving up is possible
    public boolean checkMoveUp(){
        for(int i=0;i<opPuzzle.length;i++){
            for(int j=0;j<opPuzzle.length;j++){
                if(opPuzzle[i][j] == 0){
                    if(i!=0){
                        temp1 = i;
                        temp2 = j;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Move puzzle piece up
    public Puzzle moveUp(){
        swap(temp1,temp2,temp1-1,temp2);
        return modPuzzle();
    }

    //Check if moving puzzle piece down is possible
    public boolean checkMoveDown(){
        for(int i=0;i<opPuzzle.length;i++){
            for(int j=0;j<opPuzzle.length;j++){
                if(opPuzzle[i][j] == 0){
                    if(i!=opPuzzle.length-1){
                        temp1 = i;
                        temp2 = j;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Move puzzle piece down
    public Puzzle moveDown(){
        swap(temp1,temp2,temp1+1,temp2);
        return modPuzzle();
    }

    //Check if moving puzzle piece left is possible
    public boolean checkMoveLeft(){
        for(int i=0;i<opPuzzle.length;i++){
            for(int j=0;j<opPuzzle.length;j++){
                if(opPuzzle[i][j] == 0){
                    if(j!=0){
                        temp1 = i;
                        temp2 = j;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Move puzzle piece left
    public Puzzle moveLeft(){
        swap(temp1,temp2,temp1,temp2-1);
        return modPuzzle();
    }

    //Check if moving puzzle piece right is possible
    public boolean checkMoveRight(){
        for(int i=0;i<opPuzzle.length;i++){
            for(int j=0;j<opPuzzle.length;j++){
                if(opPuzzle[i][j] == 0){
                    if(j!=opPuzzle.length-1){
                        temp1 = i;
                        temp2 = j;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Move puzzle piece left
    public Puzzle moveRight(){
        swap(temp1,temp2,temp1,temp2+1);
        return modPuzzle();
    }

    //Swap puzzle number
    public void swap(int i,int j, int i2, int j2){
        int temp;
        temp = opPuzzle[i][j];
        opPuzzle[i][j] = opPuzzle[i2][j2];
        opPuzzle[i2][j2] = temp;
    }

    //Change puzzle state based on move
    private Puzzle modPuzzle(){
        Puzzle tp = new Puzzle();
        tp.setPuzzle(opPuzzle);
        tp.setG(this.g + 1);
        if(search == 2){
            tp.misplacedTile();
        }
        if(search == 3){
            tp.manhattanCompute();
        }
        return tp;
    }

    //Get a replica of the puzzle state
    private void duplicatePuzzle(int puzzle[][]){
        for(int i=0;i<puzzle.length;i++){
            puzzleTemp[i]=puzzle[i].clone();
        }
    }

}
