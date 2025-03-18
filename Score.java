public class Score {
    private final int PUZZLE_VALUE = 10;
    private int Rooms_visited;
    private int startingScore;
    private int puzzles_solved;

    public Score(int startingScore){
        //constructor that initializes the score object
        this.startingScore = startingScore;
        Rooms_visited = 0;
        puzzles_solved = 0;
    }

    public void visitRoom(){
        Rooms_visited++;
    }

    public void solvePuzzle(){
        puzzles_solved++;
    }

    public double getScore(){
        //calculates the starting score and returns it
        double score = (startingScore-Rooms_visited)+(puzzles_solved*PUZZLE_VALUE);
        return score;
    }

}