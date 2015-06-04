package view;

import model.Game;
import model.UserException;

/**
 * Интерфейс игры
 */
public interface GameView {
    void render(Game game);
    void getMove (Game game) throws UserException;
}
