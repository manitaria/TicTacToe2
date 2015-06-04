package view.swing;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Основная форма игры
 */
public class MainForm {

    /**
     * Основная панель
     */
    public JPanel fieldPanel;
    //private JLabel gameStateLabel;

    public MainForm(Game game) {
        JLabel gameStateLabel = new JLabel(game.getState().toString(), JLabel.CENTER);
        game.addListener(state -> {gameStateLabel.setText(state.toString());});
        gameStateLabel.setHorizontalTextPosition(JLabel.CENTER);
        GridBagLayout grid = new GridBagLayout();
        fieldPanel.setLayout(grid);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.fill = GridBagConstraints.NONE;
        gridConstraints.gridheight = 1;
        gridConstraints.gridwidth  = GridBagConstraints.REMAINDER;
        gridConstraints.gridx = GridBagConstraints.RELATIVE;
        gridConstraints.gridy = GridBagConstraints.RELATIVE;
        gridConstraints.insets = new Insets(0, 0, 0, 0);
        gridConstraints.ipadx = 0;
        gridConstraints.ipady = 0;
        gridConstraints.weightx = 0.0;
        gridConstraints.weighty = 0.0;
        fieldPanel.add(gameStateLabel, gridConstraints);
        gridConstraints.gridwidth = 1;
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                if (j == game.getSize()-1) {
                    gridConstraints.gridwidth = GridBagConstraints.REMAINDER;   //
                } else {

                    gridConstraints.gridwidth = 1;
                }
                fieldPanel.add(new CellView(i, j, game), gridConstraints);
            }
        }
    }

}
