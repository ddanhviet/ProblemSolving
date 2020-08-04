package leetcode;

import java.util.Stack;

public class Leetcode224 {
    /*
     (1+(4+5+2)-3)+(6+8)
     -> push (
     -> push 1
     -> push +
     -> push (
     -> push 4
     -> push +
     -> pop 4 + [see 5]
     -> push 9
     -> push +
     -> pop 9 + [see 2]
     -> push 11
     -> pop 11 ( [see ')']
     -> pop 1 + [see 11]
     -> push 12
     -> push -
     -> pop 12 - [see 3]
     -> push 9
     -> pop 9 ( [see ')']
     -> push 9
     -> push +
     -> push (
     -> push 6
     -> push +
     -> pop 6 + [see 8]
     -> push 14
     -> pop 14 ( [see ')']
     -> pop 9 +
     -> push 23
    */
    public int calculate(String s) {
        Stack<String> exp = new Stack<>();

        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            // System.out.println(c);
            if (c == ' ')
                continue;
            if (c == '(' || c == '+' || c == '-')
                exp.push(Character.toString(c));
            else {
                if (c == ')') {
                    String number = exp.pop();
                    exp.pop(); // pop '('
                    exp.push(number);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    while (i < s.length()-1 && Character.isDigit(s.charAt(i+1))) {
                        sb.append(s.charAt(++i));
                    }
                    exp.push(sb.toString());
                }
                int nextNumber = Integer.valueOf(exp.pop());
                if (!exp.isEmpty() && (exp.peek().equals("+") || exp.peek().equals("-"))) {
                    String ops = exp.pop();
                    String theOtherNumber = exp.pop();
                    if (ops.equals("-"))
                        nextNumber = Integer.valueOf(theOtherNumber) - nextNumber;
                    else nextNumber = Integer.valueOf(theOtherNumber) + nextNumber;
                }
                exp.push(Integer.toString(nextNumber));
            }
        }
        return exp.isEmpty() ? 0 : Integer.valueOf(exp.pop());
    }

    public static void main(String[] args) {
        Leetcode224 lc224 = new Leetcode224();
        System.out.println(lc224.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

}

