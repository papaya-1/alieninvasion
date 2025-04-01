import java.util.ArrayList;
public class User extends Entities{
    private ArrayList <String> inventory;

    public User(){
        super();
    }
    public User(String type, int defense, int health, boolean isAlive, ArrayList <String> inventory){
        super(type, defense, health, isAlive);
        this.inventory = inventory;
    }

    public void addtoInventory(String x){
        inventory.add(x);
    }

    public void removeItem(int num){
        int index = num - 1;//-1 because the list is numbered starting from 1 not 0, so the user will give the computer the index + 1 
        inventory.remove(index);
    }
}