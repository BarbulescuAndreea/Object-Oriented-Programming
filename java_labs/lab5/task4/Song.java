package lab5.task4;

public class Song {
    String name;
    int id;
    String composer;
    public Song(String name, int id, String composer){
        this.name = name;
        this.id = id;
        this.composer = composer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", composer='" + composer + '\'' +
                '}';
    }
}
