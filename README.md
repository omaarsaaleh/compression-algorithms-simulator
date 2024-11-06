# Compression Algorithms Simulator
A Java-based simulator for various compression algorithms. The project does not perform real data compression but rather simulates the behavior and logic of these compression techniques providing an educational tool for understanding how compression algorithms operate, without actually compressing the data in a size-reducing manner.


# Supported Algorithms

## LZ77

- Lets you define a **Window Size** and **Look Ahead Buffer Size** on initialization.
- Compresses a `String` to a `List<LZ77Token>` or a `String` representation of the list.
- Decompresses a `List<LZ77Token>` or a `String` representation of the list to a `String`.

### Example

```cpp
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
```

## LZW

- Compresses a `String` to a `List<LZWToken>` or a `String` representation of the list.
- Decompresses a `List<LZWToken>` or a `String` representation of the list to a `String`.

### Example

```cpp
LZW lzw = new LZW();
// Compress
System.out.println(lzw.compress("AABABC"));
System.out.println(lzw.compressToTokens("AABABC").toString());
// Output: [<65>, <65>, <66>, <129>, <67>]
// Decompress
System.out.println(lzw.decompress("[<65>, <65>, <66>, <129>, <67>]"));
List<LZWToken> tokens1 = LZWToken.asList(65, 65, 66, 129, 67);
System.out.println(lzw.decompress(tokens1));
// Output: AABABC
```
