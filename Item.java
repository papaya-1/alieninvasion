public class Item{

    private String name; 
    private int healthBoost;
    private int defenseBoost;
    private int attackCapability;

    public Item(String name, int healthBoost, int defenseBoost, int attackCapability){
        this.name = name; 
        this.healthBoost = healthBoost;
        this.defenseBoost = defenseBoost;
        this.attackCapability = attackCapability; 
    } 

    public Item(String name){
        this.name = name;
        healthBoost = 0;
        defenseBoost = 0;
        attackCapability = 0; 
    }

    public String toString(){
        return name + " with a defense boost of " + defenseBoost + ", a health boost of " +  healthBoost + ", and an attack capability of " + attackCapability; 
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

    public int getAttackCap(){
        return attackCapability;
    }

}
