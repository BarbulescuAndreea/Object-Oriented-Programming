class ChocAmor extends CandyBox {
    float length;

    public ChocAmor() {
    }

    public ChocAmor(String flavor, String origin, float length) {
        super.setFlavor(flavor);
        super.setOrigin(origin);
        this.length = length;
    }

    public float getVolume() {
        return length * length * length;
    }

    @Override
    public String toString() {
        return "The " + getOrigin() + " " +
                getFlavor() + " " +
                "has volume " + getVolume() + " " + '}';
    }
    public float printChocAmorDim(ChocAmor cutie){
        return cutie.length;
    }

    public void noInstanceof(){
        System.out.println("ChocAmor bomboane fara instanceof");
    }
}
