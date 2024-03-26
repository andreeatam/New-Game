package Controller;

import GUI.ConstructGUI;
import Model.Polinom;
import Model.Result;
import Service.OperationsService;
import Service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {

    private MathController mathController;
    private ConstructGUI constructGUI;
    private OperationsService operationsService;
    private UserService userService;
    private String lastOperationButtonClicked;
    private boolean isOperationClicked = false;
    private JButton currentOperationButton;

    public CalculatorController(ConstructGUI constructGUI, UserService userService) {
        this.mathController = new MathController();
        this.constructGUI = constructGUI;
        this.operationsService = new OperationsService();
        this.userService = userService;
        addOperationListeners();
        addValidationListener();
        addTokensListener();

        constructGUI.bValidRasp.addActionListener(this);
    }


    private void addOperationListeners() {
        constructGUI.bAdunare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bAdunare);
            }
        });

        constructGUI.bScadere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bScadere);
            }
        });

        constructGUI.bInmultire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bInmultire);
            }
        });

        constructGUI.bImpartire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bImpartire);
            }
        });

        constructGUI.bDerivare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bDerivare);
            }
        });

        constructGUI.bIntegrare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperationButton(constructGUI.bIntegrare);
            }
        });
    }

    private void handleOperationButton(JButton button) {
        currentOperationButton = button;
        lastOperationButtonClicked = button.getText();
        isOperationClicked = true;
    }

    private void addTokensListener() {
        constructGUI.bTokens.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        if (sourceButton == constructGUI.bTokens) {
            handleTokensButton();
        } else if (isOperationButton(sourceButton)) {
            lastOperationButtonClicked = sourceButton.getName();
        } else if (sourceButton == constructGUI.bValidRasp) {
            handleValidation();
        }
    }


    private boolean isOperationButton(JButton button) {
        return button == constructGUI.bAdunare ||
                button == constructGUI.bScadere ||
                button == constructGUI.bInmultire ||
                button == constructGUI.bImpartire ||
                button == constructGUI.bDerivare ||
                button == constructGUI.bIntegrare;
    }

    private void addValidationListener() {
        constructGUI.bValidRasp.addActionListener(this);
    }

    private void handleValidation() {
        if (lastOperationButtonClicked == null) {
            JOptionPane.showMessageDialog(null, "Select an operation first!");
            return;
        }

        performOperation();
    }

    private void handleTokensButton() {
        String loggedInUsername = userService.getLoggedInUsername();
        if (loggedInUsername != null) {
            int tokens = userService.getTokensForUser(loggedInUsername);
            JOptionPane.showMessageDialog(null, "Your Total Tokens: " + tokens);
        } else {
            JOptionPane.showMessageDialog(null, "Error: User not logged in.");
        }
    }

    private void performOperation() {
        String loggedInUsername = userService.getLoggedInUsername();
        Polinom result;
        Polinom result2;

        if (isOperationClicked) {
            switch (lastOperationButtonClicked) {
                case "  +  ":
                    result = mathController.adunare(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input());
                    if (result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 2);
                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.adunare(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());
                    break;

                case "  -  ":
                    result = mathController.scadere(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input());
                    if (result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 2);
                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.scadere(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());
                    break;

                case "  *  ":
                    result = mathController.inmultire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input());
                    if (result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 5);
                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + mathController.inmultire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input()).convertPolinomToString());
                    break;

                case "  :  ":
                    Result rez = mathController.impartire(constructGUI.gettPolinom1Input(), constructGUI.gettPolinom2Input());
                    result = rez.cat;
                    result2 = rez.rest;
                    if ((result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (result2.convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 6);

                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este: " + "cat: " + result.convertPolinomToString() + " ,rest: " + result2.convertPolinomToString());
                    break;

                case "  '  ":
                    result = mathController.derivare(constructGUI.gettPolinom1Input());
                    result2 = mathController.derivare(constructGUI.gettPolinom2Input());
                    if ((result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (result2.convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 6);
                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este P1: " + mathController.derivare(constructGUI.gettPolinom1Input()).convertPolinomToString() + " ,P2: " + mathController.derivare(constructGUI.gettPolinom2Input()).convertPolinomToString());
                    break;


                case "":
                    result = mathController.integrare(constructGUI.gettPolinom1Input());
                    result2 = mathController.integrare(constructGUI.gettPolinom2Input());
                    if ((result.convertPolinomToString().equals(constructGUI.getRezUserInput().convertPolinomToString())) && (result2.convertPolinomToString().equals(constructGUI.getRezUserInput2().convertPolinomToString()))) {
                        JOptionPane.showMessageDialog(null, "Raspuns corect");
                        userService.updateTokens(loggedInUsername, 8);

                    } else
                        JOptionPane.showMessageDialog(null, "Raspuns incorect, " + " corect este P1: " + mathController.integrare(constructGUI.gettPolinom1Input()).convertPolinomToString() + " P2: " + mathController.integrare(constructGUI.gettPolinom2Input()).convertPolinomToString());
                    break;
                default:
                    break;
            }
            isOperationClicked = false;
        }
    }
}

