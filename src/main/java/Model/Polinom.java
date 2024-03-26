package Model;

import java.util.Map;
import java.util.TreeMap;

public class Polinom {
    private TreeMap<Integer, Monom> monoms;
    public Polinom() {
        monoms = new TreeMap<>();
    }
    public Map<Integer,Monom> getMon(){
        return monoms;
    }

    public void adaugaMonom(Monom m) {
        if(m.getCoeficient()!=0) {
            Monom aux = monoms.get(m.getPutere());
            double c = 0;
            if (aux != null) {
                c = m.getCoeficient() + aux.getCoeficient();
                if(c==0)
                    monoms.remove(m.getPutere());
                else
                    aux.setCoeficient(c);
            }
            else
                monoms.put(m.getPutere(),m);
        }
    }

    public int getGrad() {
        if(monoms.isEmpty())
            return 0;
        else
            return monoms.lastKey();
    }
    public Monom getMaxMonom(){
        if(monoms.isEmpty())
            return new Monom(0,0);
        return monoms.get(monoms.lastKey());
    }

    public String convertPolinomToString() {
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
