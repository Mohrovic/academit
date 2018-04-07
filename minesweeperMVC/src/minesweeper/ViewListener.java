package minesweeper;

public interface ViewListener {

    void needOpenCell(int x, int y);

    void needSetFlag(int x, int y);

    void needOpenAdjacent(int x, int y);

    void needStartNewLevel(int level);

    void needStartNewLevel(int dimension, int minesTotal);
}