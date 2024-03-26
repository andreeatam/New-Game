package Service;

import Model.Monom;
import Model.Polinom;
import Model.Result;

public class OperationsService implements IOperationsService{

    @Override
    public Polinom aduna(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();
        for (Monom m : p1.getMon().values())
            rez.adaugaMonom(m);
        for (Monom m1 : p2.getMon().values())
            rez.adaugaMonom(m1);
        return rez;
    }

    @Override
    public Polinom scadere(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();
        for (Monom m : p1.getMon().values())
        {
            Monom m2=p2.getMon().get(m.getPutere());
            if(m2!=null)
            {
                m2=new Monom(m.getCoeficient()-m2.getCoeficient(),m.getPutere());
                if(m2.getCoeficient()!=0)
                    rez.adaugaMonom(m2);
            }
            else
                rez.adaugaMonom(new Monom(m.getCoeficient(),m.getPutere()));
        }
        for(Monom m:p2.getMon().values())
        {
            Monom m2 =p1.getMon().get(m.getPutere());
            if(m2==null)
            {
                m2=new Monom(-m.getCoeficient(), m.getPutere());
                if(m2.getCoeficient()!=0)
                    rez.adaugaMonom(m2);
            }
        }
        return rez;
    }

    @Override
    public Polinom inmultire(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();
        for (Monom m1 : p1.getMon().values())
            for (Monom m2 : p2.getMon().values()) {
                double c=m1.getCoeficient()*m2.getCoeficient();
                int p=m1.getPutere()+m2.getPutere();
                Monom aux=new Monom(c,p);
                rez.adaugaMonom(aux);
            }
        return rez;
    }

    @Override
    public Result impartire(Polinom p1, Polinom p2) {
        Polinom poli = new Polinom();
        if (p1.getGrad() < p2.getGrad())
            return new Result(null,null);
        Polinom aux = new Polinom();
        aux.adaugaMonom(p1.getMaxMonom());
        while (aux.getGrad() >= p2.getGrad()) {
            double c = aux.getMaxMonom().getCoeficient() / p2.getMaxMonom().getCoeficient();
            int p = aux.getMaxMonom().getPutere() - p2.getMaxMonom().getPutere();
            Monom m_aux = new Monom(c, p);
            Polinom t = new Polinom();
            t.adaugaMonom(m_aux);
            for(Monom m:t.getMon().values())
                poli.adaugaMonom(m);
            Polinom temp2 = new Polinom();
            for (Monom m : p2.getMon().values()) {
                double coef = m.getCoeficient() * c;
                int power = m.getPutere() + p;
                Monom mon = new Monom(coef, power);
                temp2.adaugaMonom(mon);
            }
            p1=scadere(p1,temp2);
            aux = new Polinom();
            aux.adaugaMonom(p1.getMaxMonom());
        }
        Result rez=new Result(poli,p1);
        return rez;
    }

    @Override
    public Polinom derivare(Polinom p1) {
        Polinom rez = new Polinom();
        Monom m1;
        for (Monom m : p1.getMon().values())
        {
            int p=m.getPutere();
            double c=m.getCoeficient();
            if(m.getPutere() !=0)
            {
                c= c * p;
                p--;
            }
            else
                c=0;
            m1=new Monom(c,p);
            rez.adaugaMonom(m1);
        }
        return rez;
    }

    @Override
    public Polinom integrare(Polinom p1) {
        Polinom rez = new Polinom();
        for (Monom m : p1.getMon().values())
        {
            double c=m.getCoeficient()/(m.getPutere()+1);
            int p=m.getPutere()+1;
            Monom aux=new Monom(c, p);
            rez.adaugaMonom(aux);

        }
        return rez;
    }

}
