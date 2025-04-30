public class Item{

    private String name; 
    private int healthBoost;
    private int defenseBoost;
    private int type; //1 represents food, 2 represents medicine, 3 represents homeRepair

    public Item(String name, int healthBoost, int defenseBoost, int type){
        this.name = name; 
        this.healthBoost = healthBoost;
        this.defenseBoost = defenseBoost;
        this.type = type;
    } 

    public Item(String name, int type){
        this.name = name;
        this.type = type;
        healthBoost = 0;
        defenseBoost = 0;
    }

    public String toString(){
        return name + " with a defense boost of " + defenseBoost + "and a health boost of " +  healthBoost; 
    }

    public int getDefBoost(){
        return defenseBoost;
    }

    public String getName(){
        return name;
    }

    public int getHealthBoost(){
        return healthBoost;
    }

    public int getType(){
        return type;
    }

}
