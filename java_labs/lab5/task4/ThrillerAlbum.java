package lab5.task4;

public class ThrillerAlbum extends Album{
    //ThrillerAlbum conține melodii scrise doar de Michael Jackson
    //și au id-ul număr par
    public ThrillerAlbum(){
        System.out.println("Thriller album is:\n");
    }
    public boolean isMichaelJ(String name){
        boolean bool = true;
        String str = "Michael Jackson";
        if(name.equals(str)){
            bool = false;
        }
        return bool;
    }
    public boolean parID(int number){
        boolean par = true;
        if((number % 2) != 0){
            par = false;
        }
        return par;
    }
    public void addSong(Song song){
        if(isMichaelJ(song.name) == true && parID(song.id) == true){
            System.out.println(isMichaelJ(song.name));
            cantece.add(song);
        }
    }

}
