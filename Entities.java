public class Entities{
    private String type;
    protected int defense;
    protected int health;
    protected boolean isAlive;

    public Entities(){
        type = "unknown";
        defense = 0;
        health = 0;
        isAlive = true;
    }
    public Entities(String t, int d, int h, boolean a){
        type = t;
        defense = d;
        health = h;
        isAlive = a;
    }
    public String getType(){
        return type;
    }
    public int getDefense(){
        return defense;
    }
    public int getHealth(){
        return health;
    }
    public boolean getIsAlive(){
        return isAlive;
    }
    public void setType(String t){
        type = t;
    }
    public void setDefense(int d){
        defense = d;
    }
    public void setHealth(int h){
        health = h;
    }
    public void setIsAlive(boolean a){
        isAlive = a;
    }
}