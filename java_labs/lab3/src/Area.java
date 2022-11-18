import java.lang.Math;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;

class Area{
    CandyBag bomboane;
    int number;
    String street;

    public Area(){}

    public Area(CandyBag bomboane, int number, String street){
        this.bomboane = bomboane;
        this.number = number;
        this.street = street;
    }


    public String getBirthdayCard(Area obj){
        //parcurgere array list si folosire tostring
        for(int j = 0; j <bomboane.bomboane.size(); j++){
            System.out.println(bomboane.bomboane.get(j).toString());
        }
        System.out.println("\n");

        //parcurgere arraylist si folosire instance of
        for(int i = 0; i < bomboane.bomboane.size(); i++){
            if(bomboane.bomboane.get(i) instanceof Lindt){
                System.out.println("Este Lindt si volumul este: " + ((Lindt)bomboane.bomboane.get(i)).getVolume());
                System.out.println(" Dimensiunile sunt :" + ((Lindt)bomboane.bomboane.get(i)).printLindtDim((Lindt)bomboane.bomboane.get(i)));
            }
            if(bomboane.bomboane.get(i) instanceof ChocAmor){
                System.out.println("Este ChocAmor si volumul este: " + ((ChocAmor)bomboane.bomboane.get(i)).getVolume());
                System.out.println(" Dimensiunile sunt :" + ((ChocAmor)bomboane.bomboane.get(i)).printChocAmorDim((ChocAmor)bomboane.bomboane.get(i)));
            }
            if(bomboane.bomboane.get(i) instanceof Baravelli){
                System.out.println("Este Baravelli si volumul este: " + ((Baravelli)bomboane.bomboane.get(i)).getVolume());
                System.out.println(" Dimensiunile sunt :" + ((Baravelli)bomboane.bomboane.get(i)).printBaravelliDim((Baravelli)bomboane.bomboane.get(i)));
            }
        }
        return "\nAdresa este: " + obj.street + ",nr " + obj.number + "\n" + "La multi ani!" + "\n";
    }

    //exemplu de evitare a instanceof
    public void noInstanceOfFunction(Area obj){
        for(int i = 0; i < bomboane.bomboane.size(); i++){
            bomboane.bomboane.get(i).noInstanceof();
        }
    }
}
