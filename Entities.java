public class Entities{
    private String type;
    protected int health;
    protected boolean isAlive;

    public Entities(){
        type = "unknown";
        health = 0;
        isAlive = true;
    }
    public Entities(String t, int h, boolean a){
        type = t;
        health = h;
        isAlive = a;
    }
    public String getType(){
        return type;
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
    public void setHealth(int h){
        health = h;
    }
    public void setIsAlive(boolean a){
        isAlive = a;
    }
}