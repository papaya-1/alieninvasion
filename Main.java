import java.util.Scanner;
public class Main{
        static int dayNum = 0; 
        static ArrayList<String> userInventory; 
    public static void main (String[] args){
        Scanner collector = new Scanner(System.in);
        System.out.println("Intro");
        User user = new User("human", 50, 100, true, "name", userInventory);
        while(dayNum <= 12 && user.getIsAlive()){
            if(dayNum == 12){
                System.out.println("Your saved");
            }
            String userResponse = collector.nextString();
        }
        
    }
}