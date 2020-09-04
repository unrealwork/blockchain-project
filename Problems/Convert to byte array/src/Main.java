import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        File sampleFile = new File("sample.txt");
        // implement the method
        try (CharArrayWriter charArrayWriter = new CharArrayWriter()) {
            for (String word : words) {
                charArrayWriter.append(word);
            }
            return charArrayWriter.toCharArray();
        }
    }
}
