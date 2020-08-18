package onlineassessment;

import java.util.HashMap;
import java.util.Map;

public class FunWithVowels {

    static Map<Character, Integer> orderStringMap = new HashMap<>();

    static Map<String, Integer> pastRun = new HashMap<>();

    static void setOrderStringMap() {
        orderStringMap.put('a', 0);
        orderStringMap.put('e', 1);
        orderStringMap.put('i', 2);
        orderStringMap.put('o', 3);
        orderStringMap.put('u', 4);
    }

    /*
     * Complete the 'longestVowelSubsequence' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int longestVowelSubsequence(String s) {
        setOrderStringMap();

        return dp(s, 0, 0, new StringBuilder());
    }

    private static int dp(String s, int iS, int iOS, StringBuilder sb) {
        // System.out.println(iS + " " + c + " " + iOS + " " + sb.toString());
        String formedString = sb.toString();
        if (!pastRun.containsKey(formedString)) {
            if (iS >= s.length()) {
                if (iOS < 4)
                    pastRun.put(formedString, 0);
                else pastRun.put(formedString, sb.length());
            } else {
                /* optimize idea:
                    if current letter is out of order -> know for sure don't take it
                    if current letter is same with last letter -> know for sure take it
                    otherwise try
                */
                char c = s.charAt(iS);

                while (orderStringMap.getOrDefault(c, -1) != iOS+1 && iS < s.length()) {
                    if (orderStringMap.getOrDefault(c, -1) == iOS)
                        sb.append(c);
                    iS++;
                    if (iS < s.length())
                        c = s.charAt(iS);
                }

                // see letter higher in order or end of string
                int maxLength = 0;
                if (iS < s.length()) {
                    // actually see letter higher in order
                    sb.append(c);
                    maxLength = Math.max(maxLength, dp(s, iS+1, orderStringMap.get(c), sb));
                    sb.deleteCharAt(sb.length() - 1);
                }
                maxLength = Math.max(maxLength, dp(s, iS+1, iOS, sb));
                pastRun.put(formedString, maxLength);
            }
        }
        sb.setLength(formedString.length());

        return pastRun.get(formedString);
    }

    public static void main(String[] args) {
        String testString = "aeiaaioooaauuaeiu";
        String testString1 = "aeiaaiooaa";
        String testString2 = "uioieeeaouiiuaeeuuiuuauuauaeaeuauaeaaiuoiouaeuiuuoooaeeaioeieoeooaeuooae";
        String testString3 = "eouiuaaeiaoauaoaeauieeoiuiuiu";

        setOrderStringMap();

        String hackerEarth1 = "aaaeeeeegjbodnodfnboidnbooofksdvipsfvifbondfbonfklkvssifuu";
        StringBuilder hackerEarthFilteredSb = new StringBuilder();
        for (int i=0; i<hackerEarth1.length(); i++)
            if (orderStringMap.containsKey(hackerEarth1.charAt(i)))
                hackerEarthFilteredSb.append(hackerEarth1.charAt(i));
        String hackerEarthFiltered = hackerEarthFilteredSb.toString();
        System.out.println(hackerEarthFiltered);
        System.out.println(FunWithVowels.longestVowelSubsequence(hackerEarthFiltered));
    }
}