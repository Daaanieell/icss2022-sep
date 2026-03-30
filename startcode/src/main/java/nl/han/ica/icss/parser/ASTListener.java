package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
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

		//checks op welk soort stylerule het is
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
		//
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

	@Override
	public void exitProperty(ICSSParser.PropertyContext ctx) {
		ASTNode parent = currentContainer.peek();
		PropertyName propertyName = new PropertyName(ctx.LOWER_IDENT().getText());
		parent.addChild(propertyName);
	}

	// -------------------------------------- expressions --------------------------------------


	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		// dit checkt hoeveel '+' en '-' er zijn. zorgt ervoor dat meerdere sommen in dezelfde regel herkent worden
		for (int expressions = ctx.add_op().size() - 1; expressions >= 0; expressions--) { //de loop is omgekeerd, anders is de rekenvolgorde fout..
			ASTNode right = currentContainer.pop();
			ASTNode left = currentContainer.pop();

			//checken welk type een som is en dat op de stack zetten met zijn left/right waardes
			if (ctx.add_op(expressions).PLUS() != null) {
				AddOperation addOperation = new AddOperation();
				addOperation.addChild(left);
				addOperation.addChild(right);
				currentContainer.push(addOperation);
			} else if (ctx.add_op(expressions).MIN() != null) {
				SubtractOperation subtractOperation = new SubtractOperation();
				subtractOperation.addChild(left);
				subtractOperation.addChild(right);
				currentContainer.push(subtractOperation);
			}
		}
	}

	@Override
	public void exitTerm(ICSSParser.TermContext ctx) {
		//zelfde als bij exitexpression, checkt voor nu alleen nog keersommen
		for (int expressions = ctx.mult_op().size() - 1; expressions >= 0; expressions--) {
			ASTNode right = currentContainer.pop();
			ASTNode left = currentContainer.pop();

			//TODO: deelsommen toevoegen?
			if (ctx.mult_op(expressions).MUL() != null) {
				MultiplyOperation multiplyOperation = new MultiplyOperation();
				multiplyOperation.addChild(left);
				multiplyOperation.addChild(right);
				currentContainer.push(multiplyOperation);
			}
		}
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

		//dit zijn de waardes binnen '( )'
		if (ctx.CAPITAL_IDENT() != null) {
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