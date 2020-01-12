import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("Welcome to JukeBox!");
    
        //De jukebox wordt aangemaakt.
        Jukebox jukebox = new Jukebox();
        //De scanner leest de user input.
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Do you want to activate admin mode? y/N:");
        if (checkInput(scanner))
        {
            adminMode(jukebox, scanner);
        }
        else
        {
            userMode(jukebox, scanner);
        }
    }
    
    public static void userMode(Jukebox jukebox, Scanner scanner)
    {
        System.out.println("\nAvailable songs:");
    
        //De Beschikbare nummers worden weergegeven.
        jukebox.getSongList().printSongList();
    
        //Het gebruik van de jukebox bestaat uit 2 stappen.
        //1. gooi geld in de machine
        //2. kies nummer + speel af
    
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
                    System.out.println("\nPlease insert some money:");
                    String input = scanner.nextLine();
                    jukebox.insertCash(Double.parseDouble(input)); //Kan een exception gooien
                    insertingMoney = false;
                    break;
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
            if (!insertingMoney && !choosingSong)
            {
                break; //Uit de prepare loop springen
            }
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
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    }
    
    public static void adminMode(Jukebox jukebox, Scanner scanner)
    {
        // Authorizatie
        boolean enteringPassword = true;
        while (enteringPassword)
        {
            System.out.println("Please enter the admin password:");
            String password = scanner.nextLine();
            if (password.equals("admin"))
            {
                enteringPassword = false;
                System.out.println("The password is correct.");
            }
            else
            {
                System.out.println("The password is incorrect.");
            }
        }
        
        boolean inLoop = true;
        while (inLoop)
        {
            // Geldlade legen
            System.out.println("Do you want to empty the cash drawer? y/N:");
            if (checkInput(scanner))
            {
                jukebox.getCashDrawer().emptyCashDrawer();
                System.exit(0);
            }
    
            // Overzicht printen
            System.out.println("Do you want to print an overview? y/N:");
            if (checkInput(scanner))
            {
                jukebox.printOverview();
                System.exit(0);
            }
    
            // Nummer verwijderen
            System.out.println("Do you want to delete a song? y/N:");
            if (checkInput(scanner))
            {
                jukebox.getSongList().printSongList();
                System.out.println("Please select a song number to delete:");
                int songToRemove = Integer.parseInt(scanner.nextLine());
                jukebox.getSongList().removeSong(songToRemove);
                System.exit(0);
            }
    
            // Nummer toevoegen
            System.out.println("Do you want to add a song? y/N:");
            if (checkInput(scanner))
            {
                System.out.println("Now enter the song details.");
                System.out.println("Song title:");
                String title = scanner.nextLine();
                System.out.println("Song artist:");
                String artist = scanner.nextLine();
                System.out.println("Price:");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.println("Full filename:");
                String filename = scanner.nextLine();
        
                // Als nummer niet bestaat, toevoegen
                // Zowel, jammer, mag je alle stappen opnieuw doorlopen vanaf geldlade legen
                if (jukebox.getSongList().isUniqueSong(filename))
                {
                    jukebox.getSongList().addSong(title, artist, price, filename);
                    System.exit(0);
                }
                else
                {
                    System.out.println("There already exists a song with this filename");
                }
            }
            else
            {
                inLoop = false;
            }
        }
        
        // Volume aanpassen
        System.out.println("Do you want to change the volume? y/N:");
        if (checkInput(scanner))
        {
            // Moet volume omhoog?
            System.out.println("Do you want to turn the volume up? y/N:");
            if (checkInput(scanner))
            {
                jukebox.higherVolume(4f);
            }
            else
            {
                System.out.println("The volume will be lowered by one interval.");
            }
        }
        else
        {
            System.out.println("Thank you for administering this djoekbox.\nHave a good one.");
        }
    }
    
    private static boolean checkInput(Scanner scanner)
    {
        return scanner.nextLine().toLowerCase().equals("y");
    }

}
