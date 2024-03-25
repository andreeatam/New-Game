package Controller;

import GUI.ConstructGUI;
import Model.Polinom;
import Model.Result;
import Model.User;
import Service.OperationsService;
import Service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CalculatorController implements ActionListener {

    private MathController mathController;
    private ConstructGUI constructGUI;
    private OperationsService operationsService;

    private UserService userService;

    private boolean isBValidClicked = false;
    private boolean isBAdunareClicked = false;
    private boolean isBScadereClicked = false;
    private boolean isBInmultireClicked = false;
    private boolean isBImpartireClicked = false;
    private boolean isBDerivareClicked = false;
    private boolean isBIntegrareClicked = false;
    private boolean isBTokensClicked = false;

    public Polinom raspunsCorect1, raspunsCorect2;

    public CalculatorController(ConstructGUI constructGUI, UserService userService){
        this.mathController = new MathController();
        this.constructGUI = constructGUI;
        this.operationsService = new OperationsService();
        this.userService = userService;
        addListeners();
    }

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

    private void addListeners() {
        constructGUI.bAdunare.addActionListener(this);
        constructGUI.bScadere.addActionListener(this);
        constructGUI.bInmultire.addActionListener(this);
        constructGUI.bImpartire.addActionListener(this);
        constructGUI.bDerivare.addActionListener(this);
        constructGUI.bIntegrare.addActionListener(this);
        constructGUI.bValidRasp.addActionListener(this);
        constructGUI.bTokens.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        boolean areRaspCorect = false;
        addListeners();

        if (e.getSource() == constructGUI.bAdunare) {
            isBAdunareClicked = true;
        } else if (e.getSource() == constructGUI.bScadere) {
            isBScadereClicked = true;
        } else if (e.getSource() == constructGUI.bInmultire) {
            isBInmultireClicked = true;
        } else if (e.getSource() == constructGUI.bImpartire) {
            isBImpartireClicked = true;
        } else if (e.getSource() == constructGUI.bDerivare) {
            isBDerivareClicked = true;
        } else if (e.getSource() == constructGUI.bIntegrare) {
            isBIntegrareClicked = true;
        } else if (e.getSource() == constructGUI.bValidRasp) {
            isBValidClicked = true;
        } else if (e.getSource() == constructGUI.bTokens) {
            isBTokensClicked = true;
        } else {
            return;
        }

        if (isBValidClicked) { //e true
            if (isBAdunareClicked)
            {
                isBAdunareClicked = false; //resetam flag urile
                setRaspunsCorect1(mathController.adunare(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()));
                if (getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString()))
                {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 2);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;

                }
            } else
                JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.adunare(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());


            } else if (isBScadereClicked) {
                isBScadereClicked = false;
                setRaspunsCorect1(mathController.scadere(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()));
                if (getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 2);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;
                } else
                    JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.scadere(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());



            } else if (isBInmultireClicked) {
                isBInmultireClicked = false; //resetam flag urile
                setRaspunsCorect1(mathController.inmultire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()));
                if (getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 5);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;
                } else
                    JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.inmultire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());



            } else if (isBImpartireClicked) {
                isBImpartireClicked = false; //resetam flag urile
                Result rez = mathController.impartire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input());
                setRaspunsCorect1(rez.cat);
                setRaspunsCorect2(rez.rest);
                if ((getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (getRaspunsCorect2().convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 8);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;
                } else
                    JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + "cat: " + getRaspunsCorect1().convertPolinomToString() + " ,rest: " + getRaspunsCorect2().convertPolinomToString());


            } else if (isBDerivareClicked) {
                isBDerivareClicked = false; //resetam flag urile
                setRaspunsCorect1(mathController.derivare(constructGUI.gettPolinom1Input()));
                setRaspunsCorect2(mathController.derivare(constructGUI.gettPolinom2Input()));
                if ((getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (getRaspunsCorect2().convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 6);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;
                } else
                    JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este P1: " + mathController.derivare(constructGUI.gettPolinom1Input()).convertPolinomToString()+ " ,P2: " + mathController.derivare(constructGUI.gettPolinom2Input()).convertPolinomToString());



            } else if (isBIntegrareClicked) {
                isBIntegrareClicked = false; //resetam flag urile
                setRaspunsCorect1(mathController.integrare(constructGUI.gettPolinom1Input()));
                setRaspunsCorect2(mathController.integrare(constructGUI.gettPolinom2Input()));
                if ((getRaspunsCorect1().convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (getRaspunsCorect2().convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                    JOptionPane.showMessageDialog(null, "Raspuns corect");

                    String loggedInUsername = userService.getLoggedInUsername();
                    if (loggedInUsername != null) {
                        userService.updateTokens(loggedInUsername, 8);
                    } else {
                        System.out.println("Eroare: Utilizatorul nu este autentificat.");
                    }

                    areRaspCorect = true;
                } else
                    JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este P1: " + mathController.integrare(constructGUI.gettPolinom1Input()).convertPolinomToString() + " P2: " + mathController.integrare(constructGUI.gettPolinom2Input()).convertPolinomToString());

            }

            //if (areRaspCorect) {
                //updateScorInDB();
                areRaspCorect = false;
           // }

            isBValidClicked = false;
            constructGUI.bValidRasp.removeActionListener(this);

        if(isBTokensClicked){
                isBTokensClicked =false;
                String loggedInUsername = userService.getLoggedInUsername();
                if (loggedInUsername != null) {
                    int tokens = userService.getTokensForUser(loggedInUsername);
                    JOptionPane.showMessageDialog(null, "Your Total Tokens: " + tokens);
                } else {
                    JOptionPane.showMessageDialog(null, "Eroare: Utilizatorul nu este autentificat.");
                }
            }
        }


    }





