package minesweeper.field;

class Cell {
    private Field.cellObject cellObject;
    private Field.mask mask;

    Cell (){
        cellObject = Field.cellObject.EMPTY;
        mask = Field.mask.CLOSED;
    }

    Field.cellObject getCellObject(){
    return cellObject;
    }

    Field.mask getMask(){
        return mask;
    }

    void setCellObject(Field.cellObject cellObject){
        this.cellObject = cellObject;
    }

    void setMask (Field.mask mask){
        this.mask = mask;
    }
}