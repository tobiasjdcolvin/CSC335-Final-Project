package model;

public class Word {
    private boolean DEBUG = true;

    private char[] chars;

    public Word(String word) {
        char[] t = new char[5];
        System.arraycopy(word.toCharArray(), 0, t, 0, 5);
        this.chars = t;
    }

    public char[] getChars() {
        char[] ret = new char[5];
        System.arraycopy(this.chars, 0, ret, 0, 5);
        return ret;
    }
}