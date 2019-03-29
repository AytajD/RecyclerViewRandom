package com.dadashova.aytaj.recyclerviewrandom.Containers;

public class RecyclerModel {

    private int mImgId;
    private String mName;
    private boolean isRight;

    public int getmImgId() {
        return mImgId;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setmImgId(int mImgId) {
        this.mImgId = mImgId;
    }

    public String getmName() {
        return mName;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
