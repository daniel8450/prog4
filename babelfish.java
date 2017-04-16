import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
public class babelfish {
	
	public static enum type{
		LPAREN, RPAREN, LBRACK, RBRACK, STRING, INT, VAR, IF, FOR,
		PLUS, MINUS, MULTIPLY, DIVIDE, EQUALS, SEMICOLON, RETURN,
		TRUE, FALSE, SEMI, FUNCTION, CALL, NUMBER
	}
	
	public static class Token{
		public final type x;
		public final String y;
	
		public Token(type x, String y){
			this.x = x;
			this.y = y;
		}
		
		public type getType(){
			return x;
		}
		
		public String toString(){
			if(x == type.VAR || x == type.NUMBER){
				return y;
			}else{
				return x.toString();
			}
		}
	}
	
	public static String getVar(String x, int y ){
		int i = y;
		for( ; i < x.length();){
			if(Character.isLetter(x.charAt(i))){
				i++;
			} else{
				return x.substring(y, i);
			}
		}
		return x.substring(y,i);
	}
	
	public static boolean isInt(String s){
		for(int i = 0; i < s.length(); i++){
			if(i == 0 && s.charAt(i) == '-')
				continue;
			if(!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}
	

	public static List<Token> lex(String in){
		String temp;
		List<Token> list = new ArrayList<Token>();
		for(int i = 0; i < in.length(); ){
			switch(in.charAt(i)){
			case '+':
				list.add(new Token(type.PLUS, "+"));
				i++;
				break;
			case '(':
				list.add(new Token(type.LPAREN, "("));
				i++;
				break;
			case ')':
				list.add(new Token(type.RPAREN, ")"));
				i++;
				break;
			case ':':
				list.add(new Token(type.LBRACK, "{"));
				i++;
				break;
			case '0':
				list.add(new Token(type.NUMBER, "0"));
				i++;
				break;
			case '1':
				list.add(new Token(type.NUMBER, "1"));
				i++;
				break;
			case '<':
				list.add(new Token(type.EQUALS, "="));
				i++;
				break;
			case '-':
				i++;
				break;
			default:
				if(Character.isWhitespace(in.charAt(i))){
					i++;
				} else{
					String var = getVar(in, i);
					i += var.length();			
                    switch(var){
						case "integer":
							list.add(new Token(type.INT, "int"));
							break;
						case "return":
							list.add(new Token(type.RETURN, "return"));
							break;
						case "function":
							list.add(new Token(type.FUNCTION, "function"));
							break;
						case "for":
							list.add(new Token(type.FOR, "for"));
							break;
						case "call":
							list.add(new Token(type.CALL, "call"));
							break;
						case "from":
							break;
						case "to":
							break;
						case "in":
							break;
						case "steps":
							break;
						case "of":
							break;
						default:
								list.add(new Token(type.VAR, var));
                    }
				}
				break;
			}
		}
		return list;
	}

	public static void main(String[] args) {
		
		File file = new File("sum.alg");
		try{
		
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			String input = sc.nextLine();
			List<Token> tokens = lex(input);
			for(Token t : tokens){
				if(t.getType() == type.FUNCTION){
					System.out.print("public static ");
				} else if(t.getType() == type.INT){
					System.out.print("int ");
				} else if(t.getType() == type.LPAREN){
					System.out.print("(");
				} else if(t.getType() == type.RPAREN){
					System.out.print(")" + "\n");
				} else if(t.getType() == type.LBRACK){
					System.out.print("{" + "\n");
				} else if(t.getType() == type.RBRACK){
					System.out.print("}" + "\n");
				} else if(t.getType() == type.EQUALS){
					System.out.print(" = ");
				} else if(t.getType() == type.FOR){
					System.out.print("for( THIS LINE IS WRONG)");
				} else if(t.getType() == type.CALL){
					System.out.print("");
				} else if(t.getType() == type.RETURN){
					System.out.print("return ");
				} else if(t.getType() == type.PLUS){
					System.out.print(" + ");
				} else{
					System.out.print(t);
				}
			}
			System.out.println(";");
			
		}
		
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		System.out.print("}");

	}

}
