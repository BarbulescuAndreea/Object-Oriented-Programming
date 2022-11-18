class Lindt extends CandyBox{
    float length;
    float width;
    float height;

    public Lindt(){}

    public Lindt(String flavor, String origin, float length,float width, float height){
        super.setFlavor(flavor);
        super.setOrigin(origin);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public float getVolume(){
        return length * width * height;
    }

    @Override
    public String toString() {
        return "The " + getOrigin() + " " +
                " " + getFlavor() + " " +
                "has volume " +getVolume() + " " + '}';
    }
    public String printLindtDim(Lindt cutie){
        return cutie.length + "," + cutie.width + "," + cutie.height;
    }

    public void noInstanceof(){
        System.out.println("Lindt bomboane fara instanceof");
    }
}
