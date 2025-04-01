import java.util.Scanner;
public class Main{
        static int dayNum = 0; 
    public static void main (String[] args){
        Scanner collector = new Scanner(System.in);
        System.out.println("Intro");

        while(dayNum <= 12){
            if(dayNum == 12){
                System.out.println("Your saved");
            }
            String user = collector.nextString();
            if(dayNum == 1){
                System.out.println("Now that you're all caught up, why don't you chose the base that you would like to live in over the next 12 day.")
            }

            
        }
        
    }
}