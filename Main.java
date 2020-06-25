import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to Jack's ANTLR Test Calculator!");
		System.out.print(">>");
		Scanner in=new Scanner(System.in);
		TraversalVisitor eval = new TraversalVisitor();
		while(in.hasNextLine()) {
			String oper=in.nextLine()+'\n';
			ANTLRInputStream input = new ANTLRInputStream(oper);
	        ExprLexer lexer = new ExprLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        ExprParser parser = new ExprParser(tokens);
	        ParseTree tree = parser.prog();
	        try {
	        	eval.visit(tree);
	        }catch(Exception e) {
	        	
	        }
	        System.out.print(">>");
		}
	}
}
