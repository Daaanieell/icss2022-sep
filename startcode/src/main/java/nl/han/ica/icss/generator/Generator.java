package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class Generator {
	StringBuilder sb = new StringBuilder();

	public String generate(AST ast) {
        return createCSS(ast);
	}

	private String createCSS(AST ast) {
		generateStylesheet(ast.root);
		return sb.toString();
	}

	private void generateStylesheet(Stylesheet stylesheet) {
		for (ASTNode child : stylesheet.body) {
			if (child instanceof Stylerule stylerule) generateStylerule(stylerule);
		}
	}

	private void generateStylerule(Stylerule stylerule) {
		String selector = stylerule.selectors.getFirst().toString();

		sb.append(selector)
			.append(" {\n");

		for (ASTNode child : stylerule.body) {
			if (child instanceof Declaration declaration) generateDeclaration(declaration);
		}

		sb.append("}\n\n");
	}

	private void generateDeclaration (Declaration declaration) {
        Expression expression = declaration.expression;

        sb.append("  ")
			.append(declaration.property.name)
			.append(": ")
			.append(literalToString(expression))
			.append(";\n");
	}

	private String literalToString(Expression expression) {
		if (expression instanceof PixelLiteral px)       return px.value + "px";
		if (expression instanceof PercentageLiteral pct) return pct.value + "%";
		if (expression instanceof ColorLiteral col)      return col.value;
		if (expression instanceof ScalarLiteral sc)      return String.valueOf(sc.value);
		return "";
	}

}
