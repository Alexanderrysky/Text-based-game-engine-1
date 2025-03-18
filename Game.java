
import java.util.Scanner;
public class Game {

    public static void commands(){
        System.out.println("The commands you can use are");
        System.out.println("'Commands' - Displays the commands you can use");
        System.out.println("'move <direction>' - (<direction> can be 'north', 'south', 'east', 'west'). The player moves to a new room based on the direction");
        System.out.println("'Look'. Displays a description of the room you are in");
        System.out.println("'Look <feature>'. Displays a more detailed description of the feature of a room(must be in same room as feature)");
        System.out.println("'Look <item>'. Displays a description of an item in your inventory");
        System.out.println("'inventory'. Displays a list of all items you have in your inventory");
        System.out.println("'Use. Asks you what item you want to use in the current room then attempts to use it");
        System.out.println("'score'. Displays your current score");
        System.out.println("'map'. Displays a text-based map of the current explored game world");
        System.out.println("'help'. gives a hint of what to do in a room");
        System.out.println("'quit'. quits the game");  
    }

    /*all the move functions take the full_map,user_map,user_position and score as attributes, 
    it checks if the user is at the borders of the map, if he is, his position isnt changed,if he 
    isnt at the border he is allowed to move and his position is changed */
    public static void move_north(Map full_map,Map user_map, Position pos,Score score){
        if (pos.y>0){
            pos.y--;
            //after the position change it checks if the user has been to the room before
            if(!full_map.map[pos.x][pos.y].checkVisited()){
                full_map.map[pos.x][pos.y].changeVisited();
                //if he hasnt the room is appednded to his own map which is what is printed
                user_map.placeRoom(pos, full_map.map[pos.x][pos.y].getSymbol());
                score.visitRoom();
            }
            System.out.println("You have moved north");

        }else{
            System.out.println("You cannot move north anymore");
        }
    }

    public static void move_south(Map full_map,Map user_map, Position pos,int hieght,Score score){
        if (pos.y<(hieght-1)){
            pos.y++;
            if(!full_map.map[pos.x][pos.y].checkVisited()){
                full_map.map[pos.x][pos.y].changeVisited();
                user_map.placeRoom(pos, full_map.map[pos.x][pos.y].getSymbol());
                score.visitRoom();
            }
            System.out.println("You have moved south");

        }else{
            System.out.println("You cannot move south anymore");
        }
    }

    public static void move_east(Map full_map, Map user_map, Position pos,int width,Score score){
        if (pos.x<(width-1)){
            pos.x++;
            if(!full_map.map[pos.x][pos.y].checkVisited()){
                full_map.map[pos.x][pos.y].changeVisited();
                user_map.placeRoom(pos, full_map.map[pos.x][pos.y].getSymbol());
                score.visitRoom();
            }
            System.out.println("You have moved east");

        }else{
            System.out.println("You cannot move east anymore");
        }
    }

    public static void move_west(Map full_map,Map user_map, Position pos,Score score){
        if (pos.x>0){
            pos.x--;
            if(!full_map.map[pos.x][pos.y].checkVisited()){
                full_map.map[pos.x][pos.y].changeVisited();
                user_map.placeRoom(pos, full_map.map[pos.x][pos.y].getSymbol());
                score.visitRoom();
            }
            System.out.println("You have moved west");

        }else{
            System.out.println("You cannot move west anymore");
        }
    }

    public static void Look(Map full_map,Position pos){
        //displays the description of the room using full_map array and 
        String description = full_map.map[pos.x][pos.y].getDescription();
        //the description of the room is returned the printed
        System.out.println(description);
    }

    public static boolean Look_feature(Map full_map,Position pos, char symbol){
        //gets room symbol of the current room the user is in and compared it to the symbol in the parameters
        char temp = full_map.map[pos.x][pos.y].getSymbol();
        if (temp == symbol){
            return true;
        }
        return false;
    }

    public static boolean look_item(Inventory inv, String item){
        //makes sure hasItem doesnt return a -1 on the String parameter, if it doesnt then returns true
        if (inv.hasItem(item) != -1){
            return true;
        }else{
            System.out.println("item: "+item + " not in inventory");
            return false;
        }
    }
    
