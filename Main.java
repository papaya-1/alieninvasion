import java.util.Scanner;
import java.util.ArrayList;
public class Main{
        static int dayNum = 1; 
        static ArrayList<String> userInventory; 
        static boolean baseSelected = false;
        static boolean morningChoice = false;
        static boolean alienEncountered = false;
        static boolean stayHomed = false; 
        static boolean mainChoice = false;
        static Base a = new Base(" shack", 4, 4, 50, false); // 5x5 grid
        static Base b = new Base(" apartment", 1, 2, 50, false);
        static Base c = new Base(" house", 2, 0, 50, false);
        static Base chosen;
        static Scanner collector = new Scanner(System.in);
    public static void main (String[] args){
        System.out.println("You come from summer break to find that your town has been invaded by aliens! No one else is around, but luckily help is arriving in 12 days. You'll need to survive in your 5x5 town by finding a base, maintaining it, geting food, and remain alive all through alien attacks to keep your health up until help arrives.");
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
                enactNight(user,chosen);
                morningChoice = false;
                alienEncountered = false;
                stayHomed = false;
                mainChoice = false;
                dayNum++;
            } 
            else{
                String resp = collector.nextLine();
                enactDay();
                //stuff here for regular days 
                morningChoice = false; //reset all other day choices here so that the computer knows //remember to set true each times these things happen
                alienEncountered = false;
                stayHomed = false;
                mainChoice = false;
                dayNum++;
            }
        }
    }

    public static String getResponse(String x){
        String gameResp = "";
        if (baseSelected == false){
            if (x.equals("1")){
                gameResp += "You have selected " + a; //Include whatever the user has to do to move on from here, perhaps a method for daily activties 
                chosen = a;
            }
            else if(x.equals("2")){
                gameResp += "You have selected " + b;
                chosen = b;
            }
            else if (x.equals("3")){
                gameResp += "You have selected " + c;
                chosen = c;
            }
            else{
                gameResp += "That is not a valid selection. Please try again and this time, enter a number";
            }
        }
        else if (morningChoice == false){
            //for when user makes the choice for the first activity, checks which option you chose and provides response
            morningChoice = true;
        }
        else if (mainChoice == false){
            //for when user makes the choice for the second activtiy, checks which option you chose and provides response
            mainChoice = true; //make sure this is the spot to put this
        }
        else if (alienEncountered == false && stayHomed == false){
            // for getResp when you choose to go out and get supplies
            alienEncountered = true;
        }
        else if (stayHomed == true){
            // for getResp when you choose to stay home
        }
        return gameResp;
    }

    public static void enactDay(){
        System.out.println("Day " + dayNum + "has begun!");
        System.out.println("Would you like to first...");
        System.out.println("1. Eat");
        System.out.println("2. Check inventory/stats");
        System.out.println("3. Take medicine");
        System.out.println("Choose a number!!!");
        String resp = collector.nextLine();
        getResponse(resp);
        System.out.println("Now choose your next activity...");
        System.out.println("1. Go to store/gather supplies");

    }
    public static void enactNight(User user, Base chosen){
        System.out.println("It is now  nighttime and you must sleep to prepare for yout next day. Take the next few moments to rest up. Zzzzzz Zzzzzzzzzzzzz Zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println("(please wait a moment for the next morning to start)");
        try{
        Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        chosen.setProtectionLevel(chosen.getProtectionLevel()-10);
        user.setHealth(user.getHealth()+10);
        System.out.println("Good morning! Hope you slept well! During the night their was some attacks on your base, but luckly your base held them out. Nevertheless, your base took some hits and needs to be kept up. Base protection level is now at "+ chosen.getProtectionLevel()+", but sleeping has boosted your health to "+ user.getHealth());

    }

}