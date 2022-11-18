package lab10.builder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final void main(String[] args){
        //casa doar cu facilitati obligatorii
        House.HouseBuilder house = new House.HouseBuilder("Dna Ghica", 5, "Centrala");
        System.out.println(house);

        //case si cu facilitati optionale
        List<String> electrocasnice = new ArrayList<>();
        electrocasnice.add("Prajitor de paine");
        electrocasnice.add( "Aparat de cafea");
        electrocasnice.add("Cuptor cu microunde");
        House.HouseBuilder house2 = new House.HouseBuilder("Dna Ghica", 5, "Centrala");
        house2.electronicCount(electrocasnice);
        house2.poolCount("piscina cu jacuzzi");
        house2.securityCount("alarma de incendiu");
        System.out.println(house2);

        List<String> electrocasnice2 = new ArrayList<>();
        electrocasnice2.add("Prajitor de paine");

        electrocasnice2.add( "Mixer");
        electrocasnice2.add("Tocator de carne");
        electrocasnice2.add("blender");
        House.HouseBuilder house3 = new House.HouseBuilder("Aparatorii Patriei", 9, "Semineu");
        house3.electronicCount(electrocasnice2);
        house3.poolCount(null);
        house3.securityCount("alarma de securitate noaptea");
        System.out.println(house3);
    }
}
