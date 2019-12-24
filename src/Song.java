public class Song
{
    /** De titel van het liedje */
    private String title;
    /** De artiest van het liedje */
    private String artist;
    /** De prijs van het liedje */
    private double price;
    /** De bestandsnaam van het liedje*/
    private String fileName;
    
    public Song(String title, String artist, double price, String fileName)
    {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.fileName = fileName;
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

    public String getFileName()
    {
        return this.fileName;
    }
}
