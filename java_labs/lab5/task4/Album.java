package lab5.task4;

import java.util.ArrayList;

abstract class Album {
    ArrayList<Song> cantece = new ArrayList<>();
    public abstract void addSong(Song song);
    public void removeSong(Song song){
        cantece.remove(song);
    }
    @Override
    public String toString() {
        return "Album{" +
                "cantece=" + cantece +
                '}';
    }

}
