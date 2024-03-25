package Model;

public class Monom {
    private double coeficient;
    private int putere;

    public Monom(double coeficient, int putere) {
        this.coeficient = coeficient;
        this.putere = putere;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return putere;
    }

    @Override
    public String toString() {
        return coeficient + "x^" + putere;
    }

}
