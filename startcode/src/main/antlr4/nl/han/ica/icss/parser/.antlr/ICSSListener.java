// Generated from /home/daniel/github-repo/work/cni/app/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ICSSParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ICSSParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ICSSParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ICSSParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#add_op}.
	 * @param ctx the parse tree
	 */
	void enterAdd_op(ICSSParser.Add_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#add_op}.
	 * @param ctx the parse tree
	 */
	void exitAdd_op(ICSSParser.Add_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#mult_op}.
	 * @param ctx the parse tree
	 */
	void enterMult_op(ICSSParser.Mult_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#mult_op}.
	 * @param ctx the parse tree
	 */
	void exitMult_op(ICSSParser.Mult_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#if_clause}.
	 * @param ctx the parse tree
	 */
	void enterIf_clause(ICSSParser.If_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#if_clause}.
	 * @param ctx the parse tree
	 */
	void exitIf_clause(ICSSParser.If_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void enterElse_clause(ICSSParser.Else_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void exitElse_clause(ICSSParser.Else_clauseContext ctx);
}