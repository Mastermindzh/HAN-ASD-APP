package nl.han.ica.icss.ast;

import java.util.ArrayList;

/**
 * An assignment binds a value to an identifier.
 *
 */
public class Assignment extends ASTNode {
	
	public ConstantReference name;
	public Value value;

	@Override
	public String getNodeLabel() {
		return "Assignment (" + name.name + ")";
	}

	@Override
	public ArrayList<ASTNode> getChildren() {

		ArrayList<ASTNode> children = new ArrayList<>();
		children.add(value);
		return children;
	}

	@Override
	public void addChild(ASTNode child) {
		value = (Value) child;
	}
}
