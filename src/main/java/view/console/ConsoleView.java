package view.console;

import model.Cell;
import model.Game;
import model.UserException;
import view.GameView;

import java.util.Scanner;

/**
 * Запуск консольного варианта игры
 */
public class ConsoleView implements GameView {
    public ConsoleView() {
        System.out.println("Игра Крестики-нолики");
        System.out.println("====================");
    }

    public void render(Game game) {
        for (int x = 0; x < game.getSize(); x++) {
            for (int y = 0; y < game.getSize(); y++) {
                System.out.print(game.getField()[x][y] + " ");
            }
            System.out.println();
        }
        // TODO: Реализовать
    }

    @Override
    public void getMove(Game game) throws UserException {
        System.out.println("input movement coordinates (x,y):%n");
        Scanner sc = new Scanner(System.in);
        int x = 0, y = 0;
        try {
            x = sc.nextInt();
            y = sc.nextInt();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (x >= 0 && x <= game.getSize() && y >= 0 && y <= game.getSize()) {
            game.move(x, y);
        } else {
            System.out.println("invalid coordinates %n input movement coordinates (x,y):%n");
        }

        // TODO: Реализовать
    }
}
