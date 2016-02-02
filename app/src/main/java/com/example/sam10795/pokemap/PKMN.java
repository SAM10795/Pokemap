package com.example.sam10795.pokemap;

import java.util.Date;

/**
 * Created by SAM10795 on 17-09-2015.
 */
public class PKMN {
    private String name;
    private String nick;
    private String ID;
    private int[] bmp;
    private int lv;
    private int id;
    private Date cdt;
    private boolean shiny;

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setBmp(int[] bmp) {
        this.bmp = bmp;
    }

    public void setCdt(Date cdt) {
        this.cdt = cdt;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public Date getCdt() {
        return cdt;
    }

    public String getNick() {
        return nick;
    }

    public int getId() {
        return id;
    }

    public int getLv() {
        return lv;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int[] getBmp() {
        return bmp;
    }

    public boolean isShiny() {
        return shiny;
    }
}
