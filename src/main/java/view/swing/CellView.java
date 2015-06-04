package view.swing;

import model.Game;
import model.UserException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kirill on 02.06.2015.
 */
public class CellView extends JButton {

    int x, y;

    public CellView(int x, int y, Game game) {
        super(game.getField()[x][y].toString());
        this.x = x;
        this.y = y;
        addActionListener(actionEvent -> {
            try {
                game.move(x, y);
            } catch (UserException e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            }
            setText(game.getField()[x][y].toString());
            setEnabled(false);
        });
    }

}
