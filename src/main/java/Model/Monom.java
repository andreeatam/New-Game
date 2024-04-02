package Model;

public class Monom {
    private double coeficient;
    private int putere;

    public Monom(double coeficient, int putere) throws Exception {
        this.coeficient = coeficient;
        this.putere = putere;
        validatePowerMonom();
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


    public void validatePowerMonom() throws Exception{
        if(this.putere<0 || this.putere>50){
            throw new Exception("Power from input is not valid");
        }
    }



}
