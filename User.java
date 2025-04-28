import java.util.ArrayList;
public class User extends Entities{
    
    private ArrayList <Item> inventory;

    public User(){
        super();
    }
    public User(String type, int health, boolean isAlive, ArrayList <Item> inventory){
        super(type, health, isAlive);
        this.inventory = inventory;
    }
    public void plusHealth(int x){
        health += x;
    }
    public void minusHealth(int x){
        health -= x;
    }
    public void changeLifeStatus(boolean x){
        isAlive = x;
    }
    public void addtoInventory(Item x){
        inventory.add(x);
    }
    public void removeItem(int num){
        int index = num - 1;//-1 because the list is numbered starting from 1 not 0, so the user will give the computer the index + 1 
        inventory.remove(index);
    }
}