package application;

class Notation{

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		NotationStack<?> stack= new NotationStack(postfix.length());
		String infix="", first, second;
		for(int i =0; i< postfix.length();i++) {
			if(postfix.charAt(i)==' ') {
			}
			if (postfix.charAt(i)>=48 && postfix.charAt(i)<=57) {
				try {
					stack.push(postfix.charAt(i));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			//if character is operator 
			if(postfix.charAt(i)=='*'|| postfix.charAt(i)=='/'|| postfix.charAt(i)=='+'|| postfix.charAt(i)=='-') {
				try {
					first=stack.pop().toString();
					second=stack.pop().toString();
				} catch (StackUnderflowException e) {
					throw new InvalidNotationFormatException();
				}
				infix="("+ second + postfix.charAt(i) + first+")";
				try {
					stack.push(infix);
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException();
				}
			}
		}
		if(stack.size()>1) {
			throw new InvalidNotationFormatException();
		}
		else return stack.toString();
	}

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		NotationStack<?> stack= new NotationStack(infix.length());
		NotationQueue<?> postfix=new NotationQueue(infix.length());
		for(int i =0; i< infix.length();i++) {
			//if space ignore
			if(infix.charAt(i)==' ') {
				
			}
			
			if (infix.charAt(i)>=48 && infix.charAt(i)<=57) {
				try {
					postfix.enqueue(infix.charAt(i));
				} catch (QueueOverflowException e) {
					e.printStackTrace();
				}
			}
			
			if(infix.charAt(i)=='(') {
				try {
					stack.push(infix.charAt(i));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
			try {
				if(infix.charAt(i)=='*') {
					stack.push('*');
					}
				else if(infix.charAt(i)=='/') {
					stack.push('/');
					}
				else if(infix.charAt(i)=='+') {
					stack.push('+');
				}
				else if(infix.charAt(i)=='-') {
					stack.push('-');
				} 
			}catch (StackOverflowException e) {
				e.printStackTrace();
				}
			
			if(infix.charAt(i) ==')') {
				try {
					postfix.enqueue(stack.pop());
					stack.pop();
				}catch(QueueOverflowException | StackUnderflowException e) {
					throw new InvalidNotationFormatException();
				}
		}
	}
		return postfix.toString();
	}


	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		NotationStack<?> stack= new NotationStack(postfixExpr.length());
		double expression=0, first, second;
		for(int i =0; i< postfixExpr.length();i++) {
			if(postfixExpr.charAt(i)==' ') {
			}
			if (postfixExpr.charAt(i)>=48 && postfixExpr.charAt(i)<=57) {
				try {
					stack.push(postfixExpr.charAt(i));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			if(postfixExpr.charAt(i)=='*'|| postfixExpr.charAt(i)=='/'|| postfixExpr.charAt(i)=='+'|| postfixExpr.charAt(i)=='-') {
				try {
					first=Double.valueOf(stack.pop().toString());
					second=Double.valueOf(stack.pop().toString());
				} catch (StackUnderflowException e) {
					throw new InvalidNotationFormatException();
				}
				//evaluate based on operator
				if(postfixExpr.charAt(i)=='*')
					expression = second* first;
				else if(postfixExpr.charAt(i)=='/')
					expression = second/ first;
				else if(postfixExpr.charAt(i)=='+')
					expression = second+ first;
				else if(postfixExpr.charAt(i)=='-')
					expression = second- first;
					
				try {
					stack.push(expression);
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException();
				}
			}
		}
		return expression;
	}

	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		// TODO Auto-generated method stub
		String postfix = convertInfixToPostfix(infixExpr);
		
		double evaluatePost=evaluatePostfixExpression(postfix);
		return evaluatePost;
	}
}