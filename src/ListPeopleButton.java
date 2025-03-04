import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListPeopleButton extends JButton {
    public ListPeopleButton(ArrayList<PersonInfo> personInfos) {
        this.setText("Listare persoane");
        this.setBounds(300, 325, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new ListPeopleFrame(personInfos);
        });
    }
}
