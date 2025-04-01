import java.util.Scanner;
import java.util.ArrayList;
public class Main{
        static int dayNum = 0; 
        static ArrayList<String> userInventory; 
    public static void main (String[] args){
        Scanner collector = new Scanner(System.in);
        System.out.println("You come from summer break to find that your town has been invaded by alien! No one else is around, but luckily help is arriving in 12 days. You'll need to find a base, maintain it, and get food and survive alien attacks to keep your health up until help arrives.");
        User user = new User("human", 50, 100, true, userInventory);
        while(dayNum <= 12 && user.getIsAlive()){
            if(dayNum == 12){
                System.out.println("Your saved");
            }
            String resp = collector.nextLine();
            if(dayNum == 1){
                System.out.println("Now that you're all caught up, why don't you chose the base that you would like to live in over the next 12 day. Here are your options:");
                System.out.println("1) _________");
                System.out.println("2)__________");
                System.out.println("3)__________");
            } 
        }
    }

    public String getResponse(String x){
        String gameResp = "";
        return gameResp;
    }
}