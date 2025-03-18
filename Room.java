public class Room {
    private String name;
    private String description;
    private char symbol;
    private Position position;
    //made custom attributes:visited and completed so that everytime the user visits a new room the score gets punished, and to be able to sett completd once the user finsishes the puzzle in the room
    private boolean visited;
    private boolean complete;

    public Room(String name, String description, char symbol, Position position){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
        visited = false;
        complete = false;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
    //made custom setters to be able to change description of rooms from the game class when starting up the program
    public void setDescription(String desc){
        description = desc;
    }
    public void setName(String name){
        this.name = name;
    }

    public char getSymbol(){
        return symbol;
    }

    public Position getPosition(){
        return position;
    }

    //added custom checkers and completed methods to be able to change value of visited and completed booleans to print custom messages in the game class
    public boolean checkVisited(){
        return visited;
    }
    public void changeVisited(){
        visited = true;
    }
    public boolean checkCompleted(){
        return complete;
    }
    public void changeCompleted(){
        complete = true;
    }
}
