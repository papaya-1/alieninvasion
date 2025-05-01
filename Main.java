import java.util.Scanner;
import java.util.ArrayList;
public class Main{
        static int dayNum = 1; 
        //keeps track of user's stufff
        static ArrayList<Item> userInventory = new ArrayList<Item>();
        //since the user always types in 1, 2, 3..., we need to know what prompt they are answering. This keeps track of what prompt has and hasn't been answered 
        static ArrayList<Item> tempInventory = new ArrayList<Item>();
        static boolean baseSelected = false; 
        static boolean morningChoice = false;
        static boolean mainChoice = false;
        static boolean foodAvailable = false;
        static boolean medicineAvailable = false;
        static boolean repairAvailable = false; 
        //keeps track of store inventroy 
        static ArrayList<Item> hardwareInventory = new ArrayList<Item>();
        static ArrayList<Item> groceryInventory = new ArrayList<Item>();
        static ArrayList<Item> pharmacyInventory = new ArrayList<Item>();
        //stores 
        static Store hardware = new Store("Home-Depot", 0, 0, hardwareInventory);
        static Store grocery = new Store("Safeway", 2, 2, groceryInventory);
        static Store pharmacy = new Store("Walgreens", 4, 2, pharmacyInventory);
        //store stock 
            //home depot items 
        static Item ductTape = new Item("Duct tape", 0, 10, 3);
        static Item sandBags = new Item ("Sanbag", 0, 20, 3);
        static Item woodenPlanks = new Item ("Wooden planks", 0, 15, 3);        
            //walgreens items 
        static Item coldMedicine = new Item("Cold medicine", 10, 0, 2);
        static Item bandages = new Item("Bandages", 15, 0, 2);
        static Item tylenol = new Item("Tylenol", 20, 0, 2);
            //safeway items 
        static Item banana = new Item("Banana", 10, 0, 1);
        static Item bread = new Item("Bread", 20, 0, 1);
        static Item muffins = new Item("Muffins", 15, 0, 1);
        //housing options 
        static Base a = new Base(" shack", 4, 4, 50, false); // 5x5 grid
        static Base b = new Base(" apartment", 1, 2, 50, false);
        static Base c = new Base(" house", 2, 0, 50, false);
        static Base[] baseList = {a,b,c};
        //keeps track of what base the user is using 
        static Base chosen; 
        //creates a user 
        static User user = new User("human", 100, true, userInventory);
        //takes in user input
        static Scanner collector = new Scanner(System.in);
    public static void main (String[] args){
        //adding items to store inventories 
        hardwareInventory.add(ductTape);
        hardwareInventory.add(sandBags);
        hardwareInventory.add(woodenPlanks);
        pharmacyInventory.add(coldMedicine);
        pharmacyInventory.add(bandages);
        pharmacyInventory.add(tylenol);
        groceryInventory.add(banana);
        groceryInventory.add(muffins);
        groceryInventory.add(bread);
        //introduction 
        System.out.println(""); 
        System.out.println("You come from summer break to find that your town has been invaded by aliens! \nNo one else is around, but luckily help is arriving in 12 days. \nYou'll need to survive in your 5x5 town by finding a base, maintaining it, geting food, and remain alive all through alien attacks to keep your health up until help arrives.");
        System.out.println("Note: to select any options in this game, you must choose a number");
        System.out.println("");
        //pause between introduction and start of game  
        try{ 
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
                e.printStackTrace();
        }
        while(dayNum <= 12 && user.getIsAlive()){ //loop for 12 days game simulation 
            if(dayNum == 12){ //game sim for day 12 
                System.out.println("You have been saved! /(^^)/");
            }
            else if(dayNum == 1){ //game sim for 1st day- it is seperate since it has an additional step which is base selection 
                System.out.println("Let's start with finding a base! Remember to choose wisely. Here are your options:");
                System.out.println("1)The" + a); //add more description about base besides toString, ex: distance from stores
                System.out.println("2)The" + b);
                System.out.println("3)The" + c);
                String resp = collector.nextLine();
                getResponse(resp); //gets the computer's response for base selection. the other days don't use this method since, enactDay itself calls getResponse
                enactDay(); //begins the game sim 
                enactNight(user,chosen); //begins the game sim for nightime 
                morningChoice = false; //resets the prompt vars that check is a prompt has been called
                mainChoice = false;
                dayNum++; //ends a day and begins a new one 
            } 
            else{ //game sim for days 2 - 11
                enactDay();
                enactNight(user,chosen);
                morningChoice = false; //reset all other day choices here so that the computer knows //remember to set true each times these things happen
                mainChoice = false;
                dayNum++;
            }
        }
    }

