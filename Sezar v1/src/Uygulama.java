import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Uygulama extends JFrame implements ActionListener {
    JButton sifreleDugmesi, cozumDugmesi;
    JTextField anahtarAlani, mesajAlani;
    JTextArea sonucAlani;

    public Uygulama() {
        setTitle("Şifreleme Uygulaması");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(Color.GREEN);

        JLabel anahtarEtiketi = new JLabel("Anahtar 0-25 Arasında:");
        anahtarEtiketi.setBounds(50, 30, 100, 30);
        add(anahtarEtiketi);


        anahtarAlani = new JTextField();
        anahtarAlani.setBounds(150, 30, 100, 30);
        add(anahtarAlani);
        anahtarAlani.setBackground(Color.RED);

        JLabel mesajEtiketi = new JLabel("Mesaj:");
        mesajEtiketi.setBounds(50, 80, 100, 30);
        add(mesajEtiketi);

        mesajAlani = new JTextField();
        mesajAlani.setBounds(150, 80, 300, 30);
        add(mesajAlani);

        sifreleDugmesi = new JButton("Şifrele");
        sifreleDugmesi.setBounds(100, 150, 100, 40);
        sifreleDugmesi.addActionListener(this);
        add(sifreleDugmesi);

        cozumDugmesi = new JButton("Şifre Çöz");
        cozumDugmesi.setBounds(250, 150, 100, 40);
        cozumDugmesi.addActionListener(this);
        add(cozumDugmesi);

        sonucAlani = new JTextArea();
        sonucAlani.setBounds(50, 220, 400, 100);
        sonucAlani.setEditable(false);
        add(sonucAlani);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int anahtar = Integer.parseInt(anahtarAlani.getText());
            String mesaj = mesajAlani.getText();

            if (e.getSource() == sifreleDugmesi) {
                String sifrelenmisMesaj = sifrele(mesaj, anahtar);
                sonucAlani.setText("Şifrelenmiş Mesaj: " + sifrelenmisMesaj);
            } else if (e.getSource() == cozumDugmesi) {
                String cozulmusMesaj = coz(mesaj, anahtar);
                sonucAlani.setText("Çözülmüş Mesaj: " + cozulmusMesaj);
            }
        } catch (NumberFormatException ex) {
            sonucAlani.setText("Geçersiz anahtar! Lütfen 0-25 arasında bir sayı girin.");
        }
    }

    public static String sifrele(String metin, int anahtar) {
        StringBuilder sonuc = new StringBuilder();

        for (char karakter : metin.toCharArray()) {
            if (Character.isLetter(karakter)) {
                char temel = Character.isLowerCase(karakter) ? 'a' : 'A';
                karakter = (char) (temel + (karakter - temel + anahtar) % 26);
            }
            sonuc.append(karakter);
        }

        return sonuc.toString();
    }

    public static String coz(String metin, int anahtar) {
        return sifrele(metin, 26 - anahtar);
    }

    public static void main(String[] args) {
        new Anasayfa();
    }
}
