import java.util.Stack;
import java.util.StringTokenizer;


public class Infix {

	public int infix(String expression) {
        expression = expression.replaceAll("[\t\n ]", "") + "=";
        final String operator = "+-*=";
        StringTokenizer tokenizer = new StringTokenizer(expression, operator, true);
        Stack<String> operatorStack = new Stack<String>();
        Stack<String> valueStack = new Stack<String>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (operator.indexOf(token)<0) {
            	valueStack.push(token);
            }
            else {
                operatorStack.push(token);
            }
            resolve(valueStack, operatorStack);
        }
        String lastOne = valueStack.pop();
        return Integer.parseInt(lastOne);  
	}
        
    public int getPriority(String op) {
        if (op.equals("*"))
            return 1;
        else if(op.equals("+") || op.equals("-"))
            return 2;
        else if(op.equals("="))
            return 3;
        else
            return Integer.MIN_VALUE;
    }
    
    public void resolve(Stack<String> values, Stack<String> operators) {
        while (operators.size() >= 2) {
            String first = operators.pop();
            String second = operators.pop();
            if (getPriority(first) < getPriority(second)) {
                operators.push(second);
                operators.push(first);
            }
            else {
                String firstValue = values.pop();
                String secondValue = values.pop();
                values.push(getResults(secondValue, second, firstValue));
                operators.push(first);
            }
            
        }
    }
    
    public String getResults(String operand1, String operator, String operand2) {
        int op1 = Integer.parseInt(operand1);
        int op2 = Integer.parseInt(operand2);
        if(operator.equals("*"))
            return "" + (op1 * op2);
        else if(operator.equals("+"))
            return "" + (op1 + op2);
        else if(operator.equals("-"))
            return "" + (op1 - op2);
        return null;
    }
}