    public static void findAndRemove(int index){
        String name = tempInventory.get(index).getName();
        for(int i = 0; i < userInventory.size(); i++){
            if (userInventory.get(i).getName().equals(name)){
                userInventory.remove(i);
            }
        }
    }

    public static void getResponse(String x){
        //getResponse provides the computers responses for the day sim + base selection 
        if (baseSelected == false){ // when user has selected base
            System.out.println("");
            if (x.equals("1") || x.equals(" 1")){ //choosing shack
                System.out.println("You have selected the" + a); 
                System.out.println("Let's head there now to start your day");
                chosen = a;
                baseSelected = true;
            }
            else if(x.equals("2") || x.equals(" 2")){ //choosing apartment
                System.out.println("You have selected the" + b);
                System.out.println("Let's head there now to start your day");
                chosen = b;
                baseSelected = true;
            }
            else if (x.equals("3") || x.equals(" 3")){ //choosing house 
                System.out.println("You have selected the" + c);
                System.out.println("Let's head there now to start your day");
                chosen = c;
                baseSelected = true;
            }
            else{ //user error 
                System.out.println("That is not a valid selection. Please try again and make sure to enter a number");
                //recursion, so user can make the right choice 
                String resp = collector.nextLine();
                getResponse(resp);
                baseSelected = false; 
            }
        }
        else if (morningChoice == false){ //morning activity 
            if (x.equals("1") || x.equals(" 1")){ //user chooses to eat 
                System.out.println("");
                System.out.println("Select a food item in your inventory");
                System.out.println("");
                tempInventory.clear();
                displaySelectInventory(1);
                if(foodAvailable == true){
                    String resp = collector.nextLine();
                    //include inventory max of 5 items whenever option comes up to add item 
                    if (resp.equals("1") || resp.equals(" 1")){ // for consuming item 1(index 0)
                        user.plusHealth(tempInventory.get(0).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(0).getName() + ".");
                        findAndRemove(0);
                    }
                    else if (resp.equals("2") || resp.equals(" 2")){ //for consuming item 2(index 1)
                        user.plusHealth(tempInventory.get(1).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(1).getName() + ".");
                        findAndRemove(1);
                    }
                    else if (resp.equals("3") || resp.equals(" 3")){ //for consuming item 3(index 2)
                        user.plusHealth(tempInventory.get(2).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(2).getName() + ".");
                        findAndRemove(2);
                    }
                    else if (resp.equals("4") || resp.equals(" 4")){ //for consuming item 4(index 3)
                        user.plusHealth(tempInventory.get(3).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(3).getName() + ".");
                        findAndRemove(3);
                    }
                    else if (resp.equals("5") || resp.equals(" 5")){ //for consuming item 5(index 4)
                        user.plusHealth(tempInventory.get(4).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(4).getName() + ".");
                        findAndRemove(4);
                    }
                    foodAvailable = false;
                }
                    //come up with recursrive statement for this as well
                morningChoice = true;
            }
            else if (x.equals("2") || x.equals(" 2")){ //user chooses to check stats/inventory 
                System.out.println("");
                System.out.println("Currently, you are alive, as your health is at "+ user.getHealth());
                System.out.print("In your Inventory, you have: ");
                System.out.println("");
                displayInventory();
                morningChoice = true;
            }
            else if (x.equals("3") || x.equals(" 3")){ //user chooses to take medicine 
                System.out.println("");
                System.out.println("Select a medical item in your inventory");
                System.out.println("");
                tempInventory.clear();
                displaySelectInventory(2);
                if (medicineAvailable == true){
                    String resp = collector.nextLine();
                    //include inventory max of 5 items whenever option comes up to add item 
                    if (resp.equals("1") || resp.equals(" 1")){ // for consuming item 1(index 0)
                        user.plusHealth(tempInventory.get(0).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(0).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(0);
                    }
                    else if (resp.equals("2") || resp.equals(" 2")){ //for consuming item 2(index 1)
                        user.plusHealth(tempInventory.get(1).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(1).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(1);
                    }
                    else if (resp.equals("3") || resp.equals(" 3")){ //for consuming item 3(index 2)
                        user.plusHealth(tempInventory.get(2).getHealthBoost());
                        System.out.println("You have used " + userInventory.get(2).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(2);
                    }
                    else if (resp.equals("4") || resp.equals(" 4")){ //for consuming item 4(index 3)
                        user.plusHealth(tempInventory.get(3).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(3).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(3);
                    }
                    else if (resp.equals("5") || resp.equals(" 5")){ //for consuming item 5(index 4)
                        user.plusHealth(tempInventory.get(4).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(4).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(4);
                    }
                    medicineAvailable = false;
                    //come up with recursrive statement for this as well
                    }
                morningChoice = true; //note to self: place morningChoice in enactDay class and allow for user to choose an option again using recursion since they might not want to just do one of the options 
            }
            else{
                System.out.println("That is not a valid selection. Please try again and make sure to enter a number");
                String resp = collector.nextLine();
                getResponse(resp);
            }
        }
        else if (mainChoice == false){ //main activity
            if (x.equals("1") || x.equals(" 1")){ //user chooses to go to store
                //include choice of what store to go to here, later factor distance into monster attack danger 
                shopping();
                possibleAlienEncounter();
                mainChoice = true;
            }
            else if (x.equals("2") || x.equals(" 2")){ //user choose to make home repairs
                repair();
                mainChoice = true;
            }
            else if (x.equals("3") || x.equals(" 3")){ //user chooses to stay home 
                System.out.println("");
                System.out.println("You have decided to spend the afternoon at home.(cough lazy cough cough)");
                System.out.println("");
                mainChoice = true;
            }
            else{
                System.out.println("That is not a valid selection. Please try again and make sure to enter a number");
                String resp = collector.nextLine();
                getResponse(resp);
            }
        }
        else{ //evening activtiy 
            if (x.equals("1") || x.equals(" 1")){ //user chooses to eat 
                System.out.println("");
                System.out.println("Select a food item in your inventory");
                System.out.println("");
                tempInventory.clear();
                displaySelectInventory(1);
                if(foodAvailable == true){
                    String resp = collector.nextLine();
                    //include inventory max of 5 items whenever option comes up to add item 
                    if (resp.equals("1") || resp.equals(" 1")){ // for consuming item 1(index 0)
                        user.plusHealth(tempInventory.get(0).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(0).getName() + ".");
                        findAndRemove(0);
                    }
                    else if (resp.equals("2") || resp.equals(" 2")){ //for consuming item 2(index 1)
                        user.plusHealth(tempInventory.get(1).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(1).getName() + ".");
                        findAndRemove(1);
                    }
                    else if (resp.equals("3") || resp.equals(" 3")){ //for consuming item 3(index 2)
                        user.plusHealth(tempInventory.get(2).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(2).getName() + ".");
                        findAndRemove(2);
                    }
                    else if (resp.equals("4") || resp.equals(" 4")){ //for consuming item 4(index 3)
                        user.plusHealth(tempInventory.get(3).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(3).getName() + ".");
                        findAndRemove(3);
                    }
                    else if (resp.equals("5") || resp.equals(" 5")){ //for consuming item 5(index 4)
                        user.plusHealth(tempInventory.get(4).getHealthBoost());
                        System.out.println("You have consumed " + tempInventory.get(4).getName() + ".");
                        findAndRemove(4);
                    }
                    foodAvailable = false;
                }
                    //come up with recursrive statement for this as well
            }
            else if (x.equals("2") || x.equals(" 2")){ //user chooses to check stats/inventory 
                System.out.println("");
                System.out.println("Currently, you are alive, as your health is at "+ user.getHealth());
                System.out.print("In your Inventory, you have: ");
                System.out.println("");
                displayInventory();
            }
            else if (x.equals("3") || x.equals(" 3")){ //user chooses to take medicine 
                System.out.println("");
                System.out.println("Select a medical item in your inventory");
                System.out.println("");
                tempInventory.clear();
                displaySelectInventory(2);
                if (medicineAvailable == true){
                    String resp = collector.nextLine();
                    //include inventory max of 5 items whenever option comes up to add item 
                    if (resp.equals("1") || resp.equals(" 1")){ // for consuming item 1(index 0)
                        user.plusHealth(tempInventory.get(0).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(0).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(0);
                    }
                    else if (resp.equals("2") || resp.equals(" 2")){ //for consuming item 2(index 1)
                        user.plusHealth(tempInventory.get(1).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(1).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(1);
                    }
                    else if (resp.equals("3") || resp.equals(" 3")){ //for consuming item 3(index 2)
                        user.plusHealth(tempInventory.get(2).getHealthBoost());
                        System.out.println("You have used " + userInventory.get(2).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(2);
                    }
                    else if (resp.equals("4") || resp.equals(" 4")){ //for consuming item 4(index 3)
                        user.plusHealth(tempInventory.get(3).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(3).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(3);
                    }
                    else if (resp.equals("5") || resp.equals(" 5")){ //for consuming item 5(index 4)
                        user.plusHealth(tempInventory.get(4).getHealthBoost());
                        System.out.println("You have used " + tempInventory.get(4).getName() + ".");
                        System.out.println("Your health is now at: " + user.getHealth());
                        findAndRemove(4);
                    }
                    medicineAvailable = false;
                    //come up with recursrive statement for this as well
                    }
            }
            else{
                System.out.println("That is not a valid selection. Please try again and make sure to enter a number");
                String resp = collector.nextLine();
                getResponse(resp);
            }
        }
    }
       /* else if(goToStore == true){ //when going to store equals true}*/

    public static void enactDay(){
        if (dayNum == 1){
            try{ //pause between base selection and start of day  
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("");
        System.out.println("Day " + dayNum + " has begun!");
        System.out.println("");
        System.out.println("Would you like to first...");
        System.out.println("1. Eat");
        System.out.println("2. Check inventory/stats");
        System.out.println("3. Take medicine");
        String resp = collector.nextLine();
        getResponse(resp);
        System.out.println("");
        System.out.println("Now choose your daily activity...");
        System.out.println("1. Go to store/gather supplies");
        System.out.println("2. Make repairs to your house");
        System.out.println("3. Stay home (with this choice, you essentually foreit doing an afternoon activity today)");
        resp = collector.nextLine();
        getResponse(resp);
        System.out.println("The day is coming to an end! Choose your evening activity");
        System.out.println("1. Eat");
        System.out.println("2. Check inventory/stats");
        System.out.println("3. Take medicine");
        resp = collector.nextLine();
        getResponse(resp);
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("The last sunlight of the day fades away, as the day draws to a close. You will have another chance to improve to situation and make it until help arrives tomorrow.");
        System.out.println("");
    }
    
    public static void enactNight(User user, Base chosen){
        System.out.println("It is now  nighttime and you must sleep to prepare for yout next day. Take the next few moments to rest up. Zzzzzz Zzzzzzzzzzzzz Zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println("(please wait a moment for the next morning to start)");
        System.out.println("");
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        chosen.setProtectionLevel(chosen.getProtectionLevel()-10);
        //if a base falls
        if (chosen.getProtectionLevel() <= 0){
            chosen.setDestroyed(true);
            if (chosen.getName().equals("shack")){
                a.setDestroyed(true);
            }
            else if (chosen.getName().equals("apartment")){
                b.setDestroyed(true);
            }
            else if (chosen.getName().equals("house")){
                c.setDestroyed(true);
            }
            System.out.println("You wake up to your shelter crumbling around you. Your base has been destroyed by alien attacks");
            System.out.println("You must travel to a new base and might be attacked by aliens. Here are your options for a base:");
            int index = 1;
            boolean atLeastOne = false;
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            for(int i = 0; i < baseList.length; i++){
                if (baseList[i].getDestroyed() == false){
                    System.out.println(index + ") " + baseList[i]);
                    indexes.add(i);
                    atLeastOne = true;
                }
            }
            if(atLeastOne == false){
                System.out.println("With no available bases, you are exposed to alien attacks...");
                try{
                    Thread.sleep(3000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("You have died!");
            }
            else{
                String resp = collector.nextLine();
                if(resp.equals("1")){
                    chosen = baseList[indexes.get(0)];
                    System.out.println("You head towards your new base " + chosen);
                    System.out.println("As you head there, you are wary of the chance that you could be attacked by aliens");
                }
                else if (resp.equals("2")){
                    chosen = baseList[indexes.get(1)];
                    System.out.println("You head towards your new base " + chosen);
                }
                else if (resp.equals("3")){
                    chosen = baseList[indexes.get(2)];
                    System.out.println("You head towards your new base " + chosen);
                }
                possibleAlienEncounter();
                System.out.println("You make it to your base and rest up for the rest of the night. You loose 10 health due to lack of sleep");

                user.setHealth(user.getHealth()-10);
                if (user.getHealth() <= 0){
                    System.out.println("It looks like that was the last straw. You have died");
                }
            }
        }
        if (user.getHealth() == 100){
            //nothing happens here 
        }
        else{
            user.setHealth(user.getHealth()+5); 
        }
        try{ 
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
                e.printStackTrace();
        }
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

    public static void displaySelectInventory(int x){
        int index = 1;
        int numOf = 0;
        for(int i = 0; i < userInventory. size(); i++){
                if(userInventory.get(i).getType() == x){
                    numOf++;
                }
        }
        if(numOf > 0){
            if (x == 1){
                foodAvailable = true;
            }
            else if (x == 2){
                medicineAvailable = true;
            }
            else if (x == 3){
                repairAvailable = true;
            }
            for (int i = 0; i < userInventory.size(); i++){
                if(userInventory.get(i).getType() == x){
                tempInventory.add(userInventory.get(i));
                System.out.println(index + ") " + userInventory.get(i));
                index++;
                }
            }
        }
        else{
            if (x == 1){
                System.out.println("There are no food items in your inventory");
                System.out.println("You will not be able to eat anything today :(");
            }
            else if (x == 2){
                System.out.println("There are no medical items in your iventory");
                System.out.println(":(");
            }
            else if (x == 3){
                System.out.println("There are no home repair items in your iventory");
                System.out.println("You won't be able to fix your house :(");
            }
        }
    }

    public static void possibleAlienEncounter(){
        int chance = (int)(Math.random()*101); // 101 is 100 inclusive, right?, yes 
        if (chance <=50){
            System.out.println("");
            System.out.println("You have made it through without encountering an alien");
            System.out.println("");

        }
        else{
            System.out.println("");
            System.out.println("You have had the unfortunate luck of encountering an alien. You have three choices:");
            System.out.println("1) Run away, but end activity, lose all inventory, and potentially lose health (90% success)");
            System.out.println("2) Defend, and potentiallly lose health (60% success)");
            System.out.println("3) Attack, and potentially lose health and all inventory(30% success)");
            System.out.println("");
            String resp = collector.nextLine();
            int success = (int)(Math.random()*101); //101 is 100 inclusive, right?, yes 
            if (resp.equals("1")){
                if(success <= 90){
                System.out.println("");
                System.out.println("You have successfully run away from the alien");
                System.out.println("");
                }
                else{
                System.out.println("");
                System.out.println("You have not successfully run away from the alien");
                user.setHealth(user.getHealth()-5);
                System.out.println("");
                }
            }
            else if (resp.equals("2")){
                if(success <= 60){
                System.out.println("");
                System.out.println("You have successfully defended yourself from the alien");
                System.out.println("");
                }
                else{
                System.out.println("");
                System.out.println("You have not successfully defended yourself from the alien");
                user.setHealth(user.getHealth()-10);
                System.out.println("");
                }
            }
            else if (resp.equals("3")){
                if(success <= 30){
                System.out.println("");
                System.out.println("You have successfully attacked the alien");
                System.out.println("");
                }
                else{
                System.out.println("");
                System.out.println("You have not successfully attaked the alien");
                user.setHealth(user.getHealth()-15);
                System.out.println("");
                }
            }
            if (user.getHealth() <= 0){
                System.out.println("You have died");
                user.setIsAlive(false);
            }
        }
    }

    public static double calculateDistance(Base x, Store y){
        double distance = Math.sqrt(Math.pow(2, (y.getX()-x.getX())) + Math.pow(2,(y.getY()- x.getY())));
        return distance;
    }

    public static void shopping(){
        double x = (double)(Math.round(calculateDistance(chosen, grocery)* 100.00))/100;
        double y = (double)(Math.round(calculateDistance(chosen, pharmacy)*100.00))/100;
        double z = (double)(Math.round(calculateDistance(chosen, hardware)*100.00))/100;
        System.out.println("");
        System.out.println("Which store would you like to visit?");
        System.out.println("Your options are:");
        System.out.println("1) Safeway with a distance away of " + x);
        System.out.println("2) Walgreens with a distance away of " + y);
        System.out.println("3) Home Depot with a distance away of " + z);
        //System.out.println("The further away the store, the higher chance you will be attacked by an alien on the way there.");
        //add this in later 
        String resp = collector.nextLine();
        System.out.println("");
        if (resp.equals("1")){
            System.out.println("You start walking towards Safeway...");
            try{ 
                Thread.sleep(3000);
            }
            catch (InterruptedException e){
                    e.printStackTrace();
            }
            System.out.println("");
            System.out.println("You arrive at the store and check the last shelf filled with supplies. You see the following items on the shelf:");
            for(int i = 0; i < groceryInventory.size(); i++){
                System.out.println(i+1 + ") " + groceryInventory.get(i));
            }
            System.out.println("Select the item you want to pick up");
            String resp2 = collector.nextLine();
            if (resp2.equals("1")){
                userInventory.add(groceryInventory.get(0));
                System.out.println("You have added " + groceryInventory.get(0) + " to your inventory");
            }
            else if (resp2.equals("2")){
                userInventory.add(groceryInventory.get(1));
                System.out.println("You have added " + groceryInventory.get(1) + "to your inventory");
            }
            else if (resp2.equals("3")){
                userInventory.add(hardwareInventory.get(2));
                System.out.println("You have added " + groceryInventory.get(2) + "to your inventory");
            }           
        }
        else if (resp.equals("2")){
            System.out.println("You start walking towards Walgreens...");
            try{ 
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                    e.printStackTrace();
            }
            System.out.println("You arrive at the store and check the last shelf filled with supplies. You see the following items on the shelf:");
            for(int i = 0; i < pharmacyInventory.size(); i++){
                System.out.println(i+1 + ") " + pharmacyInventory.get(i));
            }
            System.out.println("Select the item you want to pick up");
            String resp2 = collector.nextLine();
            if (resp2.equals("1")){
                userInventory.add(pharmacyInventory.get(0));
                System.out.println("You have added " + pharmacyInventory.get(0) + " to your inventory");
            }
            else if (resp2.equals("2")){
                userInventory.add(pharmacyInventory.get(1));
                System.out.println("You have added " + pharmacyInventory.get(1) + "to your inventory");
            }
            else if (resp2.equals("3")){
                userInventory.add(pharmacyInventory.get(2));
                System.out.println("You have added " + pharmacyInventory.get(2) + "to your inventory");
            }
        }
        else if (resp.equals("3")){
            System.out.println("You start walking towards Home Depot...");
            try{ 
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                    e.printStackTrace();
            }
            System.out.println("You arrive at the store and check the last shelf filled with supplies. You see the following items on the shelf:");
            for(int i = 0; i < hardwareInventory.size(); i++){
                System.out.println(i+1 + ") " + hardwareInventory.get(i));
            }
            System.out.println("Select the item you want to pick up");
            String resp2 = collector.nextLine();
            if (resp2.equals("1")){
                userInventory.add(hardwareInventory.get(0));
                System.out.println("You have added " + hardwareInventory.get(0) + " to your inventory");
            }
            else if (resp2.equals("2")){
                userInventory.add(hardwareInventory.get(1));
                System.out.println("You have added " + hardwareInventory.get(1) + "to your inventory");
            }
            else if (resp2.equals("3")){
                userInventory.add(hardwareInventory.get(2));
                System.out.println("You have added " + hardwareInventory.get(2) + "to your inventory");
            }
        }
        try{ 
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
                e.printStackTrace();
        }
        System.out.println("");
        System.out.println("With your items secured, you head back to your base. Be wary- the aliens come out at evening");
        try{ 
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
                e.printStackTrace();
        }
        //this is the method for going to the store, seeing the display, and choosing to pick up max 2 items 
        //if you already have 5 items, you have to drop items to get more 
    }

    public static void repair(){
        // repairing house 
        if(chosen.getProtectionLevel() <= 100){
            if (userInventory.size() != 0){
                tempInventory.clear();
                displaySelectInventory(3);
                if (repairAvailable == true){
                    String resp = collector.nextLine();
                    if (resp.equals("1")){
                        chosen.addProtection(tempInventory.get(0).getDefBoost());
                        System.out.println("Your base's strength is now at " + chosen.getProtectionLevel() + "/100.");
                        findAndRemove(0);
                    }
                    else if (resp.equals("2")){
                        chosen.addProtection(tempInventory.get(1).getDefBoost());
                        System.out.println("Your base's strength is now at " + chosen.getProtectionLevel() + "/100.");
                        findAndRemove(1);
                    }
                    else if (resp.equals("3")){
                        chosen.addProtection(tempInventory.get(2).getDefBoost());
                        System.out.println("Your base's strength is now at " + chosen.getProtectionLevel() + "/100.");
                        findAndRemove(2);
                    }
                    else if (resp.equals("4")){
                        chosen.addProtection(tempInventory.get(3).getDefBoost());
                        System.out.println("Your base's strength is now at " + chosen.getProtectionLevel() + "/100.");
                        findAndRemove(3);
                    }
                    else if (resp.equals("5")){
                        chosen.addProtection(tempInventory.get(4).getDefBoost());
                        System.out.println("Your base's strength is now at " + chosen.getProtectionLevel() + "/100.");
                        findAndRemove(4);
                    }
                    repairAvailable = false;
                }
                else{
                    System.out.println("You have no tools to conduct repairs");
                }
            }
            else{
                System.out.println("You have no tools to conduct repairs");
            }
        }
        else{
            System.out.println("Your base is at full strength. There are no repairs to be made.");
        }
    }

}