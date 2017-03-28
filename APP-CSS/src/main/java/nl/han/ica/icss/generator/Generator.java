package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;

public class Generator {

	public String generate(AST ast) {
		StringBuilder builder = new StringBuilder();

		ast.root.getChildren().stream().filter(node -> node instanceof Stylerule).forEach(node -> processStyleRule((Stylerule) node, builder, ""));

		return builder.toString();
	}

	private void processStyleRule(Stylerule stylerule, StringBuilder builder, String prefix) {
		builder
				.append(prefix)
				.append(stylerule.selector)
				.append(" {")
				.append(System.lineSeparator());

		// loop through declarations and add them.
		stylerule.getChildren().stream().filter(node -> node instanceof Declaration).forEach(node -> addDeclaration(builder, (Declaration) node));

		builder
				.append('}')
				.append(System.lineSeparator());

		// handle recursion
		stylerule.getChildren().stream().filter(node -> node instanceof Stylerule).forEach(node -> processStyleRule((Stylerule) node, builder, prefix + stylerule.selector + " > "));
	}

	/**
	 * append a declaration to a string
	 * @param builder StringBuilder to append to
	 * @param node Declaration to append.
     */
	private void addDeclaration(StringBuilder builder, Declaration node) {
		indent(8,builder);
		builder
				.append(node.property)
				.append(": ")
				.append(node.value)
				.append(';')
				.append(System.lineSeparator());
	}

	/**
	 * Add an indentation to a string.
	 * @param numberOfSpaces number of spaces to indent
	 * @param builder StringBuilder to append to
     */
	private void indent(int numberOfSpaces, StringBuilder builder){
		for(int i = 0; i < numberOfSpaces; i++){
			builder.append(" ");
		}
	}
}