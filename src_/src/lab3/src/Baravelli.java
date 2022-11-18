class Baravelli extends CandyBox {
    float radius;
    float height;

    public Baravelli() {
    }

    public Baravelli(String flavor, String origin, float radius, float height) {
        super.setFlavor(flavor);
        super.setOrigin(origin);
        this.radius = radius;
        this.height = height;
    }

    public float getVolume() {

        return (float) Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return "The " + getOrigin() + " " +
                " " + getFlavor() + " " +
                "has volume " + getVolume() + " " + '}';
    }

    public String printBaravelliDim(Baravelli cutie) {
        return cutie.radius + "," + cutie.height;
    }
    public void noInstanceof(){
        System.out.println("Baravelli bomboane fara instanceof");
    }
}
