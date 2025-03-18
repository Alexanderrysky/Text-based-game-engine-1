public class Inventory {
    final int MAX_ITEMS = 10;
    private int item_num;
    private String[] items;

    public Inventory(){
        //constructs everything and sets every index in items array as 'EMPTY'
        items = new String[MAX_ITEMS];
        item_num = 0;
        for(int i=0;i<MAX_ITEMS;i++){
            items[i] = "EMPTY";
        }
    }

    public void addItem(String item){
        if(item_num != MAX_ITEMS){
            for(int i=0;i<MAX_ITEMS;i++){
                //uses a for loop to find the first available place to put an item
                if(items[i].equals("EMPTY")){
                    items[i] = item;
                    item_num++;
                    break;
                }
            }
            System.out.println(item+" succsesfully added to your inventory");
        }else{
            System.out.println("Sorry couldnt add "+item+" as your inventory is full");
        }
    }

    public void removeItem(String item){
        if(item_num > 0){
            for(int i=0;i<MAX_ITEMS;i++){
                //loops through array to see if item is inside of it, if it is it then replaces it with an 'EMPTY' then breaks the for loop
                if(items[i].equals(item)){
                    items[i] = "EMPTY";
                    System.out.println("succsesfuly removed "+item);
                    item_num--;
                    break;
                }
            }
        }else{
            System.out.println("there is nothing to remove from your inventory");
        }
        
    }

    public int hasItem(String item){
        int index = -1;
        for(int i=0;i<MAX_ITEMS;i++){
            if(items[i].equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }

    public String displayInventory(){
        String inventory = "";
        //looks through inventroy adding every item that is not empty to a string that is returned and printed
        for(int i=0;i<MAX_ITEMS;i++){
            if(!items[i].equals("EMPTY")){
                inventory += items[i] + " ";
            }
        }
        return inventory;
    }
}