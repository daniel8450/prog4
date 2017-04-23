import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
public class babelfish {

	public static enum type{
		LPAREN, RPAREN, LBRACK, RBRACK, STRING, INT, VAR, IF, FOR,
		PLUS, MINUS, MULTIPLY, DIVIDE, EQUALS, SEMICOLON, RETURN,
		TRUE, FALSE, SEMI, FUNCTION, CALL, NUMBER, NONE
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
		int s = 0;
		int x = 0;
		int v = 0;
		int c = 0;
		int b = 0;
		int n = 0;
		int temp = 0;
		Token tmp1, tmp2, tmp3, tmp4;
		tmp1 = new Token(type.NONE, "none");
		tmp2 = new Token(type.NONE, "none");
		tmp3 = new Token(type.NONE, "none");
		tmp4 = new Token(type.NONE, "none");
		ArrayList<Token> list2 = new ArrayList<Token>(50);

		File file = new File("sum.alg");
		try{

		Scanner sc2 = new Scanner(System.in);
		Scanner sc = new Scanner(file);
		temp = sc2.nextInt();
		while(sc.hasNextLine()){
			String input = sc.nextLine();
			List<Token> tokens = lex(input);

			for(Token t : tokens){
				list2.add(s,t);

				if(t.getType() == type.FOR){
					c = 1;
					v = s;
				}

				s++;
			}

			if(c == 1){
				tmp1 = list2.get(v+1);
				tmp2 = list2.get(v+2);
				tmp3 = list2.get(v+3);
				tmp4 = list2.get(v+4);
			}

////////////java/////////////////
			for(Token t : tokens){
				if(t.getType() == type.FUNCTION){
					switch(temp){
						case 1:
						System.out.print("public static ");
						break;
						case 2:
						System.out.print("");
						break;
						case 3:
						System.out.print("def ");
						break;
					}
					b = 0;
					n = 0;
				} else if(t.getType() == type.INT){
					switch(temp){
						case 1:
						System.out.print("int ");
						break;
						case 2:
						System.out.print("int ");
						break;
						case 3:
						System.out.print("");
						break;
					}

					b = 0;
					n = 0;
				} else if(t.getType() == type.LPAREN){

					System.out.print("(");
					b = 0;
					n = 0;
				} else if(t.getType() == type.RPAREN){
					System.out.print(")");
					b = 0;
					n = 0;
				} else if(t.getType() == type.LBRACK){
					switch(temp){
						case 1:
						System.out.print("{");
						break;
						case 2:
						System.out.print("{");
						break;
						case 3:
						System.out.print("");
						break;
					}
					b = 0;
					n = 0;
				} else if(t.getType() == type.RBRACK){
					switch(temp){
						case 1:
						System.out.print("}");
						break;
						case 2:
						System.out.print("}");
						break;
						case 3:
						System.out.print("");
						break;
					}
					b = 0;
					n = 0;
				} else if(t.getType() == type.EQUALS){
					System.out.print(" = ");
					b = 0;
					n = 0;
				} else if(t.getType() == type.FOR){
					switch(temp){
						case 1:
						System.out.print("for("+tmp1+" = "+tmp2+"; "+tmp1+" < "+tmp3+
						"; "+tmp1+" += "+tmp4+")");
						break;
						case 2:
						System.out.print("for("+tmp1+" = "+tmp2+"; "+tmp1+" < "+tmp3+
						"; "+tmp1+" = "+tmp1+" + "+tmp4+")");
						break;
						case 3:
						System.out.print("for "+tmp1+" in range("+tmp2+","+tmp3+"):");
						break;
					}

					b = 1;
					n = 0;
				} else if(t.getType() == type.CALL){
					System.out.print("");
					b = 0;
					n = 0;
				} else if(t.getType() == type.RETURN){
					System.out.println();
					System.out.println("}");
					System.out.print("return ");
					b = 0;
					n = 1;
				} else if(t.getType() == type.PLUS){
					System.out.print(" + ");
					b = 0;
					n = 0;
				} else{
					if(b == 1){
						System.out.print("");
					}else if(n == 1){
						System.out.print(t);
						System.out.println();
						System.out.print("}");
					}else{
						System.out.print(t);
					}

				}

			}
			System.out.println(" ");
		}

		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}

}
