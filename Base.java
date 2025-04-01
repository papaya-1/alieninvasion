public class Base extends Place{
    private int protectionLevel;
    
    public Base(){
        super();
        protectionLevel = 0;
    }
    public Base(String name, int x, int y, int protectionLevel){
        super(name, x, y);
        this.protectionLevel=protectionLevel;
    }
    public int getProtectionLevel(){
        return protectionLevel;
    }
    public void setProtectionLevel(int x){
        protectionLevel=x;
    }
    public void night(){
        protectionLevel = protectionLevel-10;
        System.out.println("The Base has lost 10% protection over the course of the night. Go to the Store and fix up your base to keep the base protection up. You are currently at "+ protectionLevel+"% right now.");
    }

}