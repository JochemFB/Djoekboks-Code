public class Song
{
    private String title;
    private String artist;
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
