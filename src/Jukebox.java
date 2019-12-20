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
       this.songList.addSong("DuivenDisco", "DJ Koonstra", 5.00);
       this.songList.addSong("My new car", "Duitse koopman", 2.50);
       this.songList.addSong("Mijn moeder en ik", "YkelKampf", 1.50);
    }

    public void higherVolume(int volumeUp)
    {
        soundVolume += volumeUp;
    }

    public void lowerVolume(int volumeDown)
    {
        soundVolume += volumeDown;
    }

    public void selectSong(int songNumber)
    {
        try
        {
            double priceOfSong = this.songList.getSongList().get(songNumber - 1).getPrice();
            if (this.balance - priceOfSong < 0)
            {
                throw new IllegalArgumentException();
            }
            else if (this.balance > priceOfSong)
            {
                double change = this.balance - priceOfSong;
                this.balance = 0;
                cashDrawer.revenue -= change; //revenue = revenue - change
                System.out.println("Wisselgeld: €" + String.format("%.2f", change));
                playSong(songNumber);
            }
            else if (this.balance == priceOfSong)
            {
                this.balance = 0;
                playSong(songNumber);
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Dit nummer bestaat niet.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Je hebt te weinig saldo om dit nummer af te spelen.");
        }
    }
    
    /**
     * Speel een nummer af (eigenlijk wordt het nu geprint maar het gaat om het idee)
     * @param songNumber Het nummer van het liedje dat moet worden afgespeeld
     */
    private void playSong(int songNumber)
    {
        Song songToPlay = songList.getSongList().get(songNumber - 1);
        System.out.println("Now Playing: " + songToPlay.getTitle() + " - " + songToPlay.getArtist());
    }
    
}