    public static boolean use_item(Map map, Position pos,Inventory inv, Score score){
        //creats a new scanner object allowing users to input the item they want to use
        Scanner input = new Scanner(System.in);
        System.out.println("Adventurer please enter the item you want to use: "+"\n" +inv.displayInventory());
        String user_item = input.nextLine();
        //checks if the user has the item they want to use in their inventory
        if(inv.hasItem(user_item)!=-1){
            //checks if the user is using an item in the correct room
            if(user_item.equals("Rusty key") && map.map[pos.x][pos.y].getSymbol() == 'L'){
                //checks if the user has already completed the room
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("You put the Rusty key into the door and arent suprised when it fits looking through the library you come across you come across a book of jokes, it amuses you so you decide to take it");
                    inv.addItem("Book of jokes");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                //sometimes when the user completeds a room the item isnt removed from his inventory, so an else is used to tell the user he has already completed the room with the needed item
                }else{
                    System.out.println("Ive already used the Rusty key here i dont think its gonna help to use again");
                }
            }else if(user_item.equals("Apple pie") && map.map[pos.x][pos.y].getSymbol() == 'S'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("You present the Apple pie to the horse 'WOW i didnt expect that but non the less HAND IT OVER!!!!!' it then quickly lunges its head stealing the pie from you and scarfs it down(your a little sad you didnt get to try any) it then thanks you for the meal and takes the rusty key in its teeth and presents it to you. You take it");
                    inv.removeItem(user_item);
                    inv.addItem("Rusty key");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }
            }else if(user_item.equals("Fishing rod") && map.map[pos.x][pos.y].getSymbol() == 'W'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("You cast your line and a few minuts pass, when suddenly you feel a tug, a strong tug you pull hard and you fear the line may break but to your suprise you pull up a Slimey fish");
                    inv.addItem("Slimey fish");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }else{
                    System.out.println("Ive already caught a fish it would be a waste of energy to do it again");
                }
            }else if(user_item.equals("Axe") && map.map[pos.x][pos.y].getSymbol() == 'R'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("as you approach the beaver says 'Ahhhh you've returned and with a shiny thingy on a stick.... well come along and cut my tree'. you oblige cutting with fever until you hear cracking and hear a loud chrash as the tree falls into the river.'well my my, i cant you've done it my dear chap. Well as promised here you go' he says as he hands you a car key");
                    inv.addItem("Car key");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }else{
                    System.out.println("Ive already cut down the Tree");
                }
            }else if(user_item.equals("Rock") && map.map[pos.x][pos.y].getSymbol() == 'C'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("You hurl the rock through the window feeling satisfied when you hear the crash of glass you then quickly reach through and grab the apple pie carefule to not snag yourself on the broken glass");
                    inv.removeItem(user_item);
                    inv.addItem("Apple pie");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }
            }else if(user_item.equals("Radio") && map.map[pos.x][pos.y].getSymbol() == 'P'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("You aproach the sound whimpering of which you've already heard as you do so you turn on the radio and turn it on, immediatly the ogre looks up and smiles then says to you 'my swamp lies still no fish in sight,but music will fill the air tonight, for this joy a gift il share, a fishing rod use it with gentle care'.he concludes by handing you his fishing rod.");
                    inv.removeItem(user_item);
                    inv.addItem("Fishing rod");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }
            }else if(user_item.equals("Crowbar") && map.map[pos.x][pos.y].getSymbol() == 'U'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("as you approach the Ruins you use the crowbar to pry open the barricaded entrance, entering you find what apears to be a loung but you wouldnt be able to find yourself relaxing here on acount of the dust. You notice quite a few loose papers around picking one up and reading you find yourself reading an old poem, realising this you hurriedly pick all the papers you can see and put them together.");
                    inv.addItem("Old poem");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }else{
                    System.out.println("Ive already opened the ruins");
                }
            }else if(user_item.equals("Slimey fish") && map.map[pos.x][pos.y].getSymbol() == 'A'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("as you approach the cave you hear a rumbling voice 'so you're back... goten anythin for me to eat' you throw the fish into the cave, then after a second hear the worst sound you've ever heard. Shortly after the sound stops an crowbar comes sliding out of the cave, you pick it up and wonder if this was a waste of your time.");
                    inv.removeItem(user_item);
                    inv.addItem("Crowbar");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }
            }else if(map.map[pos.x][pos.y].getSymbol() == 'A' && map.map[pos.x][pos.y].checkCompleted()){
                System.out.println("You hear the bear say 'go away stop botherin me'");
            }else if(user_item.equals("Book of jokes") && map.map[pos.x][pos.y].getSymbol() == 'M'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("as you approach the Scarecrow his head shoots up and looks at you 'ahh its you adventurer, got any jokes for me?' you read him a few jokes and you two share a laugh.'Thanks for that hadnt had a good laugh in a while, feel free to take this thing from my chest,i can tell you want it',you happily take the invitaton and rip the axe from the scarecrow.");
                    inv.addItem("Axe");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }else{
                    System.out.println("You share another joke with the scarecrow and laugh");
                }
            }else if(user_item.equals("Car key") && map.map[pos.x][pos.y].getSymbol() == 'B'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    System.out.println("as you approach the rusted car you can tell the man inside grows with excitment as he sees you as aproaching with car keys. You use them to open the door and the man immediately bolts you probably wont see him again.");
                    inv.addItem("Radio");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                }else{
                    System.out.println("You look at the now empty car, theres nothing you can do with the key");
                }
            }else if(user_item.equals("Old poem") && map.map[pos.x][pos.y].getSymbol() == 'T'){
                if(!map.map[pos.x][pos.y].checkCompleted()){
                    inv.removeItem(user_item);
                    System.out.println("as you approach the Tower you are again stoped by the armour but you pay it no heed throwing its old poem that you have pieced together at it, it looks at its old poem frozen but you do not linger. You enter the tower and run up the stiars until you get to a ornate door, flinging it open you find laying in bed ................................................................................................................................................ A wolf in a pink shower cap and dress reading a magazine 'what?' it asks.");
                    System.out.println("\n\n\n\n\n and that was the end of your adventure");
                    score.solvePuzzle();
                    map.map[pos.x][pos.y].changeCompleted();
                    return true;
                }
            }else{
                System.out.println("Sorry you cannot use this item in your current area");
            }
        }else{
            System.out.println(user_item + " is not in your inventory");
        }
        return false;
    }

    public static void help(Map full_map,Position pos){
        //checks if the specific room the user wants help for is already completed
        if(!full_map.map[pos.x][pos.y].checkCompleted()){
            //uses a switch case for the symbol of the users_current room
            switch(full_map.map[pos.x][pos.y].getSymbol()){
                case 'F':
                    System.out.println("Theres something about that forrest maybie i should look at it");
                    break;
                case 'L':
                    System.out.println("Theres got to be something interesting to read in the library the key hole is quit old and rusty");
                    break;
                case 'S':
                    System.out.println("That talking wants to eat something appely maybie someone cooked something");
                    break;
                case 'W':
                    System.out.println("The lake has something moving at the bottom it may be usefull to have it, i need to catch it somehow");
                    break;
                case 'R':
                    System.out.println("That beaver felow seems to want to cut down that tree i may need to find something sharp");
                    break;
                case 'C':
                    System.out.println("That apple pie might be usefull to have, i have to get through this window somehow");
                    break;
                case 'P':
                    System.out.println("That rhyming ogre seems sad,those birds singing songs seemed to cheer him up,i should do so as well");
                    break;
                case 'U':
                    System.out.println("The only entrance to these ruins is borded up, i need something to pry them open");
                    break;
                case 'A':
                    System.out.println("That bear seemed hungry something to eat may calm him down");
                    break;
                case 'M':
                    System.out.println("That scarecrow seemed like he could use a good joke or two. Maybie even more");
                    break;
                case 'B':
                    System.out.println("That guy seemed to be locked in his car, i should look out for the key");
                    break;
                case 'T':
                    System.out.println("That armour will let me pass if i can help him remember his favourite poem");
                    break;
                case '.':
                    System.out.println("Theres nothing to do here");
                    break;
            }
        }else{
            System.out.println("Ive already done everything i can do in this area");
        }
    }
    public static void Roomsetter(Room room, char symbol){
        //checks the symbol that was being placed into room and uses it to set name and description
        switch(symbol){
            case 'F':
                room.setName("Forest");
                room.setDescription("Strangely you see a forest in front of you");
                break;
            case 'L':
                room.setName("Library");
                room.setDescription("You find a building in a field with grates on the windows looking inside you can see a library");
                break;
            case 'S':
                room.setName("Stable");
                room.setDescription("You come accross a stable, that smells of manour");
                break;
            case 'W':
                room.setName("Lake");
                room.setDescription("You come accross a stale lake that has some moss at the edges");
                break;
            case 'R':
                room.setName("River");
                room.setDescription("You come across a bubbling brooke with fishes jumping ");
                break;
            case 'C':
                room.setName("Cottage");
                room.setDescription("You come across a small cottage with smoke still coming out the chimney");
                break;
            case 'P':
                room.setName("Swamp");
                room.setDescription("You come across a swamp, you can see slugs crawling out");
                break;
            case 'U':
                room.setName("Ruins");
                room.setDescription("You come across old ruins, be carefule they look unstable");
                break;
            case 'A':
                room.setName("Cave");
                room.setDescription("You come across a cave, you hear a growel from within");
                break;
            case 'M':
                room.setName("Farm");
                room.setDescription("You come across a Farm, you see a scarecrow in a field of corn(you can swear you saw it move)");
                break;
            case 'B':
                room.setName("Ruined Car");
                room.setDescription("You come across a rusted car, there may be something to savage");
                break;
            case 'T':
                room.setName("Tower");
                room.setDescription("This is the tower you were told of, the princess is here");
                break;
            case 'X':
                room.setName("Meadow");
                room.setDescription("An empty meadow with nothing of interest");
                break;
        }
    }

    // is the main program
    public static void main(String[] args) {
        //sets the width and hieght
        int width = 4;
        int hieght = 4;
        //sets the starting score
        Score score = new Score(50);
        //sets the custom symbols that descriptions 
        char[] map_symbols = {'F','L','S','W','R','C','P','U','A','M','B','T'};
        //sets the full_map which controls and stores all the description, position, symbol and name of the room
        Map full_map = new Map(width,hieght);
        //the user map is empty only storing symbol to be used in display map
        Map user_map = new Map(width,hieght);
        int symbol_count = 0;
        //uses for loop to create and set values into map array
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                Position temp_pos = full_map.map[j][i].getPosition();
                if (symbol_count<12){
                    //replaces empty room object in map array with room that has the correct symbol and possition
                    full_map.placeRoom(temp_pos,map_symbols[symbol_count]);
                    //uses a switch case of the symbol to set the room name and description
                    Roomsetter(full_map.map[temp_pos.x][temp_pos.y],map_symbols[symbol_count]);
                    symbol_count++;
                }else{
                    //after the custom symbols run out map array is filled with basic symbols meant to reduce users score for exploring to much
                    full_map.placeRoom(temp_pos,'X');
                    Roomsetter(full_map.map[temp_pos.x][temp_pos.y],'X');
                }

            }
        }
        //sets the user info
        Position user_pos = (full_map.map[1][3]).getPosition();
        Inventory inv = new Inventory();
        System.out.println("Welcome to tower quest your job is to reach the princcess in the tower, can you do it?(probably), type Commands to see available comands");
        String command = "";
        Scanner input = new Scanner(System.in);
        //uses a nested switch in a while loop to take user commands
        while (!command.equals("quit")){
            command = input.nextLine();
            switch(command){
                case "Commands":
                    commands();
                    break;
                case "move north":
                    move_north(full_map,user_map,user_pos,score);
                    break;
                case "move south":
                    move_south(full_map,user_map,user_pos,hieght,score);
                    break;
                case "move east":
                    move_east(full_map,user_map,user_pos,width,score);
                    break;
                case "move west":
                    move_west(full_map,user_map,user_pos,score);
                    break;
                case "look":
                //takes the full/true map and user position
                    Look(full_map,user_pos);
                    break;
                case "look forest":
                    //confirms if the user is in the correct location to look at the feature
                    if(Look_feature(full_map,user_pos,'F')){
                        //uses a room attribute to check if the user has completed puzzle in the room
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the forrest you can see it is quit dense and in the bushes you see a grey Rock.....its calling to you,you pick it up");
                            //adds rock to users inventory then changes completness of room
                            inv.addItem("Rock");
                            full_map.map[user_pos.x][user_pos.y].changeCompleted();
                        }else{
                            //if the user has completed the puzzle in the room the description changes
                            System.out.println("Looking around the forest you no longer see a rock... because you already picked it up");
                        }
                    }
                    break;
                case "look library":
                    if(Look_feature(full_map,user_pos,'L')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the library you can see an old rusty door with no other entrances, the key hole of the door is quite large");
                        }else{
                            System.out.println("Walking back into the library you see the empty space on the shelf where your Book of jokes used to lie");
                        }
                    }
                    break;
                case "look stable":
                    if(Look_feature(full_map,user_pos,'S')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking into the stable you here a rumbling,walking in you hear 'hey you' turning around you see a horse in a pen behind it you see a big rusty key hanging on a nail, the horse says 'If you can give me something appely to eat i will give you the key hanging behind me'");
                        }else{
                            System.out.println("The horse looks content after eating that Apple pie, you're still sad you didnt get to try any");
                        }
                    }
                    break;
                case "look lake":
                    if(Look_feature(full_map,user_pos,'W')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the Lake you can see it quite murky, but you can make out some movement at the bottom");
                        }else{
                            System.out.println("Looking at the lake you can no longer see movement");
                        }
                    }
                    break;
                case "look river":
                    if(Look_feature(full_map,user_pos,'R')){
                        if(full_map.map[user_pos.x][user_pos.y].checkCompleted() == false){
                            System.out.println("Looking at the River you hear the unmistakable voice of Matt Berry 'OI YOU YOU JUST GONNA STAND THERE OR ARE YOU GONNA COME HELP ME' turning around you see a beaver standing next to a tree that has it bark stripped near its base he says 'my word ever since my teeth fell out i am unable to build a damn,...tell you what if you can nock this tree down il give you shiny key' unmistakabaly you can tell its a car key");
                        }else{
                            System.out.println("Looking at the river, it no longer flows thanks to the tree that you fell");
                        }
                    }
                    break;
                case "look cottage":
                    if(Look_feature(full_map,user_pos,'C')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the Cottage you see a window, peering through you can see a freshly baked pie and from the billowing chimney you can smell apples");
                        }else{
                            System.out.println("you see the broken window and can hear some shrill screaming,you think it better to not get to close");
                        }
                    }
                    break;
                case "look swamp":
                    if(Look_feature(full_map,user_pos,'P')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the Swamp you can hear someone or something whimpering looking around you come across an ogre, when you ask whats wrong he tells you 'My swamp has dried, my fish have died,i bide my time, alone ,resigned,with only the song of birds to ease my mind'. \nYou ask if 'if i can make you feel better will you give me your fishing rod' to which he nods");
                        }else{
                            System.out.println("Nearing the swamp you no longer hear weeping but the sound of music eminating from the radio");
                        }
                    }
                    break;
                case "look ruins":
                    if(Look_feature(full_map,user_pos,'U')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the Ruins you can tell they are quite old, the only entrance you can see is borded up");
                        }else{
                            System.out.println("Looking at the ruins theres no longer anything of interest inside");
                        }
                    }
                    break;
                case "look cave":
                    if(Look_feature(full_map,user_pos,'A')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the cave you are suprised by a voice 'get away from my cave' looking closer you can see a bear lying on a pile of bones knawing on one of them, in the pile of bones you see something shine, you ask the bear for it, looking annoyed he replies 'if it will make you go away il let you take it if you bring me something to eat'");
                        }else{
                            System.out.println("You come close to the cave and hear snoring you dont think you should wake it up");
                        }
                    }
                    break;
                case "look farm":
                    if(Look_feature(full_map,user_pos,'M')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the farm, you see a scarecrow with an axe wedged in its chest, walking closer it shoots its head up and looks at you 'hey there' it says 'wanna hear a joke', 'yes' you reply, it then says 'well i would tell ya if a knew one' 'make one up' you say,'i would but my head of hay isnt great at thinking...tell you what give me some jokes and il give you this thing in my chest'");
                        }else{
                            System.out.println("Looking at the farm you see the scarecrow swaying side to side and giggling to himself");
                        }
                    }
                    break;
                case "look rusted car":
                    if(Look_feature(full_map,user_pos,'B')){
                        if(!full_map.map[user_pos.x][user_pos.y].checkCompleted()){
                            System.out.println("Looking at the Car you can make out someone inside of it, when you get closer, they immediatly spot you and wave for you to get closer apun doing so they start ranting 'Hey you can you help me, i was given the royal shaft when i had leave the car to use the can, when i came back i noticed my key gone from the ignitation and the doors locked when i closed them, stucking me in this chariot, when i looked i saw a beaver running away. CAN YOU HELP ME'. Feeling bad you take note to look out for some car keys");
                        }else{
                            System.out.println("You know look at an empty car with nothing of value");
                        }
                    }
                    break;
                case "look tower":
                    if(Look_feature(full_map,user_pos,'T')){
                        System.out.println("THIS IS IT, THIS IS THE TOWER WHERE THE PRINCCES IS BEING HELD. You quickly strid over but are stoped in your tracks by a rusted and anciant looking suit of armour. Asking whats wrong it informs you its boss the dragon said no visators, you ask if theres anything you can do an he replies 'well there is something troubling me i know of this poem, its almost as old as me but for the life of me i cant remember the words, if you cant find it and remind me of it, i will turn the other eye and let you pass'");
                    }
                    break;
                case "look Rock":
                //checks if the item is in the users inventory. If it is, it then prints out a description
                    if(look_item(inv,"Rock")){
                        System.out.println("Maybie this Rock can be used to commit vandilism");
                    }
                    break;
                case "look Apple pie":
                    if(look_item(inv,"Apple pie")){
                        System.out.println("Id like to eat this.... didnt that horse want to eat something with apples");
                    }
                    break;
                case "look Rusty key":
                    if(look_item(inv,"Rusty key")){
                        System.out.println("This looks like it goes into an old or rusty door");
                    }
                    break;
                case "look Book of jokes":
                    if(look_item(inv,"Book of jokes")){
                        System.out.println("This can really help me make someone laugh");
                    }
                    break;
                case "look Axe":
                    if(look_item(inv,"Axe")){
                        System.out.println("Holding this, i feel like i want to chew on some bark and build a dam");
                    }
                    break;
                case "look Car key":
                    if(look_item(inv,"Car key")){
                        System.out.println("This key looks like its for a car");
                    }
                    break;
                case "look Radio":
                    if(look_item(inv,"Radio")){
                        System.out.println("The music playing from this really cheers me up");
                    }
                    break;
                case "look Fishing rod":
                    if(look_item(inv,"Fishing rod")){
                        System.out.println("I can catch fish");
                    }
                    break;
                case "look Slimey fish":
                    if(look_item(inv,"Slimey fish")){
                        System.out.println("What kind of animal is known for eating fish");
                    }
                    break;
                case "look Crowbar":
                    if(look_item(inv,"Crowbar")){
                        System.out.println("I can really peel open some boarded up places that im not meant to get into, with this");
                    }
                    break;
                case "look Old poem":
                    if(look_item(inv,"Old poem")){
                        System.out.println("if i read this to someone it could really distract them for a sec because of how buetifull it is");
                    }
                    break;
                case "map":
                    System.out.println(user_map.display());
                    break;
                case "inventory":
                    System.out.println(inv.displayInventory());
                    break;
                case "use":
                //use_item returns a value, it only returns true if the user has completed the last puzzle finishing the game
                    if(use_item(full_map,user_pos,inv,score)){
                        command = "quit";
                    }
                    break;
                case "score":
                    System.out.println(score.getScore());
                    break;
                case "help":
                    //uses help method to print unique help message for each room
                    help(full_map,user_pos);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("That command doesnt exist");
            }
        }
        System.out.println("So sad to see you leave adventurer, but you adventure has ended. you score is " + score.getScore());
    }
}
