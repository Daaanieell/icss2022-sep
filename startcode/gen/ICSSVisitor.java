// Generated from /home/daniel/github-repo/work/cni/app/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2

    package nl.han.ica.icss.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(ICSSParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(ICSSParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#css_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCss_rule(ICSSParser.Css_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ICSSParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ICSSParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(ICSSParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#add_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_op(ICSSParser.Add_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#mult_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult_op(ICSSParser.Mult_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(ICSSParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ICSSParser.ConditionContext ctx);
}