import java.util.ArrayList;

public class SongList
{
    public ArrayList<Song> songArrayList = new ArrayList<>();

    public void addSong(String title, String artist, Double price)
    {
        Song songToAdd = new Song(title, artist, price);

        for(int i = 0; i < songArrayList.size(); i++)
        {
            Song song = songArrayList.get(i);
            if(!(song.getArtist().toLowerCase().equals(artist.toLowerCase()) && song.getTitle().toLowerCase().equals(title.toLowerCase())))
            {
                continue;
            }
            else
            {
                return;
            }
        }

        songArrayList.add(songToAdd);
        //System.out.println(songToAdd.getTitle() + " - " + songToAdd.getArtist() + " is toegevoegd.");
    }

    public void removeSong(int songToRemove)
    {
        try
        {
            songArrayList.remove(songToRemove - 1);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Dit nummer bestaat niet: " + songToRemove);
        }

    }

    public void printSongList()
    {
        for(int i = 0; i < songArrayList.size(); i++)
        {
            Song song = songArrayList.get(i);
            System.out.println(i + 1 + ": " + song.getTitle() + " - " + song.getArtist() + " - €" + String.format("%.2f", song.getPrice()));
        }
    }
}
