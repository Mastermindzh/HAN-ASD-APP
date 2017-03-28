// Generated from nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.3
package nl.han.ica.icss.parser;
import org.antlr.v4.runtime.misc.NotNull;
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
	void enterStylesheet(@NotNull ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(@NotNull ICSSParser.StylesheetContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#style_rule}.
	 * @param ctx the parse tree
	 */
	void enterStyle_rule(@NotNull ICSSParser.Style_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#style_rule}.
	 * @param ctx the parse tree
	 */
	void exitStyle_rule(@NotNull ICSSParser.Style_ruleContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(@NotNull ICSSParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(@NotNull ICSSParser.ConstantContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(@NotNull ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(@NotNull ICSSParser.SelectorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull ICSSParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull ICSSParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull ICSSParser.DeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ICSSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull ICSSParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull ICSSParser.ValueContext ctx);
}