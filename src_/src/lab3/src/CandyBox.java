import java.lang.Math;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;

class CandyBox {
    private String flavor;
    private String origin;

    public CandyBox(){}

    public CandyBox(String flavor, String origin){
        this.flavor = flavor;
        this.origin = origin;
    }
    public float getVolume(){
        return 0;
    }

    @Override
    public String toString() {
        return "flavor='" + flavor + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public String getOrigin() {

        return origin;
    }

    public void setOrigin(String origin) {

        this.origin = origin;
    }

    public String getFlavor() {

        return flavor;
    }

    public void setFlavor(String flavor) {

        this.flavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandyBox candyBox = (CandyBox) o;
        return Objects.equals(flavor, candyBox.flavor) && Objects.equals(origin, candyBox.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavor, origin);
    }

    public void noInstanceof(){
    }
}














