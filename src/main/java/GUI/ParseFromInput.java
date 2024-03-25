package GUI;

import Model.Monom;
import Model.Polinom;

public class ParseFromInput {
    public static Monom parseMonom(String m) {
        double coef;
        int power;
        if (m.contains("x")) {
            if (m.charAt(0) == 'x')
                coef = 1;
            else if (m.charAt(0) == '-' && m.charAt(1) == 'x')
                coef = -1;
            else
                coef = Double.parseDouble(m.substring(0, m.indexOf('x')));
            if (m.contains("^"))
                power = Integer.parseInt(m.substring(m.indexOf('^') + 1));
            else
                power = 1;
            return new Monom(coef, power);
        } else {
            coef = Integer.parseInt(m);
            return new Monom(coef, 0);
        }
    }

    public static Polinom parsePolinom(String s) {
        Polinom p = new Polinom();
        if (s.charAt(0) != '+' && s.charAt(0) != '-')
            s = "+" + s;
        int i = 0;
        while (i < s.length()) {
            int j = i + 1;
            while (j < s.length() && s.charAt(j) != '+' && s.charAt(j) != '-')
                j++;
            Monom m = parseMonom(s.substring(i, j));
            p.adaugaMonom(m);
            i = j;
        }
        return p;
    }
}
