package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.util.HashMap;

public class InlineConstants implements Transform {

    private HashMap<String, Value> symbolTable;

    @Override
    public void apply(AST ast) {
        symbolTable = ast.symboltable;

        //fancy stuff to loop through all children and find the stylerules (skips assignments basically)
        ast.root.getChildren().stream().filter(node -> node instanceof Stylerule).forEach(node -> {
            transformNode(((Stylerule) node));
        });
    }

    /**
     * Apply inline transformation to a stylerule.
     * @param parent start node
     */
    private void transformNode(Stylerule parent) {
        for (ASTNode child : parent.getChildren()) {
            if (child instanceof Declaration) {
                transformDeclaration(((Declaration) child));
            }
            // make sure we can handle nesting
            else if (child instanceof Stylerule) {
                transformNode(((Stylerule) child));
            }
        }
    }

    /**
     * Apply inline transformation to a declaration.
     * @param declaration start node
     */
    private void transformDeclaration(Declaration declaration) {
        if (declaration.value instanceof ConstantReference) {
            declaration.value = symbolTable.get(((ConstantReference) declaration.value).name);
        } else if (declaration.value instanceof Operation) {
            transformOperation(((Operation) declaration.value));
        }// else ignore
    }

    /**
     * Apply inline transformation to an operation.
     * @param operation start node
     */
    private void transformOperation(Operation operation) {
        operation.lhs = transformValue(operation.lhs);
        operation.rhs = transformValue(operation.rhs);
    }

    /**
     * Apply inline transformation to a value.
     * @param v start node
     */
    private Value transformValue(Value v) {
        if (v instanceof ConstantReference) {
            ConstantReference value = ((ConstantReference) v);
            return symbolTable.get(value.name);
        } else if (v instanceof Operation) { // we gotta handle all the operations
            transformOperation(((Operation) v));
        }
        return v;
    }
}
