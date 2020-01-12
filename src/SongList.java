import java.util.ArrayList;
import java.util.Iterator;

public class SongList
{
    private ArrayList<Song> songList;

    public SongList()
    {
        this.songList = new ArrayList<>();
        setUpSongList(); //Er worden liedjes in de lijst gezet.
    }

    /**
     *Returnt een list met alle Song opbjecten uit de songlist.
     * @return ArrayList<Song>
     */
    public ArrayList<Song> getSongList()
    {
        return this.songList;
    }

    /**
     * Voeg een nummer toe aan de Songlist
     * @param title Naam vh nummer
     * @param artist Naam vd artiest
     * @param price prijs in euros
     * @param fileName bijv: naam.wav
     */
    public void addSong(String title, String artist, double price, String fileName)
    {
        Song songToAdd = new Song(title, artist, price, fileName);

        for (int i = 0; i < songList.size(); i++)
        {
            Song song = songList.get(i);
            if (!(song.getArtist().toLowerCase().equals(artist.toLowerCase()) && song.getTitle().toLowerCase().equals(title.toLowerCase())))
            {
                continue;
            }
            else
            {
                return;
            }
        }
        songList.add(songToAdd);
        //System.out.println(songToAdd.getTitle() + " - " + songToAdd.getArtist() + " is toegevoegd.");
    }

    /**
     * Verwijder een song uit de songlist
     * @param songToRemove Het nummer van het te verwijderen liedje
     */
    public void removeSong(int songToRemove)
    {
        try
        {
            songList.remove(songToRemove - 1);
            System.out.println("Song sucesfully deleted.");
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Dit nummer bestaat niet: " + songToRemove);
        }

    }
    
    /**
     * Check if a song with a certain filename already exists in the songlist
     * @param filename The filename to search for
     * @return True if the song doesn't exist, false if it does
     */
    public boolean isUniqueSong(String filename)
    {
        boolean searching = true;
        Iterator<Song> it = this.songList.iterator();
        while (searching && it.hasNext())
        {
            Song currentSong = it.next();
            if (currentSong.getFileName().equals(filename))
            {
                searching = false;
            }
        }
        return searching;
    }

    /**
     * Print alle beschikbare liedjes
     */
    public void printSongList()
    {
        for (int i = 0; i < songList.size(); i++)
        {
            Song song = songList.get(i);
            System.out.println(i + 1 + ": " + song.getTitle() + " - " + song.getArtist() + " - €" + String.format("%.2f", song.getPrice()));
        }
    }

    /**
     * Vul de songlist met liedjes
     */
    private void setUpSongList()
    {
        this.songList.add(new Song("Mine Right Now", "Sigrid", 2.00, "Mine_Right_now.wav"));
        this.songList.add(new Song("Basic (Acoustic)", "Sigrid", 3.00, "Basic.wav"));
        this.songList.add(new Song("Dynamite", "Sigrid", 5.00, "Dynamite.WAV"));
        this.songList.add(new Song("BRUH", "Your mom", 4.20, "BRUH.wav"));
        this.songList.add(new Song("Ping", "Pong", 4.20, "ping.wav"));
    }

}
