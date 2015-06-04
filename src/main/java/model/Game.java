package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Состояние игры
 */
public class Game {


    /**
     * Размер поля
     */
    final int SIZE;
    final int WIN_NUMBER;
    /**
     * Поле игры
     * Координаты отсчитываем от
     * верхнего левого угла
     */
    final Cell[][] field;
    /**
     * Состояние игры
     */
    State state = State.X_MOVE;

    public final List<GameUpdateListener> Listeners = new ArrayList<>();

    public void addListener(GameUpdateListener listener){
        Listeners.add(listener);
    }

    public Game(int size) {
        WIN_NUMBER = 5;                 //temp
        this.SIZE = size;
        field = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = Cell.EMPTY;
            }
        }
    }

    public Game() {
        this(20);
    }

    public int getSize() {
        return SIZE;
    }

    public Cell[][] getField() {
        return field;
    }

    public State getState() {
        return state;
    }

    /**
     * Ход
     *
     * @param x координата по горизонтали (столбец)
     * @param y координата по вертикали (строка)
     */
    public void move(int x, int y) throws UserException {
        if (x < 0 || x >= SIZE)
            throw new UserException("x за пределами поля");
        if (y < 0 || y >= SIZE)
            throw new UserException("y за пределами поля");
        if (field[x][y] != Cell.EMPTY)
            throw new UserException("Ячейка занята x = " + x + " y = " + y);
        switch (state) {
            case X_MOVE:
                field[x][y] = Cell.X;
                state = State.O_MOVE;
                notifyListeners();
                break;
            case O_MOVE:
                field[x][y] = Cell.O;
                state = State.X_MOVE;
                notifyListeners();
                break;
            default:
                throw new UserException("Ход невозможен!");
        }
        checkEnd();
        switch (state) {
            case X_WINS:
                notifyListeners();
                System.out.println("x wins"); //should be listener
                break;
            case O_WINS:
                notifyListeners();
                System.out.println("o wins"); //should be listener
                break;
            default:
                //throw new UserException("Ход невозможен!");
        }
    }

    /**
     * Checking the end of the game (who wins or is it a draw)
     * <p>
     * here the whole game field are being checked
     * to provide scalability for future infinite field
     */
    private void checkEnd() {
        int row_max_x_count;
        int row_max_o_count;
        int column_max_x_count;
        int column_max_o_count;
        int lb_to_rt_max_x_count;   //left bottom to right top
        int lb_to_rt_max_o_count;   //left bottom to right top
        int rb_to_lt_max_x_count;   //right bottom to left top
        int rb_to_lt_max_o_count;   //right bottom to left top
        int diag_elem_number = 1;
        int diag_number_count = 0;
        int diag_search_row;
        int diag_search_col;
        int diag_search_start_row = SIZE - 1;
        int diag_search_start_col = 0;
        //left bottom to right top diagonal
        while (diag_number_count < SIZE * 2 - 1) {
            lb_to_rt_max_x_count = 0;
            lb_to_rt_max_o_count = 0;
            diag_search_row = diag_search_start_row;
            diag_search_col = diag_search_start_col;
            for (int i = 0; i < diag_elem_number; i++) {
                if (field[diag_search_row][diag_search_col] == Cell.X) {
                    lb_to_rt_max_x_count++;
                    lb_to_rt_max_o_count = 0;
                }
                if (field[diag_search_row][diag_search_col] == Cell.O) {
                    lb_to_rt_max_o_count++;
                    lb_to_rt_max_x_count = 0;
                }
                diag_search_row--;
                diag_search_col--;
            }
            if (lb_to_rt_max_x_count == WIN_NUMBER) {
                state = State.X_WINS;
                return;
            } else if (lb_to_rt_max_o_count == WIN_NUMBER) {
                state = State.O_WINS;
                return;
            }
            if (diag_number_count < SIZE - 1) {
                diag_search_start_col++;
                diag_elem_number++;
            } else {
                diag_search_start_row--;
                diag_elem_number--;
            }
            diag_number_count++;
        }
        //right bottom to left top diagonal
        diag_number_count = 0;
        diag_elem_number = 1;
        diag_number_count = 0;
        diag_search_start_row = SIZE - 1;
        diag_search_start_col = SIZE - 1;
        while (diag_number_count < SIZE * 2 - 1) {
            rb_to_lt_max_x_count = 0;
            rb_to_lt_max_o_count = 0;
            diag_search_row = diag_search_start_row;
            diag_search_col = diag_search_start_col;
            for (int i = 0; i < diag_elem_number; i++) {
                if (field[diag_search_row][diag_search_col] == Cell.X) {
                    rb_to_lt_max_x_count++;
                    rb_to_lt_max_o_count = 0;
                }
                if (field[diag_search_row][diag_search_col] == Cell.O) {
                    rb_to_lt_max_o_count++;
                    rb_to_lt_max_x_count = 0;
                }
                diag_search_row--;
                diag_search_col++;
            }
            if (rb_to_lt_max_x_count == WIN_NUMBER) {
                state = State.X_WINS;
                return;
            } else if (rb_to_lt_max_o_count == WIN_NUMBER) {
                state = State.O_WINS;
                return;
            }
            if (diag_number_count < SIZE - 1) {
                diag_search_start_col--;
                diag_elem_number++;
            } else {
                diag_search_start_row--;
                diag_elem_number--;
            }
            diag_number_count++;
        }
        //rows and columns
        for (int i = 0; i < SIZE; i++) {
            row_max_x_count = 0;
            row_max_o_count = 0;
            column_max_x_count = 0;
            column_max_o_count = 0;
            for (int j = 0; j < SIZE; j++) {

                //rows
                if (field[i][j] == Cell.X) {
                    row_max_x_count++;
                    row_max_o_count = 0;
                }
                if (field[i][j] == Cell.O) {
                    row_max_o_count++;
                    row_max_x_count = 0;
                }
                if (row_max_x_count == WIN_NUMBER) {
                    state = State.X_WINS;
                    return;
                } else if (row_max_o_count == WIN_NUMBER) {
                    state = State.O_WINS;
                    return;
                }

                //columns
                if (field[j][i] == Cell.X) {
                    column_max_x_count++;
                    column_max_o_count = 0;
                }
                if (field[j][i] == Cell.O) {
                    column_max_o_count++;
                    column_max_x_count = 0;
                }
                if (column_max_x_count == WIN_NUMBER) {
                    state = State.X_WINS;
                    return;
                } else if (column_max_o_count == WIN_NUMBER) {
                    state = State.O_WINS;
                    return;
                }
            }
        }
        return; //nobody wins
    }

    public enum State {
        X_MOVE("Ход крестиков"),
        O_MOVE("Ход ноликов"),
        X_WINS("Крестики выиграли"),
        O_WINS("Нолики выиграли"),
        DRAW("Ничья");

        public final String name;

        State(String name) {
            this.name = name;
        }
    }


    private void notifyListeners() {
        for (GameUpdateListener listener : Listeners)
            listener.update(state);
    }
}
