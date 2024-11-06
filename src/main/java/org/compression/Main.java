package org.compression;

import org.compression.algorithms.lz77.LZ77;
import org.compression.algorithms.lz77.LZ77Token;
import org.compression.algorithms.lzw.LZW;
import org.compression.algorithms.lzw.LZWToken;

import java.util.List;

public class Main {
    public static void main(String[] args)  {

        LZ77 lz77 = new LZ77(12,11) ;
        // Compress
        System.out.println(lz77.compress("AABABC"));
        System.out.println(lz77.compressToTokens("AABABC").toString());
        // Output: [<0,0,A>, <1,1,B>, <2,2,C>]
        // Decompress
        System.out.println(lz77.decompress("[<0,0,A>, <1,1,B>, <2,2,C>]"));
        List<LZ77Token> tokens = List.of(
                new LZ77Token('A'),
                new LZ77Token(1, 1, 'B'),
                new LZ77Token(2, 2, 'C')
        );
        System.out.println(lz77.decompress(tokens));
        // Output: AABABC


        LZW lzw = new LZW();
        // Compress
        System.out.println(lzw.compress("AABABC"));
        System.out.println(lzw.compressToTokens("AABABC").toString());
        // Output: [<65>, <65>, <66>, <129>, <67>]
        // Decompress
        System.out.println(lzw.decompress("[<65>, <65>, <66>, <129>, <67>]"));
        List<LZWToken> tokens1 = LZWToken.asList(65,65,66,129,67);
        System.out.println(lzw.decompress(tokens1));
        // Output: AABABC

    }
}