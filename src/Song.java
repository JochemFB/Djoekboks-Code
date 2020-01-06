public class Song
{
    /** De titel van het liedje */
    private String title;
    /** De artiest van het liedje */
    private String artist;
    /** De prijs van het liedje */
    private double price;
    /** De bestandsnaam van het nummer */
    private String fileName;


    public Song(String title, String artist, double price, String fileName)
    {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.fileName = fileName; //naam.wav
    }

    /**
     *
     * @return Title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     *
     * @return Artist
     */
    public String getArtist()
    {
        return this.artist;
    }

    /**
     *
     * @return Price
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     *
     * @return Filename
     */
    public String getFileName()
    {
        return this.fileName;
    }

}
