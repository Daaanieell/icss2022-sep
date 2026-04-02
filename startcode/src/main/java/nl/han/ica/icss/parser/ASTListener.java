package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.DivideOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();

		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }

	// -------------------------------------- stylerule en sheet --------------------------------------

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.push(new Stylesheet());
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.root = (Stylesheet) currentContainer.pop();
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = new Stylerule();

		//checks op welk soort selector het is
		if (ctx.LOWER_IDENT() != null) {
			stylerule.addChild(new TagSelector(ctx.LOWER_IDENT().getText()));
		} else if (ctx.ID_IDENT() != null) {
			stylerule.addChild(new IdSelector(ctx.ID_IDENT().getText()));
		} else if (ctx.CLASS_IDENT() != null) {
			stylerule.addChild(new ClassSelector(ctx.CLASS_IDENT().getText()));
		}

		currentContainer.push(stylerule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		ASTNode stylerule = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(stylerule);
	}

	// -------------------------------------- variable --------------------------------------

	@Override
	public void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		VariableAssignment assignment = new VariableAssignment();
		assignment.addChild(new VariableReference(ctx.CAPITAL_IDENT().getText()));
		currentContainer.push(assignment);
	}

	@Override
	public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		ASTNode expression = currentContainer.pop();
		ASTNode variable = currentContainer.pop();
		variable.addChild(expression);
		ASTNode parent = currentContainer.peek();
		parent.addChild(variable);
	}

	// -------------------------------------- property en declaration --------------------------------------

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.push(new Declaration());
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		ASTNode expression = currentContainer.pop();
		ASTNode declaration = currentContainer.pop();
		declaration.addChild(expression);
		ASTNode parent = currentContainer.peek();
		parent.addChild(declaration);
	}

	//een property is een leaf, dus is het niet nodig om het op de stack te zetten
	@Override
	public void exitProperty(ICSSParser.PropertyContext ctx) {
		ASTNode parent = currentContainer.peek();
		PropertyName propertyName = new PropertyName(ctx.LOWER_IDENT().getText());
		parent.addChild(propertyName);
	}

	// -------------------------------------- expressions --------------------------------------

	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		int size = ctx.add_op().size();
		ASTNode[] factors = new ASTNode[size + 1]; //ophalen van alle factors van de huidige som
		for (int i = size; i >= 0; i--) {
			factors[i] = currentContainer.pop();
		}

		ASTNode result = factors[0]; //dit houdt de vorige som bij
		for (int i = 0; i < size; i++) { //voor elke som een operation maken
			if (ctx.add_op(i).PLUS() != null) {
				AddOperation addOperation = new AddOperation(); //de vorige nested som = linkerkant van de huidige som
				addOperation.addChild(result);
				addOperation.addChild(factors[i + 1]);
				result = addOperation;
			} else if (ctx.add_op(i).MIN() != null) {
				SubtractOperation subtractOperation = new SubtractOperation();
				subtractOperation.addChild(result);
				subtractOperation.addChild(factors[i + 1]);
				result = subtractOperation;
			}
		}
		currentContainer.push(result);
	}

	@Override
	public void exitTerm(ICSSParser.TermContext ctx) {
		//zelfde als bij exitexpression

		int size = ctx.mult_op().size();
		ASTNode[] factors = new ASTNode[size + 1];
		for (int i = size; i >= 0; i--) {
			factors[i] = currentContainer.pop();
		}

		ASTNode result = factors[0];
		for (int i = 0; i < size; i++) {
			if (ctx.mult_op(i).MUL() != null) {
				MultiplyOperation multiplyOperation = new MultiplyOperation();
				multiplyOperation.addChild(result);
				multiplyOperation.addChild(factors[i + 1]);
				result = multiplyOperation;
			} else if (ctx.mult_op(i).DIV() != null) {
				DivideOperation divideOperation = new DivideOperation();
				divideOperation.addChild(result);
				divideOperation.addChild(factors[i + 1]);
				result = divideOperation;
			}
		}
		currentContainer.push(result);
	}

	@Override
	public void exitFactor(ICSSParser.FactorContext ctx) {
		//checkt voor iedere soort literal, zet die op de stack als er een match is

		if (ctx.PIXELSIZE() != null) {
			currentContainer.push(new PixelLiteral(ctx.PIXELSIZE().getText()));
		} else if (ctx.PERCENTAGE() != null) {
			currentContainer.push(new PercentageLiteral(ctx.PERCENTAGE().getText()));
		} else if (ctx.COLOR() != null) {
			currentContainer.push(new ColorLiteral(ctx.COLOR().getText()));
		} else if (ctx.SCALAR() != null) {
			currentContainer.push(new ScalarLiteral(ctx.SCALAR().getText()));
		} else if (ctx.TRUE() != null) {
			currentContainer.push(new BoolLiteral(true));
		} else if (ctx.FALSE() != null) {
			currentContainer.push(new BoolLiteral(false));
		} else if (ctx.CAPITAL_IDENT() != null) {
			currentContainer.push(new VariableReference(ctx.CAPITAL_IDENT().getText()));
		} else if (ctx.LOWER_IDENT() != null) {
			currentContainer.push(new VariableReference(ctx.LOWER_IDENT().getText()));
		}
	}

	// -------------------------------------- if-else --------------------------------------

	@Override
	public void enterIf_clause(ICSSParser.If_clauseContext ctx) {
		IfClause ifClause = new IfClause();

		//dit zijn de waardes binnen '[ ]' van een ifclause
		if (ctx.expression() != null) {
			ASTNode expr = currentContainer.pop();
			ifClause.addChild(expr);
		} else if (ctx.CAPITAL_IDENT() != null) {
			ifClause.addChild(new VariableReference(ctx.CAPITAL_IDENT().getText()));
		} else if (ctx.TRUE() != null) {
			ifClause.addChild(new BoolLiteral(true));
		} else if (ctx.FALSE() != null) {
			ifClause.addChild(new BoolLiteral(false));
		}

		currentContainer.push(ifClause);
	}
	@Override
	public void exitIf_clause(ICSSParser.If_clauseContext ctx) {
		ASTNode ifClause = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(ifClause);
	}

	@Override
	public void enterElse_clause(ICSSParser.Else_clauseContext ctx) {
		ElseClause elseClause = new ElseClause();
		currentContainer.push(elseClause);
	}

	@Override
	public void exitElse_clause(ICSSParser.Else_clauseContext ctx) {
		ASTNode elseClause = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(elseClause);
	}
}