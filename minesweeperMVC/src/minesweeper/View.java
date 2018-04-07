package minesweeper;

import minesweeper.field.Field;

public interface View extends AutoCloseable {

    void startApplication();

    void updateField(Field field);

    void addViewListener(ViewListener listener);

    void removeViewListener(ViewListener listener);
}
