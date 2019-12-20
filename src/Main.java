import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        System.out.println("Welcome to JukeBox!");
        System.out.println("\nAvailable songs:");


        Jukebox jukebox = new Jukebox();
        jukebox.setUpSongList();

        jukebox.getSongList().printSongList();
        //1. gooi geld in de machine
        //2. kies nummer
        //3. speel nummer

        Scanner scanner = new Scanner(System.in);

        boolean insertingMoney = true;
        while (insertingMoney)
        {
            try
            {
                System.out.println("\nInsert money");
                jukebox.insertCash(scanner.nextDouble());
            }
            catch(Exception e)
            {
                System.out.println("Wrong input.");
            }
            finally
            {
                System.out.println("Insert more money? Y or N");
                scanner.nextLine();
                String decision = scanner.nextLine();

                if(decision.toLowerCase().equals("y"))
                {
                    continue;
                }
                else if(decision.toLowerCase().equals("n"))
                {
                    insertingMoney = false;
                }
            }
        }

        System.out.println("Select a song from the playlist.");
        System.out.println("by typing the number of the song.");

        int chosenSong = scanner.nextInt();
        jukebox.selectSong(chosenSong);
    }
    
}
