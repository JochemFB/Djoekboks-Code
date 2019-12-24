import java.util.ArrayList;

public class SongList
{
    private ArrayList<Song> songList = new ArrayList<>();
    
    public ArrayList<Song> getSongList()
    {
        return this.songList;
    }

    public void addSong(String title, String artist, double price)
    {
        Song songToAdd = new Song(title, artist, price);

        for(int i = 0; i < songList.size(); i++)
        {
            Song song = songList.get(i);
            if(!(song.getArtist().toLowerCase().equals(artist.toLowerCase()) && song.getTitle().toLowerCase().equals(title.toLowerCase())))
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

    public void removeSong(int songToRemove)
    {
        try
        {
            songList.remove(songToRemove - 1);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Dit nummer bestaat niet: " + songToRemove);
        }

    }

    public void printSongList()
    {
        for(int i = 0; i < songList.size(); i++)
        {
            Song song = songList.get(i);
            System.out.println(i + 1 + ": " + song.getTitle() + " - " + song.getArtist() + " - €" + String.format("%.2f", song.getPrice()));
        }
    }
    
}
