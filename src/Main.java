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

        //Bezig met voldoende geld inwerpen en nummer kiezen
        boolean prepareToPlay = true;

        //Bezig met geld inwerpen
        boolean insertingMoney = true;

        //Bezig met nummer kiezen
        boolean choosingSong = true;
        while (prepareToPlay)
        {
            //Stap 1. Geld inwerpen
            while (insertingMoney)
            {
                try
                {
                    System.out.println("\nInsert money");
                    String input = scanner.nextLine();
                    if (input.equals("xyz"))
                    {
                        //TODO: Beheerder mode toevoegen
                        //beheermodus geactiveerd

                        System.exit(0);
                    } else
                    {
                        jukebox.insertCash(Double.parseDouble(input)); //Kan een exception gooien
                        insertingMoney = false;
                        break;
                    }
                }
                catch (Exception e) //Alles wat geen getal is.
                {
                    System.out.println("This is invalid input.");
                }
            }
            //nummer kiezen
            while (choosingSong)
            {
                try
                {
                    System.out.println("Select a song from the playlist by typing the number of the song.");
                    System.out.println("I want to hear song number: ");
                    int chosenSong = Integer.parseInt(scanner.nextLine());
                    if (jukebox.selectSong(chosenSong))
                    {
                        choosingSong = false;
                        insertingMoney = false;
                        break;
                    } else if (!jukebox.selectSong(chosenSong))
                    {
                        System.out.println("Voer meer geld in");
                        insertingMoney = true;
                        break;
                    }
                }
                catch (Exception e) //Niet bestaande nummers
                {
                    e.getMessage();
                    continue;
                }
            }
            if(!insertingMoney && !choosingSong){break;} //Uit de prepare loop springen

            continue;
        }

        //System.out.println("Use 'U' and 'D' to change the volume.");

        //Hier speelt de muziek af en kun je het volume wijzigen.
        boolean playingMusic = true;
        boolean timerSet = false;
        while (playingMusic)
        {
            try
            {
                //Check of de timer is geset.
                if (!timerSet)
                {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask()
                            {
                                @Override
                                public void run()
                                {
                                    jukebox.stopSong();
                                    System.out.println("Thank you for using DjoekBoks! \nGoodbye");
                                    System.exit(0);
                                }
                            },
                            //De tijd die het duurt voordat de run() wordt uitgevoerd in seconden.
                            //Deze is gelijk aan de lengte van het gekozen nummer
                            jukebox.getClip().getMicrosecondLength() / 1000
                    );
                    timerSet = true; //De timer is nu geset. Door de boolean kan deze niet in de volgende while iteratie opnieuw geset worden.
                }

                //TODO: Alleen de beheerder mag dit doen
                String volumeInput = scanner.nextLine();

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
