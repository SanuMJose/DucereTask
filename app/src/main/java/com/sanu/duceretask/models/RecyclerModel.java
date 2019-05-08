package com.sanu.duceretask.models;

public class RecyclerModel {
  public  String number;
  public  int count;

    public RecyclerModel(String number, int count, int colourcount) {
        this.number = number;
        this.count = count;
        this.colourcount = colourcount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getColourcount() {
        return colourcount;
    }

    public void setColourcount(int colourcount) {
        this.colourcount = colourcount;
    }

   public int colourcount;
}
