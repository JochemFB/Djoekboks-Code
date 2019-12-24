public class Song
{
    /** De titel van het liedje */
    private String title;
    /** De artiest van het liedje */
    private String artist;
    /** De prijs van het liedje */
    private double price;

    
    public Song(String title, String artist, double price)
    {
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getArtist()
    {
        return this.artist;
    }
    
    public double getPrice()
    {
        return this.price;
    }

}
