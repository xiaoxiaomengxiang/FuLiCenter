package com.example.winston.myapplication.bean;

import android.support.test.espresso.core.deps.guava.primitives.UnsignedLongs;

/**
 * Created by Winston on 2016/10/13.
 */

public class Result {

    /**
     * muserName : a952702
     * muserNick : 彭鹏
     * mavatarId : 74
     * mavatarPath : user_avatar
     * mavatarSuffix : .jpg
     * mavatarType : 0
     * mavatarLastUpdateTime : 1476285149669
     */

    private String muserName;
    private String muserNick;
    private int mavatarId;
    private String mavatarPath;
    private String mavatarSuffix;
    private int mavatarType;
    private String mavatarLastUpdateTime;
    private UnsignedLongs retData;

    public Result(UnsignedLongs retData) {
        this.retData = retData;
    }

    public String getMuserName() {
        return muserName;
    }

    public void setMuserName(String muserName) {
        this.muserName = muserName;
    }

    public String getMuserNick() {
        return muserNick;
    }

    public void setMuserNick(String muserNick) {
        this.muserNick = muserNick;
    }

    public int getMavatarId() {
        return mavatarId;
    }

    public void setMavatarId(int mavatarId) {
        this.mavatarId = mavatarId;
    }

    public String getMavatarPath() {
        return mavatarPath;
    }

    public void setMavatarPath(String mavatarPath) {
        this.mavatarPath = mavatarPath;
    }

    public String getMavatarSuffix() {
        return mavatarSuffix;
    }

    public void setMavatarSuffix(String mavatarSuffix) {
        this.mavatarSuffix = mavatarSuffix;
    }

    public int getMavatarType() {
        return mavatarType;
    }

    public void setMavatarType(int mavatarType) {
        this.mavatarType = mavatarType;
    }

    public String getMavatarLastUpdateTime() {
        return mavatarLastUpdateTime;
    }

    public void setMavatarLastUpdateTime(String mavatarLastUpdateTime) {
        this.mavatarLastUpdateTime = mavatarLastUpdateTime;
    }

    public int getRetCode() {
        return mavatarId;
    }

    public UnsignedLongs getRetData() {
        return retData;
    }

    public void setRetData(UnsignedLongs retData) {
        this.retData = retData;
    }
}
