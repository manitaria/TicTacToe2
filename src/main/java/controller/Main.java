package controller;

import model.Game;
import model.UserException;
import view.GameView;
import view.console.ConsoleView;
import view.swing.SwingView;

/**
 * Контроллер
 */
public class Main {


    public static void main(String[] args) throws UserException {
        Game game = new Game();
        GameView gameView = new ConsoleView();
        GameView gui = new SwingView(game);
        while (true){
            gameView.render(game);
            gameView.getMove(game);
        }
    }
}
