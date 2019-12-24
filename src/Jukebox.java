import java.io.*;
import javax.sound.sampled.*;

public class Jukebox
{

    private double balance;
    private int soundVolume;
    private CashDrawer cashDrawer;
    private SongList songList;

    public Jukebox()
    {
        this.cashDrawer = new CashDrawer();
        this.songList = new SongList();
    }

    public double getBalance()
    {
        return this.balance;
    }

    public int getSoundVolume()
    {
        return this.soundVolume;
    }

    public SongList getSongList()
    {
        return this.songList;
    }

    /**
     * Gooi geld in de automaat
     *
     * @param money in euro's
     */
    public void insertCash(double money)
    {
        balance += money;
        cashDrawer.revenue += money;
        System.out.printf("Balance: " + String.format("%.2f", getBalance()) + "\n");
    }

    /**
     * Vul de songlist met liedjes
     */
    public void setUpSongList()
    {
        this.songList.addSong("Basic (Acoustic)", "Sigrid", 5.00, ".\\music\\basic.mp3");
        this.songList.addSong("Budapest cover", "Sigrid", 2.50, "budapest.mp3");
        this.songList.addSong("Dynamite", "Sigrid", 1.50, "./music/dynamite.WAV");
    }

    public void higherVolume(int volumeUp)
    {
        soundVolume += volumeUp;
    }

    public void lowerVolume(int volumeDown)
    {
        soundVolume -= volumeDown;
    }

    public boolean selectSong(int songNumber)
    {
        try
        {
            double priceOfSong = this.songList.getSongList().get(songNumber - 1).getPrice();
            if (this.balance - priceOfSong < 0)
            {
                return false;
            } else if (this.balance > priceOfSong)
            {
                double change = this.balance - priceOfSong;
                this.balance = 0;
                cashDrawer.revenue -= change; //revenue = revenue - change
                System.out.println("Wisselgeld: €" + String.format("%.2f", change));
                playSong(songNumber);
                return true;
            } else if (this.balance == priceOfSong)
            {
                this.balance = 0;
                playSong(songNumber);
                return true;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Dit nummer bestaat niet.");
            return false;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Je hebt te weinig saldo om dit nummer af te spelen.");
            return false;
        }
        return false;
    }

    /**
     * Speel een nummer af (eigenlijk wordt het nu geprint maar het gaat om het idee)
     *
     * @param songNumber Het nummer van het liedje dat moet worden afgespeeld
     */
    private void playSong(int songNumber)
    {
        Song songToPlay = songList.getSongList().get(songNumber - 1);
        System.out.println("Now Playing: " + songToPlay.getTitle() + " - " + songToPlay.getArtist());
    }

}
