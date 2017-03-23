package thomsonnfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import javax.print.DocFlavor;

public final class ThomsonNfa {


    public static State searchNFA(State s, char x) {

        //return if outLinks contain x
        if (s.containsOutLink(x)) {
            return s;
        }
        //else if outLinks doesnt contain x:
        for (Edge e : s.getOutLinks()) {
            if (e.getLabel() == 'E') {
                return searchNFA(e.getTrgt(), x);
            }
        }
        return null;
    }

    public static CheckNfa parse(String sRegex) {

        CheckNfa nfa = new CheckNfa();
        /*
         bcb: begining of the current branch is used for handling possible future 
         alternation branches. 
         */
        State bcb = nfa.getInState();
        char buffer = 0; //keeps the previous character for kleene star

        int i = 0;
        Stack<Character> StackNew = new Stack();

        while (i < sRegex.length()) {

            /*
             If arrive at a left bracket '(', then push it to the bracketStack.
             Read characters until the corresponding right bracket.
             Ignore inner brackets using stack. 
             */
            if (sRegex.charAt(i) == '(') {
                StackNew.push('(');
                StringBuilder inBracketSubExp = new StringBuilder();
                int j = i + 1;
                while (true) {
                    if (sRegex.charAt(j) == ')') {
                        StackNew.pop();
                        if (StackNew.empty()) {
                            break;
                        }
                    } else if (sRegex.charAt(j) == '(') {
                        StackNew.push('(');
                    }
                    if (!StackNew.empty()) {
                        inBracketSubExp.append(sRegex.charAt(j));
                    }
                    j++;
                }
                                
                CheckNfa subNFA = parse(inBracketSubExp.toString());

                //connect currentState to the begining of inBracketSubExp 
                for (Edge e : subNFA.getInState().getOutLinks()) {
                    nfa.getCurrState().addOutLink(e);
                }
                subNFA.removeInitial();
                i = j;
            }

            
            if (Character.isAlphabetic(sRegex.charAt(i)) && Character.isLowerCase(sRegex.charAt(i))) {

                buffer = sRegex.charAt(i);
                nfa.concat(sRegex.charAt(i));

            } else if (sRegex.charAt(i) == '*') {

                //make previous character/group wild card
                nfa.kleeneStar(buffer);
            } else if (sRegex.charAt(i) == '|') {//begining of an alternative branch

                bcb = nfa.altr(bcb);

            }
            i++;
        }
        return nfa;
    }

    
    public static boolean matches(String s, String regex) {

        boolean mtch = false;
        CheckNfa testNfa = parse(regex);
        State currentState = testNfa.getInState();

        if (currentState.isAccept()) {
            return true;
        }

        char[] elements = s.toCharArray();

        State tmpState = new State();
        characters:
        for (char e : elements) {

            tmpState = searchNFA(currentState, e);
            if (tmpState == null) {
                return false;
            }
            currentState = tmpState.transition(e);

            if (currentState.isAccept()) {
                mtch = true;
                continue characters;
            }
            mtch = false;
        }
        return mtch;
    }

    public static void main(String[] args) {

        List<ExpressionCheck> testCases = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Enter the regular expression you to check for");
        String check= new String();
        check=sc.nextLine();
        
        System.out.println("Enter the second expression you to check for");
        String check1= new String();
        check1=sc.nextLine();
        
        
        System.out.println("Enter the third expression you to check for");
        String check2= new String();
        check2=sc.nextLine();
               

        //Fields of each testCase of type Test : 
        //the regex, a matching correct and a non-matching string respectively
        //concatenation
        testCases.add(new ExpressionCheck(check, check1,check2));


        for (ExpressionCheck t : testCases) {
            System.out.println("\"" + t.check1 + "\"\t matches:\t\"" + t.check + "\"\t" + matches(t.check1, t.check));
            System.out.println("\"" + t.check2 + "\"\t matches:\t\"" + t.check + "\"\t" + matches(t.check2, t.check));
            System.out.println("---------------------------------------------------------");
        }

    }
}
