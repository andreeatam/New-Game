package Model;

import java.util.Map;
import java.util.TreeMap;

public class Polinom {
    private TreeMap<Integer, Monom> monoms;
    public Polinom() {
        monoms = new TreeMap<>();       //am folosit  TreeMap ca sa fie ordonate (pentru a gasi gradul polinomului mai usor)
    }
    public Map<Integer,Monom> getMon(){
        return monoms;
    }

    public void adaugaMonom(Monom m) {
        if(m.getCoeficient()!=0) {          // daca coeficientul e 0 nu are rost sa il adaugam
            Monom aux = monoms.get(m.getPutere());     //cautam un monom in polinom cu aceeasi putere ca monomul introdus
            double c = 0;
            if (aux != null) {          //daca exista un monom cu aceeasi putere
                c = m.getCoeficient() + aux.getCoeficient();    //adunam coeficientii
                if(c==0)            //daca nou coeficient e 0 scoatem monomul cu puterea respectiva
                    monoms.remove(m.getPutere());
                else
                    aux.setCoeficient(c);    //altfel schimbam coeficientul cu noul coeficient
            }
            else
                monoms.put(m.getPutere(),m);     //daca nu exista deja un monom cu aceeasi putere il adaugam pur si simplu
        }
    }

    public int getGrad() {
        if(monoms.isEmpty())
            return 0;
        else
            return monoms.lastKey();     ///monomul l-am modificat in tree map ca sa fac asta
    }
    public Monom getMaxMonom(){
        if(monoms.isEmpty())
            return new Monom(0,0);
        return monoms.get(monoms.lastKey());      //monomul cu cea mai mare putere
    }

    public String convertPolinomToString() {              //transforma polinomul in string
        StringBuilder sb = new StringBuilder();
        for (Monom m : this.monoms.values()) {
            if (m.getCoeficient() > 0 && sb.length() > 0)
                sb.append("+");
            sb.append(m.getCoeficient());
            sb.append("x^");
            sb.append(m.getPutere());
        } return sb.toString();
    }



}
