import java.util.HashMap;
import java.util.Map;

public class Codec {
    Map<String, String> shortToLong;
    Map<String, String> longToShort;
    String host = "http://tinyurl.com/";
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int counter;

    public Codec() {
        shortToLong = new HashMap<>();
        longToShort = new HashMap<>();
        counter = 0;
    }

    // Encodes a long URL to a tiny URL.
    public String encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }

        String shortKey = toBase62(counter);
        String tinyUrl = host + shortKey;
        
        longToShort.put(longUrl, tinyUrl);
        shortToLong.put(tinyUrl, longUrl);
        counter++;

        return tinyUrl;
    }

    // Decodes a tiny URL to a long URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
    
    private String toBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(alphabet.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() < 6) { // Ensure fixed length for a nicer look
            sb.append(alphabet.charAt(0));
        }
        return sb.reverse().toString();
    }
}
