package org.compression.algorithms.lzw;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LZWToken {
    private final int CODE;

    public LZWToken(int code){
        this.CODE = code ;
    }

    int getCode(){
        return this.CODE ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LZWToken lzwToken = (LZWToken) obj;
        return CODE == lzwToken.CODE;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(CODE);
    }

    @Override
    public String toString() {
        return String.format("<%d>", CODE);
    }

    public static List<LZWToken> asList(Integer... codes) {
        return Arrays.stream(codes)
                .map(LZWToken::new)
                .collect(Collectors.toList());
    }

    public static List<Integer> toListOfIntegers(List<LZWToken> tokens) {
        return tokens.stream()
                .map(LZWToken::getCode)
                .collect(Collectors.toList());
    }

    public static List<LZWToken> parseTokens(String input) throws IllegalArgumentException{
        List<LZWToken> tokens = new ArrayList<>();
        String[] tokenStrings ;
        try {
            tokenStrings = input.substring(2, input.length() - 2).split(">, <");
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Wrong Input Format");
        }
        for (String tokenStr : tokenStrings) {
            try {
                int code = Integer.parseInt(tokenStr);
                tokens.add(new LZWToken(code));
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Wrong Input Format");
            }

        }
        return tokens;

    }
}
