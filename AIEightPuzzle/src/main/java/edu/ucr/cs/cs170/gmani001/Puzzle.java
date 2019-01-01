package edu.ucr.cs.cs170.gmani001;
import java.util.*;

public class Puzzle {
    private int puzzle[][];
    private int[][] goalState;
    private int g = 0;
    private int h = 0;
    List<Integer> list;
    Map<Integer,Integer> map = new HashMap<>();
    public Puzzle(){
        goalState = new int[][] {{1,2,3},{4,5,6},{7,8,0}};
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        map.put(6,6);
        map.put(7,7);
        map.put(8,8);
    }

    //Check if the puzzle is valid and solvable
    public boolean isCorrectPuzzle(){
        int misTile = 0;
        convertArraytoList();
        for (int i = 0; i < Math.pow(puzzle.length,2); i++){
            for (int j = i+1; j < Math.pow(puzzle.length,2); j++) {
                if(list.get(i) > list.get(j) && list.get(i) != 0 && list.get(j) !=0){
                    misTile += 1;
                }
            }
        }
        if(misTile%2 == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //Function to print puzzle
    public void printPuzzle(){
        for (int i =0; i < puzzle.length; i++){
            for (int j =0; j < puzzle.length; j++){
                System.out.print(puzzle[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    //Convert 2d puzzle into a single list
    public void convertArraytoList(){
        list = new ArrayList<Integer>();
        for (int i=0; i < this.puzzle.length; i++){
            for (int j=0; j < this.puzzle.length; j++){
                //Add each element of the puzzle to the list
                list.add(this.puzzle[i][j]);
            }
        }
    }

    //Get current puzzle state
    public int[][] getPuzzle(){
        return puzzle;
    }

    //Set puzzle state
    public void setPuzzle(int[][] puzzle){
        this.puzzle = puzzle;
    }

    //Get cost to path g(n)
    public int getG(){
        return g;
    }

    //Set cost to path g(n)
    public void setG(int cost){
        g = cost;
    }

    //Get heuristic for a given puzzle state - h(n)
    public int getH(){
        return h;
    }

    //Set heuristic for a given puzzle state - h(n)
    public void setH(int dist){
        h = dist;
    }

    //Compute misplaced tile
    public void misplacedTile(){
        int misTile = 0;
        convertArraytoList();
        for (int i=0; i<list.size();i++){
            if(list.get(i) != i+1){
                misTile+=1;
            }
        }
        setH(misTile-1);
    }

    //Compute Manhattan Distance
    public void manhattanCompute(){
        int manDist = 0;
        for(int i=0;i<puzzle.length;i++){
            for(int j=0;j<puzzle.length;j++){
                if (puzzle[i][j] == 0){
                    continue;
                }
                int x = (puzzle[i][j]-1)/puzzle.length;
                int y = (puzzle[i][j]-1)%puzzle.length;
                manDist += (Math.abs(x-i) + Math.abs(y-j));
            }
        }
        setH(manDist);
    }

    //Get total cost
    public int getCost(){
        return getG() + getH();
    }

    //Check if current puzzle state is goal state
    public boolean checkGoalState(){
        if(Arrays.deepEquals(this.puzzle,goalState))
            return true;
        return false;
    }

    //Override existing boolean equals operation with custom equals to check if Puzzle is of type object
    @Override public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Puzzle)){
            return false;
        }
        Puzzle tempP = (Puzzle) obj;
        return Arrays.deepEquals(puzzle, tempP.puzzle);
    }

    @Override public int hashCode(){
        return Arrays.hashCode(puzzle);
    }
}
