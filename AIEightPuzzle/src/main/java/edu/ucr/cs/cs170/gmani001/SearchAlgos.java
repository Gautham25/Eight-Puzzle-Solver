package edu.ucr.cs.cs170.gmani001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SearchAlgos {
    Puzzle puzzle;
    private Operators op;

    public SearchAlgos(Puzzle puzzleInitial){
        this.puzzle = puzzleInitial;
    }

    //Generalized search algorithm
    public void generalSearch(PriorityQueue<Puzzle> queue, int userChoice){
        //Initialize Priority Queue used to store nodes based on total cost
        queue = new PriorityQueue<Puzzle>(new PuzzleStateComparator());
        List<Puzzle> visitedState = new ArrayList<>();
        queue.offer(puzzle);
        int expandedNodes = 0;
        int maxExpandedNodes = 0;
        while(true){

            //Check if queue is empty
            if(queue.isEmpty()){
                System.out.println("No solution found");
                break;
            }

            //Check for max nodes expanded
            if(queue.size() > maxExpandedNodes){
                maxExpandedNodes = queue.size();
            }
            Puzzle node = queue.remove();

            //Check if node has already been visited
            if(visitedState.contains(node)){
                System.out.println("The best state to expand with g(n)="+node.getG()+" and h(n)="+node.getH());
                node.printPuzzle();
                System.out.println("Visited");
                System.out.println("\n");
                continue;
            }

            //Check if node expanded is goal state
            if(node.checkGoalState()){
                System.out.println("Goal!!!");
                System.out.println("To solve this problem the search algorithm expanded a total of "+expandedNodes+" nodes");
                System.out.println("The maximum number of nodes in the queue at any one time was "+maxExpandedNodes);
                System.out.println("The depth of the goal node was "+(node.getG()));
                node.printPuzzle();
                break;
            }

            //For nodes which are not goal state
            if(queue.size()!=0){
                System.out.println("The best state to expand with g(n)="+node.getG()+" and h(n)="+node.getH());
                node.printPuzzle();
            }
            System.out.println("\n");


            op = new Operators(node.getPuzzle(), node.getG(), userChoice);
            if(op.checkMoveUp()){
                queue.offer(op.moveUp());
                expandedNodes+=1;
            }

            op = new Operators(node.getPuzzle(), node.getG(), userChoice);
            if(op.checkMoveDown()){
                queue.offer(op.moveDown());
                expandedNodes+=1;
            }

            op = new Operators(node.getPuzzle(), node.getG(), userChoice);
            if(op.checkMoveLeft()){
                queue.offer(op.moveLeft());
                expandedNodes+=1;
            }

            op = new Operators(node.getPuzzle(), node.getG(), userChoice);
            if(op.checkMoveRight()){
                queue.offer(op.moveRight());
                expandedNodes+=1;
            }
            //Add node to visited state
            visitedState.add(node);
        }
    }
}

//Comparator used to compare cost of puzzles in queue and order in ascending order
class PuzzleStateComparator implements Comparator<Puzzle>{
    @Override public int compare(Puzzle p1, Puzzle p2){
        if(p1.getCost() > p2.getCost())
            return 1;
        else
            return -1;
    }
}
