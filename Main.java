import java.util.Scanner;
import java.util.ArrayList;
public class Main{
        static int dayNum = 1; 
        static ArrayList<Item> userInventory = new ArrayList<Item>();
        static boolean baseSelected = false;
        static boolean morningChoice = false;
        static boolean alienEncountered = false;
        static boolean stayedHome = false; 
        static boolean mainChoice = false;
        static Base a = new Base(" shack", 4, 4, 50, false); // 5x5 grid
        static Base b = new Base(" apartment", 1, 2, 50, false);
        static Base c = new Base(" house", 2, 0, 50, false);
        static Base chosen;
        static User user = new User("human", 50, 100, true, userInventory);
        static Scanner collector = new Scanner(System.in);
    public static void main (String[] args){
        System.out.println("You come from summer break to find that your town has been invaded by aliens! No one else is around, but luckily help is arriving in 12 days. You'll need to survive in your 5x5 town by finding a base, maintaining it, geting food, and remain alive all through alien attacks to keep your health up until help arrives.");
        System.out.println("");
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
                enactDay();
                enactNight(user,chosen);
                morningChoice = false;
                alienEncountered = false;
                stayedHome = false;
                mainChoice = false;
                dayNum++;
            } 
            else{
                enactDay();
                enactNight(user,chosen);
                //stuff here for regular days 
                morningChoice = false; //reset all other day choices here so that the computer knows //remember to set true each times these things happen
                alienEncountered = false;
                stayedHome = false;
                mainChoice = false;
                dayNum++;
            }
        }
    }

    public static String getResponse(String x){
        String gameResp = "";
        if (baseSelected == false){ // when user has selected base
            if (x.equals("1")){
                gameResp += "You have selected " + a; 
                chosen = a;
                baseSelected = true;
            }
            else if(x.equals("2")){
                gameResp += "You have selected " + b;
                chosen = b;
                baseSelected = true;
            }
            else if (x.equals("3")){
                gameResp += "You have selected " + c;
                chosen = c;
                baseSelected = true;
            }
            else{
                gameResp += "That is not a valid selection. Please try again and this time, enter a number";
                baseSelected = false;
            }
        }
        else if (morningChoice == false){ //morning activity 
            if (x.equals("1")){
                //the user has chosen to eat, display inventory, allow for choice, update inventory/health 
                System.out.println("Select a consumable item in your inventory");
                System.out.println("Note: if you are unsure if an item is consumable, check whether it has a health boost. Items without a health boost are not food");
                //later create a seperate display inventory for food 
                displayInventory();
                if (userInventory.size() == 0){
                }
                else{
                    String resp = collector.nextLine();
                //include inventory max of 5 items whenever option comes up to add item 
                    if (resp.equals("1")){
                        user.plusHealth(userInventory.get(0).getHealthBoost());
                        //add how you would use an attack items 
                        System.out.println("You have consumed " + userInventory.get(0).getName() + ".");
                        userInventory.remove(0);
                    }
                    else if (resp.equals("2")){
                        user.plusHealth(userInventory.get(1).getHealthBoost());
                        System.out.println("You have consumed " + userInventory.get(1).getName() + ".");
                        userInventory.remove(1);
                    }
                    else if (resp.equals("3")){
                        user.plusHealth(userInventory.get(2).getHealthBoost());
                        System.out.println("You have consumed " + userInventory.get(2).getName() + ".");
                        userInventory.remove(2);
                    }
                    else if (resp.equals("4")){
                        user.plusHealth(userInventory.get(3).getHealthBoost());
                        System.out.println("You have consumed " + userInventory.get(3).getName() + ".");
                        userInventory.remove(3);
                    }
                    else if (resp.equals("5")){
                        user.plusHealth(userInventory.get(4).getHealthBoost());
                        System.out.println("You have consumed " + userInventory.get(4).getName() + ".");
                        userInventory.remove(4);
                    }
                }
                morningChoice = true;
            }
            else if (x.equals("2")){
                //the user has chosen to check stats/inventory, display stats/inventory  
                morningChoice = true;
            }
            else if (x.equals("3")){
                //the user has chosen to take meds, return no meds if there are no meds and if there are update them
                morningChoice = true;
            }
            else{
                gameResp += "That is not a valid selection. Please try again and this time, enter a number";
                morningChoice = false;
            }
        }
        else if (mainChoice == false){ //main activity
            if (x.equals("1")){
                //user chooses to go to store
            }
            else if (x.equals("2")){
                //user choose to make home repairs
            }
            else if (x.equals("3")){
                //user chooses to stay home 
            }
            mainChoice = true; //make sure this is the spot to put this
        }
        else if (alienEncountered == false && stayedHome == false){ // for getResp when you choose to go out and get supplies
            alienEncountered = true;
        }
        else if (stayedHome == true){
            // for getResp when you choose to stay home
        }
        return gameResp;
    }

    public static void enactDay(){
        System.out.println("Day " + dayNum + " has begun!");
        System.out.println("Would you like to first...");
        System.out.println("1. Eat");
        System.out.println("2. Check inventory/stats");
        System.out.println("3. Take medicine");
        System.out.println("Choose a number!!!");
        String resp = collector.nextLine();
        getResponse(resp);
        System.out.println("Now choose your daily activity...");
        System.out.println("1. Go to store/gather supplies");
        System.out.println("2. Make repairs to your house");
        System.out.println("3. Stay home");
        System.out.println("Choose a number!!!");
        resp = collector.nextLine();
        getResponse(resp);
        System.out.println("The day is coming to an end! Choose your evening activity");
        System.out.println("1. Check inventory/stats");
        System.out.println("2. Eat");
        System.out.println("3. Take medicine");
        resp = collector.nextLine();
        getResponse(resp);
        System.out.println("The day has ended!");
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

    public static void displayInventory(){
        if (userInventory.size() != 0){
            for (int i = 0; i < userInventory.size(); i++){
                System.out.println(i+1 + ") " + userInventory.get(i));
            }
        }
        else{
            System.out.println("There are no items in your inventory");
        }
    }

}