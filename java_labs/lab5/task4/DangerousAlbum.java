package lab5.task4;

public class DangerousAlbum extends Album{
    //are doar melodii cu id nr prim
    Song song;

    public DangerousAlbum(){
        System.out.println("Dangerous album is:\n");
    }

    public boolean isPrim(int number){
        int temp;
        boolean isPrime = true;
        for(int i=2;i<=number/2;i++)
        {
            temp=number%i;
            if(temp==0)
            {
                isPrime=false;
                break;
            }
        }
        return isPrime;
    }
    public void addSong(Song song){
        if(isPrim(song.id) == true){
            cantece.add(song);
        }
    }
}
