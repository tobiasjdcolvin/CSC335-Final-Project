package model;

import java.util.Arrays;

public class Word {
    private boolean DEBUG = true;
    private String ogString;
    private char[] chars;

    public Word(String word) {
        this.ogString = word;
        char[] t = new char[5];
        System.arraycopy(word.toCharArray(), 0, t, 0, 5);
        this.chars = t;
    }

    public char[] getChars() {
        char[] ret = new char[5];
        System.arraycopy(this.chars, 0, ret, 0, 5);
        return ret;
    }

    @Override
    public String toString() {
        return this.ogString;
    }
}