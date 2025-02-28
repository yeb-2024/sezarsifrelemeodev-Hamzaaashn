import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anasayfa extends JFrame implements ActionListener {
    JButton şifreleme, şifreçözme;
    JTextField anahter, mesaj;
    JTextArea sonuç;

    public Anasayfa() {
        setTitle("Şifreleme Uygulaması");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel anahter = new JLabel("Anahtar 0-25");
        anahter.setBounds(50, 30, 100, 30);
        add(anahter);

        this.anahter = new JTextField();
        this.anahter.setBounds(150, 30, 100, 30);
        add(this.anahter);

        JLabel labelmesaj = new JLabel("Mesaj");
        labelmesaj.setBounds(50, 80, 100, 30);
        labelmesaj.setBackground(Color.RED);
        add(labelmesaj);

        mesaj = new JTextField();
        mesaj.setBounds(150, 80, 300, 30);
        add(mesaj);
        şifreleme = new JButton("Şifrele");
        şifreleme.setBounds(100, 150, 100, 40);
        şifreleme.addActionListener(this);
        add(şifreleme);

        şifreçözme = new JButton("Şifre Çöz");
        şifreçözme.setBounds(250, 150, 100, 40);
        şifreçözme.addActionListener(this);
        add(şifreçözme);

        sonuç = new JTextArea();
        sonuç.setBounds(50, 220, 400, 100);
        sonuç.setEditable(false);
        add(sonuç);

        setVisible(true);
    }
    public static String şifrelemee(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char karakter : text.toCharArray()) {
            if (Character.isLetter(karakter)) {
                char base = Character.isLowerCase(karakter) ? 'a' : 'A';
                karakter = (char) (base + (karakter - base + key) % 26);
            }
            result.append(karakter);
        }

        return result.toString();
    }

    public static String çözmee(String text, int key) {
        return şifrelemee(text, 26 - key);
    }

    public static void main(String[] args) {
        new Main();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int anahter = Integer.parseInt(this.anahter.getText());
            String mesaj = this.mesaj.getText();

            if (e.getSource() == şifreleme) {
                String şifreleme = şifrelemee(mesaj, anahter);
                sonuç.setText("Şifrelenmiş Mesaj: " + şifreleme);
            } else if (e.getSource() == şifreçözme) {
                String şifreçözme = çözmee(mesaj, anahter);
                sonuç.setText("Çözülmüş Mesaj: " + şifreçözme);
            }
        } catch (NumberFormatException ex) {
            sonuç.setText("Geçersiz anahtar Lütfen 0-25 arasında bir sayı girin.");
        }
    }

}