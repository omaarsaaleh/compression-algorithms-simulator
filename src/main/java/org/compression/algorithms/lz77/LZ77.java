package org.compression.algorithms.lz77;

import org.compression.algorithms.CompressionAlgorithm;
import java.util.ArrayList;
import java.util.List;


public class LZ77 implements CompressionAlgorithm{

    private final int WINDOW_SIZE ;
    private final int LOOK_AHEAD_BUFFER_SIZE ;

    public LZ77(int window_size, int look_ahead_buffer_size){
        this.WINDOW_SIZE = window_size ;
        this.LOOK_AHEAD_BUFFER_SIZE = look_ahead_buffer_size;
    }


    public List<LZ77Token> compressToTokens(String input){
        List<LZ77Token> result = new ArrayList<>();
        int cursor = 0;

        while (cursor < input.length()) {
            int matchLength = 0, matchOffset = 0;
            int lookAheadSize = Math.min(LOOK_AHEAD_BUFFER_SIZE, input.length() - cursor);
            int windowStart = Math.max(0, cursor - WINDOW_SIZE),  windowEnd = cursor - 1;

            for (int i = windowStart; i <= windowEnd; i++) {
                int curMatchLength = 0;
                while (curMatchLength < lookAheadSize &&  i + curMatchLength < cursor &&  input.charAt(i + curMatchLength) == input.charAt(cursor + curMatchLength) ) {
                    curMatchLength++;
                }
                if (curMatchLength>0 && curMatchLength >= matchLength  ) {
                    matchLength = curMatchLength;
                    matchOffset = cursor - i;
                }
            }

            char nextChar = (cursor + matchLength < input.length()) ? input.charAt(cursor + matchLength) : '\0';
            result.add(new LZ77Token(matchOffset, matchLength, nextChar));
            cursor +=  matchLength+1 ;
        }
        return result ;

    }

    @Override
    public String compress(String input)  {
        return compressToTokens(input).toString();
    }


    public String decompress(List<LZ77Token> tokens){

        StringBuilder result = new StringBuilder();

        for (LZ77Token token : tokens) {
            int start = result.length() - token.getOffset();

            for (int i = 0; i < token.getLength(); i++) {
                result.append(result.charAt(start + i));
            }

            if (token.getLiteral() != '\0') {
                result.append(token.getLiteral());
            }
        }
        return result.toString();

    }

    @Override
    public String decompress(String input) throws IllegalArgumentException {
        return decompress(LZ77Token.parseTokens(input));
    }


}
