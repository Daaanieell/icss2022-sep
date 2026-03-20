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
	private IHANStack<ASTNode> currentContainer = new HANStack<>();

	public ASTListener() {
		ast = new AST();

		//TODO: waar is dit voor?
		//currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.push(new Stylesheet());
	}

	@Override
	public void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		VariableAssignment assignment = new VariableAssignment();
		assignment.addChild(new VariableReference(ctx.CAPITAL_IDENT().getText()));
		currentContainer.push(assignment);
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = new Stylerule();

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
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.push(new Declaration());
	}

	@Override
	public void exitProperty(ICSSParser.PropertyContext ctx) {
		ASTNode parent = currentContainer.peek();
		PropertyName propertyName = new PropertyName(ctx.LOWER_IDENT().getText());
		parent.addChild(propertyName);
	}

	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		//TODO
	}

	@Override
	public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		ASTNode variable = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(variable);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		ASTNode stylerule = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(stylerule);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		ASTNode declaration = currentContainer.pop();
		ASTNode parent = currentContainer.peek();
		parent.addChild(declaration);
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.root = (Stylesheet) currentContainer.pop();
	}


}