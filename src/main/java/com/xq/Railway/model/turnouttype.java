package com.xq.Railway.model;

public class turnouttype {
    private String id;

    private String turnouttype;

    private String reamke;

    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTurnouttype() {
        return turnouttype;
    }

    public void setTurnouttype(String turnouttype) {
        this.turnouttype = turnouttype == null ? null : turnouttype.trim();
    }

    public String getReamke() {
        return reamke;
    }

    public void setReamke(String reamke) {
        this.reamke = reamke == null ? null : reamke.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }
}