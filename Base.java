public class Base extends Place{
    private int protectionLevel;
    private boolean destroyed;
    
    public Base(){
        super();
        protectionLevel = 0;
    }
    public Base(String name, int x, int y, int protectionLevel, boolean destroyed){
        super(name, x, y);
        this.protectionLevel = protectionLevel;
        this.destroyed = destroyed;
    }
    public int getProtectionLevel(){
        return protectionLevel;
    }
    public void setProtectionLevel(int x){
        protectionLevel=x;
    }
    public boolean getDestroyed(){
        return destroyed;
    }
    public void setDestroyed(boolean x){
        destroyed = x;
    }
    public void night(){
        protectionLevel = protectionLevel-10;
        System.out.println("The Base has lost 10% protection over the course of the night. Go to the Store and fix up your base to keep the base protection up. You are currently at "+ protectionLevel+"% right now.");
    }

    public String toString(){
        return getName() + " located at coordinates (" + getX() + ","  + getY() + ").";
    }

}