package calculator.hw1;
import static java.lang.Character.isDigit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
/**
 *
 * @author aggeliki
 */
public class ArithmeticCalculator {
    String str;
    BinaryTree tree;
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Expression: ");
        String exp = sc.nextLine();
        String expression = exp.replaceAll("\\s","");
               
        int j=0;
        for(int i=0; i<expression.length(); i++){
            char curr = expression.charAt(i);
            // look for unclosed or unopened parenthesis
            if(curr == '('){j++;}
            if(curr == ')'){j--;}
            
            //invalid character
            if(!(isDigit(curr) /*|| isLetter(curr)*/ || (curr=='.') || (curr=='+') || (curr=='-') || (curr=='/') || (curr=='*') || (curr=='^') || (curr=='x') || (curr==' ') || (curr==')') || (curr=='('))){ 
                System.out.print("\n[ERROR] Invalid character\n");
                return;
            }  
        }

        if(j>0){ // unclosed parenthesis
            System.out.print("\n[ERROR] Not closing opened parenthesis\n");
            return;
        }
        if(j<0){ // unopened parenthesis
            System.out.print("\n[ERROR] Closing unopened parenthesis\n");   
            return;
        }
        // operator at the beginning of expression
        char curr = expression.charAt(0);
        if((curr=='+') || (curr=='-') || (curr=='/') || (curr=='*') || (curr=='^') || (curr=='x')){
            System.out.print("\n[ERROR] Starting or ending with operator\n");
            return;
        }
        // operator at the end of expression
        curr = expression.charAt(expression.length()-1);
        if((curr=='+') || (curr=='-') || (curr=='/') || (curr=='*') || (curr=='^') || (curr=='x')){
            System.out.print("\n[ERROR] Starting or ending with operator\n");
            return;
        }
        
        //number after number
        Pattern p = Pattern.compile("\\d\\s\\d");
        Matcher m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Consecutive operands\n");
            return;
        }
        p = Pattern.compile("\\d\\.\\d+\\.");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Consecutive operands\n");
            return;
        }
        
        //operator after operator
        p = Pattern.compile("[\\p{Punct}&&[^()]][\\p{Punct}&&[^()]]");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Consecutive operators\n");
            return;
        }
        //operator after parenthesis  (+
        p = Pattern.compile("\\([\\p{Punct}&&[^()]]");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Operator appears after opening parenthesis\n");
            return;
        }
        //operator before parenthesis +)
        p = Pattern.compile("[\\p{Punct}&&[^()]]\\)");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Operator appears before closing parenthesis\n");
            return;
        }
        //open parenthesis after close parenthesis )(
        p = Pattern.compile("\\)\\(");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] ')' appears before opening parenthesis\n");
            return;
        }
        //number before open parenthesis 3(
        p = Pattern.compile("\\d\\(");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Operand before opening parenthesis\n");
            return;
        }
        //number after close parenthesis )3
        p = Pattern.compile("\\)\\d");
        m = p.matcher(expression);
        if(m.find()) {
            System.out.print("\n[ERROR] Operand after closing parenthesis\n");
            return;
        }
        
        ArithmeticCalculator MyArithmeticCalculator = new ArithmeticCalculator(expression);

        String opt = sc.nextLine();
        sc.close();
        
        System.out.print("\n");
        if("-s".equals(opt)){
            System.out.print("Postfix: ");
            String prnt = MyArithmeticCalculator.toString();
            StringBuilder strBd = new StringBuilder(prnt);
            strBd.deleteCharAt(0);
            System.out.print(strBd.toString()+"\n\n");
        }
        if("-d".equals(opt)){
            String prnt = MyArithmeticCalculator.toDotString();
            System.out.print(prnt+"\n");
        }
        if("-c".equals(opt)){
            System.out.print("Result: ");
            double result = MyArithmeticCalculator.calculate();
            System.out.printf("%f\n",result);
        }
    }
    
    
    
    public ArithmeticCalculator(String str){
        this.str = str;
        node rt = new node(str);
        tree = new BinaryTree(rt);
        tree.BuildTree(this.str, rt);
        tree.root = rt;
    }
    
    public String toDotString(){
        StringBuilder strB = new StringBuilder();
        return tree.preorder(tree.root, strB);
    }
    
    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        return tree.postorder(tree.root, strB);
    }
    
    public double calculate(){
        double result = BinaryTree.inorder(tree.root);
        return result;
    }
}
