package org.compression.algorithms.lz77;

import java.util.ArrayList;
import java.util.List;

public class LZ77Token {
    private final int OFFSET;
    private final int LENGTH;
    private final char LITERAL;

    public LZ77Token(int offset, int length, char literal) {
        this.OFFSET = offset;
        this.LENGTH = length;
        this.LITERAL = literal;
    }

    public LZ77Token(int offset, int length) {
        this.OFFSET = offset;
        this.LENGTH = length;
        this.LITERAL = '\0';
    }

    public LZ77Token(char literal) {
        this.OFFSET = 0;
        this.LENGTH = 0;
        this.LITERAL = literal;
    }

    public int getOffset() {
        return OFFSET;
    }

    public int getLength() {
        return LENGTH;
    }

    public char getLiteral() {
        return LITERAL;
    }

    @Override
    public String toString() {
        return String.format("<%d,%d,%c>", OFFSET, LENGTH, LITERAL);

    }

    public static List<LZ77Token> parseTokens(String input) throws IllegalArgumentException {
        List<LZ77Token> tokens = new ArrayList<>();
        String[] tokenStrings ;
        try {
            tokenStrings = input.substring(2, input.length() - 2).split(">, <");
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Wrong Input Format");
        }
        for (String tokenStr : tokenStrings) {
            try {
                String[] parts = tokenStr.split(",");

                int offset = Integer.parseInt(parts[0]);
                int length = Integer.parseInt(parts[1]);
                char literal = parts[2].charAt(0);
                tokens.add(new LZ77Token(offset, length, literal));
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Wrong Input Format");
            }

        }

        return tokens;
    }

}
