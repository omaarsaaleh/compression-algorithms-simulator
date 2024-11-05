package org.compression.algorithms.lzw;

import org.compression.algorithms.CompressionAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW implements CompressionAlgorithm {

    public List<LZWToken> compressToTokens(String input){
        if(input.isEmpty()) return new ArrayList<>();
        Map<String, LZWToken> dict = new HashMap<>();
        for (int i = 0; i < 128; i++) dict.put( "" + (char)i, new LZWToken(i));

        List<LZWToken> tags = new ArrayList<>();
        int dict_idx = 128 ;

        String last_exist = "";
        for (char c : input.toCharArray()) {
            String cur = last_exist + c;
            if (dict.containsKey(cur)) {
                last_exist = cur ;
            }
            else {
                tags.add(dict.get(last_exist));
                dict.put(cur, new LZWToken(dict_idx++) );
                last_exist = "" + c;
            }
        }
        tags.add(dict.get(last_exist));

        return tags ;
    }

    @Override
    public String compress(String input){
        return compressToTokens(input).toString();
    }

    public String decompress(List<LZWToken> tags){
        Map<LZWToken, String> dict = new HashMap<>();
        for (int i = 0; i < 128; i++) dict.put(new LZWToken(i), "" + (char)i);
        int dict_idx = 128;

        StringBuilder decompressed = new StringBuilder();
        decompressed.append(dict.get(tags.getFirst()));

        for(int i=1 ; i<tags.size(); i++){

            String prev =  dict.get(tags.get(i-1)) ;
            String cur = (dict.containsKey(tags.get(i)))?  dict.get(tags.get(i)) : prev + prev.charAt(0) ;

            decompressed.append(cur);
            dict.put(
                    new LZWToken(dict_idx++),
                    prev + cur.charAt(0)
            );
        }
        return decompressed.toString();
    }

    @Override
    public String decompress(String input) throws IllegalArgumentException {
        return this.decompress(LZWToken.parseTokens(input));
    }
}
