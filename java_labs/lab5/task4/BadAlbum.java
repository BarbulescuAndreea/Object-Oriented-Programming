package lab5.task4;

public class BadAlbum extends Album{
    //doar melodii care au nume de 3 litere și id număr palindrom.
    public BadAlbum(){
        System.out.println("Bad album is: \n");
    }

    public boolean letters(String name){
        boolean val = true;
        if(name.length() != 3){
            val = false;
        }
        return val;
    }
    public boolean palindrom(int number){
        boolean ispal = true;
        int aux = number;
        int rest = 0;
        int suma = 0;
        while(number >0){
            rest = number % 10;  //getting remainder
            suma =(suma * 10)+ rest;
            number =number /10;
        }
        if(aux != suma){
            ispal = false;
        }
        return ispal;
    }
    public void addSong(Song song){
        if(letters(song.name) == true && palindrom(song.id) == true){
            System.out.println(letters(song.name));
            System.out.println(palindrom(song.id));
            cantece.add(song);
        }
    }
}
