// Generated from /home/daniel/github-repo/work/cni/app/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2

    package nl.han.ica.icss.parser;

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
	 * Enter a parse tree produced by {@link ICSSParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(ICSSParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(ICSSParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(ICSSParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(ICSSParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#css_rule}.
	 * @param ctx the parse tree
	 */
	void enterCss_rule(ICSSParser.Css_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#css_rule}.
	 * @param ctx the parse tree
	 */
	void exitCss_rule(ICSSParser.Css_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ICSSParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ICSSParser.BodyContext ctx);
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
	 * Enter a parse tree produced by {@link ICSSParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(ICSSParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(ICSSParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ICSSParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ICSSParser.ConditionContext ctx);
}