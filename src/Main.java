import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {

        System.out.println("Welcome to JukeBox!");
        System.out.println("\nAvailable songs:");

        //De jukebox wordt aangemaakt.
        Jukebox jukebox = new Jukebox();

        //De Beschikbare nummers worden weergegeven.
        jukebox.getSongList().printSongList();

        //Het gebruik van de jukebox bestaat uit 3 stappen.
        //1. gooi geld in de machine
        //2. kies nummer + speel af
        //3. Wijzig volume

        //De scanner leest de user input.
        Scanner scanner = new Scanner(System.in);

        //Stap 1. Geld inwerpen
        boolean insertingMoney = true;
        while (insertingMoney)
        {
            try
            {
                System.out.println("\nInsert money");
                String input = scanner.nextLine();
                if (input.equals("xyz"))
                {
                    //beheermodus geactiveerd

                    System.exit(0);
                } else
                {
                    jukebox.insertCash(Double.parseDouble(input)); //Kan een exception gooien
                }
            }
            catch (Exception e) //Alles wat geen getal is.
            {
                System.out.println("Wrong input.");
            }
            finally
            {
                System.out.println("Insert more money? Y or N"); //Nog meer geld inwerpen?
                String decision = scanner.nextLine();

                if (decision.toLowerCase().equals("y")) //ja
                {
                    continue;
                } else if (decision.toLowerCase().equals("n")) //nee
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

        //nummer kiezen
        boolean choosingSong = true;
        while (choosingSong)
        {
            try
            {
                System.out.println("I want to hear song number: ");
                int chosenSong = Integer.parseInt(scanner.nextLine());
                if (jukebox.selectSong(chosenSong))
                {
                    choosingSong = false;
                    break;
                }
            }
            catch (Exception e) //Niet bestaande nummers
            {
                e.getMessage();
                continue;
            }
        }

        System.out.println("Use 'U' and 'D' to change the volume.");

        //Hier speelt de muziek af en kun je het volume wijzigen.
        boolean playingMusic = true;
        boolean timerSet = false;
        while (playingMusic)
        {
            try
            {
                if (!timerSet)
                {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask()
                            {
                                @Override
                                public void run()
                                {
                                    jukebox.stopSong();
                                    System.out.println("Goodbye!");
                                    System.exit(0);
                                }
                            },
                            jukebox.getClip().getMicrosecondLength() / 1000
                    );
                    timerSet = true;
                }

                String volumeInput = scanner.nextLine();
                // alles hierna wordt niet uitgevoerd als de gebruiker niets invoert
                if (volumeInput.toLowerCase().equals("u"))
                {
                    jukebox.higherVolume(4f);
                    continue;
                } else if (volumeInput.toLowerCase().equals("d"))
                {
                    jukebox.lowerVolume(4f);
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
