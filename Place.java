public class Place{

    private String name;
    private int x;
    private int y; 

   public Place(){
        name="unknown";
         x=0;
         y=0;
   }
    public Place(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }

    public void setName(String a){
        name = a;
    }

    public int getX(){
        return x;
    }

    public void setX(int a){
        x = a;
    }

    public int getY(){
        return y;
    }

    public void setY(int a){
        y = a;
    }

    public String toString(){
        return "a " + getName() + "at coordinates (" + getX() + "," + getY() + ")";
    }
}