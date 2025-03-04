import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public MainFrame(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setSize(1200, 700);
        this.getContentPane().setBackground(new Color(12,64,53));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Program biblioteca");
        this.setLocationRelativeTo(null);
        //make window not resizable
        this.setResizable(false);
        //this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.setLayout(null);
        this.add(new TitleLabel());
        this.add(new ListBooksButton(books, personInfos));
        this.add(new ListPeopleButton(personInfos));
        //borrow book button
        this.add(new BorrowBookButton(books, personInfos));
        //add active loans button
        this.add(new ActiveLoansButton(books, personInfos));
        //add return book button
        this.add(new ReturnBookButton(books, personInfos));
        //add statistics button
        this.add(new StatisticsButton(books, personInfos));

        //add background image as a label
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("background.png"));
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, 1200, 700);
            //resize image to fit the window
            Image dimg = backgroundImage.getScaledInstance(1200, 700,
                    Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            backgroundLabel.setIcon(imageIcon);
            this.add(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);

    }
}
