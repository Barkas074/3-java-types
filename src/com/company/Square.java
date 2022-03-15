package com.company;

public class Square{
    private boolean isWall;
    private boolean isPassage;
    private boolean isStartWay;
    private boolean isEndWay;
    private boolean isWay;
    private String name;
    private final int x;
    private final int y;

    public boolean IsWall(){
        return isWall;
    }

    public boolean IsPassage(){
        return isPassage;
    }

    public boolean IsStartWay(){
        return isStartWay;
    }

    public boolean IsEndWay(){
        return isEndWay;
    }

    public boolean IsWay(){
        return isWay;
    }
    @Override
    public String toString(){
        return name;
    }

    public int GetX(){
        return x;
    }
    public int GetY(){
        return y;
    }

    public Square(String square, int x, int y){
        SetProperty(square);
        this.x = x;
        this.y = y;
    }

    public void SetProperty(String square){
        switch (square){
            case "#":
                setDefault(square);
                isWall = true;
                break;
            case ".":
                setDefault(square);
                isPassage = true;
                break;
            case "s":
                setDefault(square);
                isStartWay = true;
                break;
            case "f":
                setDefault(square);
                isEndWay = true;
                break;
            case "*":
                setDefault(square);
                isWay = true;
                break;
        }
    }

    private void setDefault(String square) {
        isWall = isPassage = isStartWay = isEndWay = isWay = false;
        name = square;
    }
}
