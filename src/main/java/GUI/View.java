package GUI;

import Polynomial.Monom;
import Polynomial.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{

    private  JFrame frame;
    private Polinom raspunsCorect1, raspunsCorect2;
    private final Color culButoane = new Color(0,250,154);
    private final Color culLabel= new Color(0,0,255);

    private final Color culTextField = new Color(65,105,225);
    private static final Font bigFont = new Font("PT Sans",Font.PLAIN,26);

    public JLabel lPolinom1, lPolinom2;

    public JTextField tPolinom1,tPolinom2, tRezultat, tRezultat2;
    public JButton bAdunare, bScadere,bInmultire, bImpartire,bDerivare,bIntegrare,bValidRasp,bRanking,bTokens,bClasament;

    Controller controller = new Controller(this);

    public JFrame getFrame(){
        return frame;
    }


    public Polinom gettPolinom1() {
        return parsePolinom(tPolinom1.getText());
    }

    public Polinom gettPolinom2() {
        return parsePolinom(tPolinom2.getText());
    }

    public Polinom getRezUser(){ return parsePolinom(tRezultat.getText());}

    public Polinom getRezUser2() {return parsePolinom(tRezultat2.getText());}

    public Polinom getRaspunsCorect1() {
        return raspunsCorect1;
    }

    public void setRaspunsCorect1(Polinom raspunsCorect1) {
        this.raspunsCorect1 = raspunsCorect1;
    }

    public Polinom getRaspunsCorect2() {
        return raspunsCorect2;
    }

    public void setRaspunsCorect2(Polinom raspunsCorect2) {
        this.raspunsCorect2 = raspunsCorect2;
    }

    private Monom parseMonom(String m) {
        double coef;
        int power;
        if (m.contains("x")) {
            if (m.charAt(0) == 'x')     //daca primul caracter e "x" pune 1 la coeficient
                coef = 1;
            else if (m.charAt(0) == '-' && m.charAt(1) == 'x')     //daca primul caracter e "-" si al doile "x" coeficientul e -1
                coef = -1;
            else
                coef = Double.parseDouble(m.substring(0, m.indexOf('x')));        //altfel coeficientu e ce se afla pana la "x"
            if (m.contains("^"))
                power = Integer.parseInt(m.substring(m.indexOf('^') + 1));      //puterea e ce e dupa "^"
            else
                power = 1;
            return new Monom(coef, power);
        } else {
            coef = Integer.parseInt(m);     //daca nu exista "x" in string rezulta ca avem puterea x^0
            return new Monom(coef, 0);
        }
    }
    public Polinom parsePolinom(String s) {
        Polinom p = new Polinom();
        if (s.charAt(0) != '+' && s.charAt(0) != '-')       //verifica primul termen daca are semn in fata, daca nu are pune "+"
            s = "+" + s;
        int i = 0;
        while (i < s.length()) {
            int j = i + 1;
            while (j < s.length() && s.charAt(j) != '+' && s.charAt(j) != '-')    //numara cate caractere sunt intre semne
                j++;
            Monom m = parseMonom(s.substring(i, j));     // creaza monomul gasit intre semne
            p.adaugaMonom(m);
            i = j;
        }
        return p;
    }
    public View(){
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(470,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.prepareGui();
    }
    public JButton creazaButon(ImageIcon newImage, String newAction, String newText, Color newColor)
    {
        JButton aux=new JButton(newText);
        aux.setIcon(newImage);
        aux.setBackground(newColor);
        aux.setForeground(Color.white);
        aux.setFont(bigFont);
        aux.setBorder(null);
        aux.setActionCommand(newAction);
        aux.addActionListener(this.controller);
        aux.setBorder(null);
        return aux;
    }
    public JLabel creazaLabel(String newText, Font newFont)
    {
        JLabel newLabel=new JLabel(newText);
        newLabel.setFont(newFont);
        newLabel.setForeground(culLabel);
        return newLabel;
    }


    public JTextField creazaTextField(int coloane)
    {
        JTextField newTextField=new JTextField(coloane);
        newTextField.setBackground(culTextField);
        newTextField.setForeground(Color.white);
        newTextField.setBorder(null);
        return newTextField;
    }
    public void prepareGui() {

        lPolinom1 = creazaLabel("Polinom 1: ", bigFont);
        tPolinom1 = creazaTextField(30);

        lPolinom2 = creazaLabel("Polinom 2: ", bigFont);
        tPolinom2 = creazaTextField(30);


        bAdunare=creazaButon(null,"adunare","  +  ",culButoane);
        bScadere=creazaButon(null,"scadere","  -  ",culButoane);
        bInmultire=creazaButon(null,"inmultire","  *  ",culButoane);
        bImpartire=creazaButon(null,"impartire","  :  ",culButoane);
        bDerivare=creazaButon(null,"derivare","  '  ",culButoane);
        bIntegrare=creazaButon(null,"integrare",null,culButoane);
        bIntegrare.setIcon(new ImageIcon("image.png"));

        tRezultat = creazaTextField(30);

        tRezultat2 = creazaTextField(30);

        bValidRasp=creazaButon(null,"valideaza raspuns","valideaza raspunsul tau",culButoane);
        bRanking=creazaButon(null,"ranking (xp)", "vezi ranking (xp)", culButoane);
        bTokens=creazaButon(null,"Tokens","vezi tokens", culButoane);
        bClasament=creazaButon(null, "clasament", "vezi clasamentul",culButoane);

        frame.setLayout(new FlowLayout());

        frame.add(lPolinom1);
        frame.add(tPolinom1);
        frame.add(lPolinom2);
        frame.add(tPolinom2);

        frame.add(bAdunare);
        frame.add(bScadere);
        frame.add(bInmultire);
        frame.add(bImpartire);
        frame.add(bDerivare);
        frame.add(bIntegrare);


        frame.add(tRezultat);
        frame.add(tRezultat2);

        frame.add(bValidRasp);
        frame.add(bRanking);
        frame.add(bTokens);
        frame.add(bClasament);
    }
    public void showMessage()
    {
        JOptionPane.showMessageDialog(frame,"Nu se poate realiza impartirea");
    }
}
