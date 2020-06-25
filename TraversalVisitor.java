import java.util.HashMap;
import java.util.Map;
public class TraversalVisitor extends ExprBaseVisitor<Integer> {
	Map<String, Integer> memory = new HashMap<String,Integer>();
	@Override 
	public Integer visitProg(ExprParser.ProgContext ctx) { 
		visitChildren(ctx); 
		return 0;
	}
	@Override 
	public Integer visitPrintExp(ExprParser.PrintExpContext ctx) { 
		int value=visit(ctx.expr());
		System.out.println(value);
		return value;
	}
	@Override 
	public Integer visitAssign(ExprParser.AssignContext ctx) { 
		String id=ctx.ID().getText();
		int value=visit(ctx.expr());
		memory.put(id, value);
		return value; 
	}
	@Override 
	public Integer visitBlank(ExprParser.BlankContext ctx) { 
		return visitChildren(ctx); 
	}
	@Override 
	public Integer visitParens(ExprParser.ParensContext ctx) {
		return visit(ctx.expr()); 
	}
	@Override 
	public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
		int left=visit(ctx.expr(0));
		int right=visit(ctx.expr(1));
		if(ctx.op.getType()==ExprParser.MUL)return left*right;
		else return left/right;
	}
	@Override 
	public Integer visitAddSub(ExprParser.AddSubContext ctx) { 
		int left=visit(ctx.expr(0));
		int right=visit(ctx.expr(1));
		if(ctx.op.getType()==ExprParser.ADD)return left+right;
		else return left-right;
	}
	@Override 
	public Integer visitId(ExprParser.IdContext ctx) { 
		String id=ctx.ID().getText();
		if(memory.containsKey(id))return memory.get(id);
		else return 0;
	}
	@Override 
	public Integer visitInt(ExprParser.IntContext ctx) { 
		return Integer.valueOf(ctx.INT().getText()); 
	}
}
