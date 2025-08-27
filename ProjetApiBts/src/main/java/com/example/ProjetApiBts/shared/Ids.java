package com.example.ProjetApiBts.shared;

public final class Ids {
    private Ids() {}
    public static boolean same(Number a, Number b) {
        return a != null && b != null && a.longValue() == b.longValue();
    }
}
