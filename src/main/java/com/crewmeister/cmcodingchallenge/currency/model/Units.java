package com.crewmeister.cmcodingchallenge.currency.model;

public enum Units {
    AUD("BBEX3.D.AUD.EUR.BB.AC.000"),
    BGN("BBEX3.D.BGN.EUR.BB.AC.000"),
    BRL("BBEX3.D.BRL.EUR.BB.AC.000"),
    CAD("BBEX3.D.CAD.EUR.BB.AC.000"),
    CHF("BBEX3.D.CHF.EUR.BB.AC.000"),
    CNY("BBEX3.D.CNY.EUR.BB.AC.000"),
    CYP("BBEX3.D.CYP.EUR.BB.AC.000"),
    JPY("BBEX3.D.JPY.EUR.BB.AC.000"),
    CZK("BBEX3.D.CZK.EUR.BB.AC.000"),
    DKK("BBEX3.D.DKK.EUR.BB.AC.000"),
    EEK("BBEX3.D.EEK.EUR.BB.AC.000"),
    GBP("BBEX3.D.GBP.EUR.BB.AC.000"),
    GRD("BBEX3.D.GRD.EUR.BB.AC.000"),
    HKD("BBEX3.D.HKD.EUR.BB.AC.000"),
    HRK("BBEX3.D.HRK.EUR.BB.AC.000"),
    HUF("BBEX3.D.HUF.EUR.BB.AC.000"),
    IDR("BBEX3.D.IDR.EUR.BB.AC.000"),
    ILS("BBEX3.D.ILS.EUR.BB.AC.000"),
    INR("BBEX3.D.INR.EUR.BB.AC.000"),
    ISK("BBEX3.D.ISK.EUR.BB.AC.000"),
    KRW("BBEX3.D.KRW.EUR.BB.AC.000"),
    LTL("BBEX3.D.LTL.EUR.BB.AC.000"),
    LVL("BBEX3.D.LVL.EUR.BB.AC.000"),
    MTL("BBEX3.D.MTL.EUR.BB.AC.000"),
    MXN("BBEX3.D.MXN.EUR.BB.AC.000"),
    MYR("BBEX3.D.MYR.EUR.BB.AC.000"),
    NOK("BBEX3.D.NOK.EUR.BB.AC.000"),
    NZD("BBEX3.D.NZD.EUR.BB.AC.000"),
    PHP("BBEX3.D.PHP.EUR.BB.AC.000"),
    PLN("BBEX3.D.PLN.EUR.BB.AC.000"),
    ROL("BBEX3.D.ROL.EUR.BB.AC.000"),
    RON("BBEX3.D.RON.EUR.BB.AC.000"),
    RUB("BBEX3.D.RUB.EUR.BB.AC.000"),
    SEK("BBEX3.D.SEK.EUR.BB.AC.000"),
    SGD("BBEX3.D.SGD.EUR.BB.AC.000"),
    SIT("BBEX3.D.SIT.EUR.BB.AC.000"),
    SKK("BBEX3.D.SKK.EUR.BB.AC.000"),

    TRL("BBEX3.D.TRL.EUR.BB.AC.000"),
    TRY("BBEX3.D.TRY.EUR.BB.AC.000"),
    USD("BBEX3.D.USD.EUR.BB.AC.000"),
    ZAR("BBEX3.D.ZAR.EUR.BB.AC.000");

    private final String unitType;

    Units(final String unitType) {
        this.unitType = unitType;
    }

    public String getTimeAsString() {
        return this.unitType;
    }
}