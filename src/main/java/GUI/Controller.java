package GUI;

import Operation.Operations;
import Results.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final View view;
    private boolean isBAdunareClicked, isBScadereClicked,isBInmultireClicked;
    private boolean isBImpartireClicked,isBDerivareClicked, isBIntegrareClicked,isBValidClicked;
    private final Operations op=new Operations();
    public Controller(View v){
        this.view = v;
    }


    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();

        view.bAdunare.addActionListener(this);
        view.bScadere.addActionListener(this);
        view.bInmultire.addActionListener(this);
        view.bImpartire.addActionListener(this);
        view.bDerivare.addActionListener(this);
        view.bIntegrare.addActionListener(this);

        view.bValidRasp.addActionListener(this);

        if(e.getSource()==view.bAdunare){
            isBAdunareClicked=true;
        } else if(e.getSource()==view.bScadere){
            isBScadereClicked=true;
        }else if(e.getSource()==view.bInmultire) {
            isBInmultireClicked = true;
        } else if(e.getSource()==view.bImpartire){
            isBImpartireClicked=true;
        }else if(e.getSource()==view.bDerivare) {
            isBDerivareClicked = true;
        } else if(e.getSource()==view.bIntegrare) {
            isBIntegrareClicked = true;
        } else if(e.getSource()==view.bValidRasp){
            isBValidClicked=true;}


        if(isBValidClicked){
            if(isBAdunareClicked){
                view.setRaspunsCorect1(op.aduna(view.gettPolinom1(),view.gettPolinom2()));
                if(view.getRaspunsCorect1().toString().equals(view.getRezUser().toString()))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este: " + op.aduna(view.gettPolinom1(),view.gettPolinom2()).toString());
                isBAdunareClicked=false; //resetam flag urile


            } else if(isBScadereClicked) {
                view.setRaspunsCorect1(op.scadere(view.gettPolinom1(),view.gettPolinom2()));
                if(view.getRaspunsCorect1().toString().equals(view.getRezUser().toString()))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este: " + op.scadere(view.gettPolinom1(),view.gettPolinom2()).toString());
                isBScadereClicked=false;


            } else if(isBInmultireClicked) {
                view.setRaspunsCorect1(op.inmultire(view.gettPolinom1(),view.gettPolinom2()));
                if(view.getRaspunsCorect1().toString().equals(view.getRezUser().toString()))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este: " + op.inmultire(view.gettPolinom1(),view.gettPolinom2()).toString());
                isBInmultireClicked=false; //resetam flag urile


            } else if(isBImpartireClicked){
                Result rez=op.impartire(view.gettPolinom1(),view.gettPolinom2());
                view.setRaspunsCorect1(rez.cat);
                view.setRaspunsCorect2(rez.rest);
                if((view.getRaspunsCorect1().toString().equals(view.getRezUser().toString())) && (view.getRaspunsCorect2().toString().equals(view.getRezUser2().toString())))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este: " + "cat: " + view.getRaspunsCorect1().toString() + " ,rest: " +  view.getRaspunsCorect2().toString());
                isBImpartireClicked=false; //resetam flag urile


            } else if(isBDerivareClicked) {
                view.setRaspunsCorect1(op.derivare(view.gettPolinom1()));
                view.setRaspunsCorect2(op.derivare(view.gettPolinom2()));
                if((view.getRaspunsCorect1().toString().equals(view.getRezUser().toString())) && (view.getRaspunsCorect2().toString().equals(view.getRezUser2().toString())))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este P1: " + op.derivare(view.gettPolinom1()) +  " ,P2: " + op.derivare(view.gettPolinom2()));
                isBDerivareClicked=false; //resetam flag urile


            } else if(isBIntegrareClicked) {
                view.setRaspunsCorect1(op.integrare(view.gettPolinom1()));
                view.setRaspunsCorect2(op.integrare(view.gettPolinom2()));
                if((view.getRaspunsCorect1().toString().equals(view.getRezUser().toString())) && (view.getRaspunsCorect2().toString().equals(view.getRezUser2().toString())))
                    JOptionPane.showMessageDialog(null,"Raspuns corect");
                else
                    JOptionPane.showMessageDialog(null,"Raspuns incorect, "+ " corect este P1: " + op.integrare(view.gettPolinom1()) +" P2: " +  op.integrare(view.gettPolinom2()));
                isBIntegrareClicked=false; //resetam flag urile
            }

        isBValidClicked=false;
        }



    }
}
