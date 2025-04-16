import java.util.Scanner;
import java.util.ArrayList;
public class Main{
        static int dayNum = 1; 
        static ArrayList<String> userInventory; 
        static boolean baseSelected = false;
        static Base a = new Base(" shack", 4, 4, 50, false); // 5x5 grid
        static Base b = new Base(" apartment", 1, 2, 50, false);
        static Base c = new Base(" house", 2, 0, 50, false);
    public static void main (String[] args){
        Scanner collector = new Scanner(System.in);
        System.out.println("You come from summer break to find that your town has been invaded by alien! No one else is around, but luckily help is arriving in 12 days. You'll need to find a base, maintain it, get food, and survive alien attacks to keep your health up until help arrives.");
        System.out.println("");
        User user = new User("human", 50, 100, true, userInventory);
        while(dayNum <= 12 && user.getIsAlive()){
            if(dayNum == 12){
                System.out.println("You have been saved! /(^^)/");
            }
            else if(dayNum == 1){
                System.out.println("Let's start with finding a base! Remember to choose wisely. Here are your options (choose a number):");
                System.out.println("1)" + a); //add more description about base besides toString, ex: distance from stores
                System.out.println("2)" + b);
                System.out.println("3)" + c);
                String resp = collector.nextLine();
                System.out.println(getResponse(resp));
                dayNum++;
            } 
            else{
                String resp = collector.nextLine();
                //stuff here for regular days 
                dayNum++;
            }
        }
    }

    public static String getResponse(String x){
        String gameResp = "";
        if (baseSelected == false){
            if (x.equals("1")){
                gameResp += "You have selected " + a; //Include whatever the user has to do to move on from here, perhaps a method for daily activties 
            }
            else if(x.equals("2")){
                gameResp += "You have selected " + b;
            }
            else if (x.equals("3")){
                gameResp += "You have selected " + c;
            }
            else{
                gameResp += "That is not a valid selection. Please try again and this time, enter a number";
            }
        }
        return gameResp;
    }
}