public class Alien extends Entities{
    private int attack;

    public Alien(){
        super();
        attack = 0;
    }
    public Alien(String type, int health, boolean isAlive, int attack){
        super(type, health, isAlive);
        this.attack = attack;
    }
    public int getAttack(){
        return attack;
    }
    public void setAttack(int a){
        attack = a;
    }
}