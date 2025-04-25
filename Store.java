import java.util.ArrayList;
public class Store extends Place{

    private ArrayList<Item> items;
    
    public Store(String name, int x, int y, ArrayList<Item> items){
        super(name, x, y);
        this.items = items;
    }

    public String viewItems(){
        String description = "You look at the shelf. You see the following items: ";
        int numIndex = 1; 
        for(int i = 0; i < items.size(); i++){
            if (i == items.size()-1){
                description += numIndex + ") "+ items.get(i) + ".";
            }
            else{
            description += numIndex + ") " + items.get(i) + ", ";
            numIndex++;
            }
        }
        return description;
    }

    public String getItem(int num){
        int index = num - 1;
        String x = items.get(index).toString();
        return x;
    }

    public void removeItem(int num){
        int index = num - 1;
        // in the main class add the item to inventory first
        items.remove(index);
    }

}