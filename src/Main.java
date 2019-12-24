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
            catch (Exception e)
            {
                System.out.println("Wrong input.");
            }
            finally
            {
                System.out.println("Insert more money? Y or N");
                scanner.nextLine();
                String decision = scanner.nextLine();

                if (decision.toLowerCase().equals("y"))
                {
                    continue;
                } else if (decision.toLowerCase().equals("n"))
                {
                    insertingMoney = false;
                } else
                {
                    System.out.println("Wrong input.");
                    continue;
                }
            }
        }

        System.out.println("Select a song from the playlist");
        System.out.println("by typing the number of the song.");

        boolean choosingSong = true;
        while (choosingSong == true)
        {
            try
            {
                System.out.println("I want to hear song number: ");
                int chosenSong = scanner.nextInt();
                if (jukebox.selectSong(chosenSong))
                {
                    choosingSong = false;
                }
            }
            catch (Exception e)
            {
                e.getMessage();
                continue;
            }
        }

        System.out.println("Volume: " + jukebox.getSoundVolume());
        System.out.println("Use 'U' and 'D' to change the volume.");


        boolean playingMusic = true;
        while (playingMusic == true)
        {
            try
            {
                String volumeInput = scanner.nextLine();
                if (volumeInput.toLowerCase().equals("u"))
                {
                    jukebox.higherVolume(1);
                    System.out.println(jukebox.getSoundVolume());
                    continue;
                }
                else if (volumeInput.toLowerCase().equals("d"))
                {
                    jukebox.lowerVolume(1);
                    System.out.println(jukebox.getSoundVolume());
                    continue;
                }
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }

    }

}
