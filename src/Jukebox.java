import java.io.*;
import javax.sound.sampled.*;


import java.io.File;


public class Jukebox
{

    private double balance; //De hoeveelheid geld die in de machine zit die gebruikt kan worden voor het betalen van nummers.
    private CashDrawer cashDrawer; //De geldlade
    private SongList songList; //De playlist met alle liedjes
    private Clip clip; //Het nummer dat de jukebox afspeelt
    private Float volume; //Het audio volume

    public Jukebox()
    {
        this.cashDrawer = new CashDrawer();
        this.songList = new SongList();
        this.clip = null;
        this.volume = -40.0f; //Het volume kan tussen de -80 en 6 liggen.
    }

    /**
     *
     * @return balance
     */
    public double getBalance()
    {
        return this.balance;
    }

    /**
     *
     * @return Een SongList object
     */
    public SongList getSongList()
    {
        return this.songList;
    }
    
    /**
     * Verkrijg de huidige clip
     * @return De huidige clip
     */
    public Clip getClip()
    {
        return this.clip;
    }

    /**
     * Gooi geld in de automaat
     *
     * @param money in euro's
     */
    public void insertCash(double money)
    {
        this.balance += money;
        this.cashDrawer.revenue += money;
        System.out.printf("Balance: " + String.format("%.2f", getBalance()) + "\n");
    }

    /**
     * Verhoog het volume.
     * @param value Het getal waarmee het volume verhoogd wordt
     */
    public void higherVolume(float value)
    {
        this.volume += value;
        FloatControl gain = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);

        if (this.volume < 6)
        {
            gain.setValue(this.volume);
            return;
        }
        else
        {
            System.out.println("Het geluid kan niet harder.");
        }
    }

    /**
     * Verlaag het volume.
     * @param value Het getal waarmee het volume verlaagd wordt
     */
    public void lowerVolume(float value)
    {
        this.volume -= value;
        FloatControl gain = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);

        if (this.volume > -78)
        {
            gain.setValue(this.volume);
            return;
        }
        else
        {
            System.out.println("Het geluid kan niet zachter.");
        }
    }

    /**
     *
     * @param songNumber Het nummer van het liedje.
     * @return Boolean. is er genoeg geld ingeworpen voor het gekozen nummer?
     */
    public boolean selectSong(int songNumber)
    {
        try
        {
            double priceOfSong = this.songList.getSongList().get(songNumber - 1).getPrice();
            if (this.balance - priceOfSong < 0)
            {
                return false;
            }
            else if (this.balance > priceOfSong)
            {
                double change = this.balance - priceOfSong;
                this.balance = 0;
                cashDrawer.revenue -= change; //revenue = revenue - change
                System.out.println("Wisselgeld: €" + String.format("%.2f", change));
                playSong(songNumber);
                return true;
            }
            else if (this.balance == priceOfSong)
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
     * Speel een nummer af.
     * @param songNumber Het nummer van het liedje dat moet worden afgespeeld
     */
    private void playSong(int songNumber)
    {
        Song songToPlay = songList.getSongList().get(songNumber - 1);
        System.out.println("Now Playing: " + songToPlay.getTitle() + " - " + songToPlay.getArtist());

        String playFileName = "songs/" + songToPlay.getFileName();
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(playFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            this.clip = clip;
            clip.start();


            //
            // Thread.sleep(clip.getMicrosecondLength()/1000);

        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");

            ex.printStackTrace();
        }
    }


    public void stopSong()
    {
        if (this.clip.isActive())
        {
            this.clip.stop();
            //this.clip.close();
        }
    }

}
