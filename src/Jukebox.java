public class Jukebox
{

    public double balance;
    public int soundVolume;
    public CashDrawer cashDrawer;
    public SongList songList;

    public Jukebox()
    {
        this.cashDrawer = new CashDrawer();
        this.songList = new SongList();
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


    public void setUpSongList()
    {
       this.songList.addSong("DuivenDisco", "DJ Koonstra", 5.00);
       this.songList.addSong("My new car", "Duitse koopman", 2.50);
       this.songList.addSong("Mijn moeder en ik", "YkelKampf", 1.50);

    }


    public double getBalance()
    {
        return this.balance;
    }

    public void higherVolume(int volumeUp)
    {
        soundVolume += volumeUp;
    }

    public void lowerVolume(int volumeDown)
    {
        soundVolume += volumeDown;
    }

    public int getSoundVolume()
    {
        return this.soundVolume;
    }

    public void selectSong(int songNumber)
    {
        try
        {
            double priceOfSong = this.songList.songArrayList.get(songNumber - 1).getPrice();
            if (this.balance - priceOfSong < 0)
            {
                throw new IllegalArgumentException();
            } else if (this.balance > priceOfSong)
            {
                double change = this.balance - priceOfSong;
                this.balance = 0;
                cashDrawer.revenue -= change;
                System.out.println("Wisselgeld: €" + String.format("%.2f", change));
                playSong(songNumber);
            } else if (this.balance == priceOfSong)
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

    public void playSong(int songNumber)
    {
        Song songToPlay = songList.songArrayList.get(songNumber - 1);
        System.out.println("Now Playing: " + songToPlay.getTitle() + " - " + songToPlay.getArtist());

    }


}
