package GUI;
        import Controller.CalculatorController;
        import Model.Monom;
        import Model.Polinom;
        import Service.UserService;

        import javax.swing.*;
        import java.awt.*;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class ConstructGUI extends JFrame {

    private JFrame frame;
    private final Color culButoane = new Color(0, 250, 154);
    private static final Font bigFont = new Font("PT Sans", Font.PLAIN, 26);
    private final Color culLabel = new Color(0, 0, 255);
    private final Color culTextField = new Color(65, 105, 225);

    public static JTextField tPolinom1, tPolinom2, tRezultat, tRezultat2;
    public JLabel lPolinom1, lPolinom2;
    public JButton bAdunare, bScadere, bInmultire, bImpartire, bDerivare, bIntegrare, bValidRasp, bTokens;

    public ConstructGUI(UserService userService) {
        initializeFrame();
        initializeComponents();
        addComponentsToFrame();
        CalculatorController calculatorController = new CalculatorController(this, userService);
        frame.setVisible(true);
    }

    private void initializeFrame() {
        this.frame = new JFrame();
        frame.setTitle("Polynomial Operations");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(470, 600);
        frame.setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        lPolinom1 = creeazaLabel("Polinom 1: ", bigFont);
        tPolinom1 = creeazaTextField(30);
        lPolinom2 = creeazaLabel("Polinom 2: ", bigFont);
        tPolinom2 = creeazaTextField(30);
        bAdunare = creeazaButon(null, "adunare", "  +  ", culButoane);
        bScadere = creeazaButon(null, "scadere", "  -  ", culButoane);
        bInmultire = creeazaButon(null, "inmultire", "  *  ", culButoane);
        bImpartire = creeazaButon(null, "impartire", "  :  ", culButoane);
        bDerivare = creeazaButon(null, "derivare", "  '  ", culButoane);
        bIntegrare = creeazaButon(null, "integrare", null, culButoane);
        bIntegrare.setIcon(new ImageIcon("image.png"));
        tRezultat = creeazaTextField(30);
        tRezultat2 = creeazaTextField(30);
        bValidRasp = creeazaButon(null, "valideaza raspuns", "valideaza raspunsul tau", culButoane);
        bTokens = creeazaButon(null, "Tokens", "vezi tokens", culButoane);
    }


    public static JTextField gettPolinom1() {
        return tPolinom1;
    }

    public static void settPolinom1(JTextField tPolinom1) {
        ConstructGUI.tPolinom1 = tPolinom1;
    }

    public static JTextField gettPolinom2() {
        return tPolinom2;
    }

    public static void settPolinom2(JTextField tPolinom2) {
        ConstructGUI.tPolinom2 = tPolinom2;
    }

    private void addComponentsToFrame() {
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
        frame.add(bTokens);
    }

    public JButton creeazaButon(ImageIcon newImage, String newAction, String newText, Color newColor) {
        JButton aux = new JButton(newText);
        aux.setIcon(newImage);
        aux.setBackground(newColor);
        aux.setForeground(Color.white);
        aux.setFont(bigFont);
        aux.setBorder(null);
        aux.setActionCommand(newAction);
        aux.setBorder(null);
        return aux;
    }

    public JLabel creeazaLabel(String newText, Font newFont) {
        JLabel newLabel = new JLabel(newText);
        newLabel.setFont(newFont);
        newLabel.setForeground(culLabel);
        return newLabel;
    }

    public JTextField creeazaTextField(int coloane) {
        JTextField newTextField = new JTextField(coloane);
        newTextField.setBackground(culTextField);
        newTextField.setForeground(Color.white);
        newTextField.setBorder(null);
        return newTextField;
    }

    public Polinom gettPolinom1Input() {
        return ParseFromInput.parsePolinom(tPolinom1.getText());
    }

    public Polinom gettPolinom2Input() {
        return ParseFromInput.parsePolinom(tPolinom2.getText());
    }

    public Polinom getRezUserInput() {
        return ParseFromInput.parsePolinom(tRezultat.getText());
    }
    public Polinom getRezUserInput2() {
        return ParseFromInput.parsePolinom(tRezultat2.getText());
    }



//    //pt lab 2
//    public static Polinom convertFromJTextField(JTextField textField) {
//        String input = textField.getText();
//        Polinom polinom = new Polinom();
//
//        // Definim un pattern pentru a extrage monoamele
//        Pattern pattern = Pattern.compile("([-+]?(\\d+\\.?\\d*|\\d*\\.?\\d+)([eE][-+]?\\d+)?)?\\*?x\\^(-?\\d+)");
//        Matcher matcher = pattern.matcher(input);
//
//        // Parcurgem input-ul și extragem monoamele
//        while (matcher.find()) {
//            double coeficient = Double.parseDouble(matcher.group(1) != null ? matcher.group(1) : "1");
//            int putere = Integer.parseInt(matcher.group(4));
//
//            Monom monom = new Monom(coeficient, putere);
//            polinom.adaugaMonom(monom);
//        }
//
//        return polinom;
//    }



}



