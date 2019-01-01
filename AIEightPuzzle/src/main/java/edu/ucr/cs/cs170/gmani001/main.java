package edu.ucr.cs.cs170.gmani001;
import java.util.PriorityQueue;
import java.util.Scanner;

public class main {
    private static Puzzle puzzle = new Puzzle();
    private static PriorityQueue<Puzzle> queue;
    public static void main(String[] args){
        System.out.println("Welcome to Gautham\'s 8-puzzle solver");
        System.out.println("Type \"1\" to use a default puzzle, or \"2\" to enter your own puzzle.");
        Scanner s = new Scanner(System.in);
        int userChoice = s.nextInt();

        if (userChoice == 1){
           if(! defaultGameGen())
               return;
        }
        else if(userChoice == 2){
            customGameGen();
        }
        else{
            System.out.println("Invalid choice. Restart game");
            return;
        }

        //Check if game is solvable or proper
        if(puzzle.isCorrectPuzzle()){
            SearchAlgos search = new SearchAlgos(puzzle);
            System.out.println("Enter your choice of algorithm");
            System.out.println("1. Uniform Cost Search");
            System.out.println("2. A* with the Misplaced Tile heuristic.");
            System.out.println("3. A* with the Manhattan distance heuristic.");
            userChoice = s.nextInt();

            if (userChoice == 1){
                puzzle.printPuzzle();
                search.generalSearch(queue,1);
            }
            else if(userChoice == 2){
                puzzle.printPuzzle();
                search.puzzle.misplacedTile();
                search.generalSearch(queue,2);
            }
            else if(userChoice == 3){
                puzzle.printPuzzle();
                search.puzzle.manhattanCompute();
                search.generalSearch(queue, 3);
            }
            else {
                System.out.println("Invalid Choice");
            }
        }
        else{
            System.out.println("Invalid game entered or Game cannot be solved. Restart game");
        }

        s.close();
    }

    //Generate initial state for default game
    private static boolean defaultGameGen(){
        System.out.println("Please select the level of difficulty \n0 - Trivial\n1 - Very Easy\n2 - Easy\n3 - Medium\n4 - Hard\n5 - Very Hard");
        int dStateTrivial[][] = new int[][] {{1,2,3},{4,5,6},{7,8,0}};
        int dStateVeryEasy[][] = new int[][] {{1,2,3},{4,5,6},{7,0,8}};
        int dStateEasy[][] = new int[][] {{1,2,0},{4,5,3},{7,8,6}};
        int dStateMedium[][] = new int[][] {{0,1,2},{4,5,3},{7,8,6}};
        int dStateHard[][] = new int[][] {{8,7,1},{6,0,2},{5,4,3}};
        int dStateVeryHard[][] = new int[][] {{1,2,3},{4,5,6},{8,7,0}};
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        switch(c){
            case 0 :    puzzle.setPuzzle(dStateTrivial);
                break;
            case 1 :    puzzle.setPuzzle(dStateVeryEasy);
                break;
            case 2 :    puzzle.setPuzzle(dStateEasy);
                break;
            case 3 :    puzzle.setPuzzle(dStateMedium);
                break;
            case 4 :    puzzle.setPuzzle(dStateHard);
                break;
            case 5 :    puzzle.setPuzzle(dStateVeryHard);
                break;
            default:    System.out.println("Invalid option. Please restart game");
                return false;
        }
        System.out.println("Default game is being generated. Please wait...");
        return true;


    }

    //Set initial state for custom game
    private static void customGameGen(){
        System.out.println("Enter your puzzle, use zero(0) to represent the blank");
        Scanner s = new Scanner(System.in);
        int cState[][] = new int[3][3];
        for (int i=0;i<3;i++){
            if(i==0){
                System.out.println("Enter the first row, use space or tabs between numbers");
            }
            else if(i==1){
                System.out.println("Enter the second row, use space or tabs between numbers");
            }
            else{
                System.out.println("Enter the third row, use space or tabs between numbers");
            }
            for(int j=0; j<3;j++){
                cState[i][j]=s.nextInt();
            }
        }
        puzzle.setPuzzle(cState);
    }
}
