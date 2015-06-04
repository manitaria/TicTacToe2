package view.swing;

import model.Game;
import view.GameView;

import javax.swing.*;

/**
 * Отображение игры в Swing интерфейсе
 */
public class SwingView implements GameView {
    public SwingView(Game game) {
        JFrame frame = new JFrame("Игра Крестики-нолики");
        MainForm mainForm = new MainForm(game);
        frame.setContentPane(mainForm.fieldPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void render(Game game) {

    }

    @Override
    public void getMove(Game game) {

    }
}
