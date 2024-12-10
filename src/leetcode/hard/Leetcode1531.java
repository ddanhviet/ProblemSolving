package leetcode.hard;

import java.util.*;

public class Leetcode1531 {

    protected class StringNode {
        char c;
        int count;
        int length;

        StringNode(char c, int count) {
            this.c = c;
            this.count = count;
            if (count == 1)
                this.length = 1;
            else this.length = (int) (Math.log10(count) + 1) + 1;
        }

        void setCount(int count) {
            this.count = count;
            if (count == 1)
                this.length = 1;
            else this.length = (int) (Math.log10(count) + 1) + 1;
        }
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        List<StringNode> sCompressed = compressString(s);
        // for (StringNode sn: sCompressed)
        //     System.out.println(sn.c + " " + sn.count);
        // System.out.println("Finding optimal");
        return optimalAdd(sCompressed, 0, k, new ArrayList<>());
    }

    private List<StringNode> compressString(String s) {
        List<StringNode> sCompressed = new ArrayList<>();
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            count++;
            if (i+1 == s.length() || s.charAt(i+1) != s.charAt(i)) {
                sCompressed.add(new StringNode(s.charAt(i), count));
                count = 0;
            }
        }
        return sCompressed;
    }

    /*
        12 -> 9
        130 -> 99
        1 - 9 -> 0
    */
    // can save run time by memoize pos and k
    // return minLength from delete k character
    private int optimalAdd(List<StringNode> sCompressed, int scPos, int k, List<StringNode> snBuilder) {
        System.out.println(scPos + " " + k);
        if (scPos == sCompressed.size())
            return 0;

        int minLengthAdded = 100;

        int snbPos = snBuilder.size() - 1;

        StringNode sn = sCompressed.get(scPos);
        StringNode lastAddedSn = snbPos >= 0 ? snBuilder.get(snbPos) : null;

        int totalCount = sn.count;
        System.out.println("inittialTotalCount: " + totalCount);

        // if this char = previous char
        // aaaabaac -> aaaa + aa
        if (lastAddedSn != null && sn.c == lastAddedSn.c) {
            System.out.println("Last Added SN: " + lastAddedSn.c);
            totalCount += lastAddedSn.count;

        }
        System.out.println("totalCount: " + totalCount);

        List<Integer> addCharTries = new ArrayList<>();
        addCharTries.add(0);
        addCharTries.add(totalCount);
        if (totalCount > 9)
            addCharTries.add(9);
        if (totalCount > 99)
            addCharTries.add(99);

        for (int charToAdd : addCharTries) {
            int delCount = totalCount - charToAdd;

            System.out.print("scPos " + scPos + " sn.c: " + sn.c + " charToAdd: " + charToAdd);
            System.out.println(" delCount: " + delCount);
            // System.out.println(" k-delCount: " + (k-delCount));

            // check if allow to add that little
            if (delCount > k) {
                System.out.println(" " + charToAdd == totalCount + " all ");
                continue;
            }

            // if (k == 0) {
            //     System.out.print(" " + charToAdd == totalCount);
            // }

            int lengthAdded = 0;
            if (charToAdd != 0) {
                if (lastAddedSn != null && sn.c == lastAddedSn.c) {
                    // System.out.println("snBuilder " + snBuilder.size());
                    lengthAdded -= lastAddedSn.length;
                    // System.out.print(lengthAdded + " / ");
                    lastAddedSn.setCount(charToAdd);
                    lengthAdded += lastAddedSn.length;
                    // System.out.println(lengthAdded);
                } else {
                    StringNode nextSn = new StringNode(sn.c, charToAdd);
                    snBuilder.add(nextSn);
                    lengthAdded += nextSn.length;
                }
            }
            System.out.println("lengthAdded: " + lengthAdded + " ");

            // backtrack optimal
            int prevMinLengthAdded = minLengthAdded;

            minLengthAdded = Math.min(lengthAdded + optimalAdd(sCompressed, scPos+1, k-delCount, snBuilder), minLengthAdded);
            // System.out.println("minLengthAdded: " + minLengthAdded);
            if (prevMinLengthAdded > minLengthAdded) {
                System.out.println(scPos + " Found new min: " + prevMinLengthAdded + " " + minLengthAdded);
                 for (StringNode x: snBuilder)
                     System.out.println("x: " + x.c + " " + x.count);
            }

            // return to previous state
            if (snbPos < snBuilder.size() - 1)
                snBuilder.remove(snbPos + 1);
        }
        return minLengthAdded;
    }

    public static void main(String[] args) {
        System.out.println("String Compression II");

        Leetcode1531 solution = new Leetcode1531();
        System.out.println(solution.getLengthOfOptimalCompression("aabaabbcbbbaccc", 6));
    }
}