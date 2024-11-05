package org.compression.algorithms;

public interface CompressionAlgorithm {

    String compress(String input) ;
    String decompress(String input) throws IllegalArgumentException;

}
