public class Map {
    final private char EMPTY = '.';
    Room[][] map;
    private int width;
    private int height;


    public Map(int width, int height){
        this.width = width;
        this.height = height;
        //creates a 2D array using a room object as it data type
        map = new Room[width][height];

        for(int i=0; i<width;i++){
            for(int j = 0;j<height;j++){
                //populates the 2D array making them empty with the EMPTY SYMBOL
                map[i][j] = new Room("empty room","",EMPTY,new Position(i,j));
            }
        }
    }

    public void placeRoom(Position pos, char symbol){
        //replaces an existing room, with a custom room that has a symbol and position
        map[pos.x][pos.y] = new Room("","",symbol,pos);
    }

    public String display(){
        String display="";
        for(int j=0; j<height;j++){
            for(int i = 0;i<width;i++){
                //flips around i and j in the for loops so that it searches accross the 2D array instead of through it
                display = display+map[i][j].getSymbol();
            }
            display = display+"\n";
        }
        return display;
    }
}
