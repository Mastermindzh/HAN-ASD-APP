package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.awt.*;

public class EvalOperations implements Transform {

    @Override
    public void apply(AST ast) {
        //fancy stuff to loop through all children and find the stylerules (skips assignments basically)
        ast.root.getChildren().stream().filter(node -> node instanceof Stylerule).forEach(node -> {
            transformNode((Stylerule) node);
        });
    }

    /***
     * Recursively loops through all children and calls solveOperation on all Operation nodes.
     * @param parent parent to transforms nodes of.
     */
    private void transformNode(Stylerule parent) {
        for (ASTNode child : parent.getChildren()) {
            if (child instanceof Declaration) {
                Declaration declaration = ((Declaration) child);
                if (declaration.value instanceof Operation) {
                    declaration.value = solveOperation((Operation) declaration.value);
                }
            }
            // make sure we can handle nesting
            else if (child instanceof Stylerule) {
                transformNode(((Stylerule) child));
            }
        }
    }

    /***
     * Recursively solves operations.
     * @param operation Operation to check
     * @return answer in literal form.
     */
    private Literal solveOperation(Operation operation) {
        Value left;
        Value right;
        if (operation.lhs instanceof Operation) {
            left = solveOperation((Operation) operation.lhs);
        }
        else {
            left = operation.lhs;
        }

        if (operation.rhs instanceof Operation) {
            right = solveOperation((Operation) operation.rhs);
        } else {
            right = operation.rhs;
        }

        Operation newOperation = new Operation();
        newOperation.lhs = left;
        newOperation.rhs = right;

        return doMath(newOperation, operation.operator);
    }

    /***
     * Determine the type of the variables and run the calculation
      * @param operation operation to be calculated
     * @param operator operator to use in calculation
     * @return The answer in Literal form.
     */
    private Literal doMath(Operation operation, Operation.Operator operator) {
        if (operation.lhs instanceof PixelLiteral) {
            return getPixelLiteral(operation, operator);
        } else if (operation.lhs instanceof PercentageLiteral) {
            return getPercentageLiteral(operation, operator);

        } else if (operation.lhs instanceof ColorLiteral) {
            return getColourLiteral(operation, operator);
        }

        return new PixelLiteral(0);
    }

    // this should really be done with a generic class..
    private Literal getColourLiteral(Operation operation, Operation.Operator operator) {
        int left = Color.decode(((ColorLiteral) operation.lhs).value).getRGB();
        int right = Color.decode(((ColorLiteral) operation.rhs).value).getRGB();

        if (operator.equals(Operation.Operator.MIN)) {
            return new ColorLiteral("#" + Integer.toHexString(left - right));
        } else if (operator.equals(Operation.Operator.PLUS)) {
            return new ColorLiteral("#" + Integer.toHexString(left + right));
        } else if (operator.equals(Operation.Operator.MULTIPLY)) {
            return new ColorLiteral("#" + Integer.toHexString(left * right));
        } else if (operator.equals(Operation.Operator.DIVIDE)) {
            return new ColorLiteral("#" + Integer.toHexString(left / right));
        }
        return null;
    }

    // this should really be done with a generic class..
    private Literal getPercentageLiteral(Operation operation, Operation.Operator operator) {
        int left = ((PercentageLiteral) operation.lhs).value;
        int right = ((PercentageLiteral) operation.rhs).value;

        if (operator.equals(Operation.Operator.MIN)) {
            return new PercentageLiteral(left - right);
        } else if (operator.equals(Operation.Operator.PLUS)) {
            return new PercentageLiteral(left + right);
        } else if (operator.equals(Operation.Operator.MULTIPLY)) {
            return new PercentageLiteral(left * right);
        } else if (operator.equals(Operation.Operator.DIVIDE)) {
            return new PercentageLiteral(left / right);
        }
        return null;
    }

    // this should really be done with a generic class..
    private Literal getPixelLiteral(Operation operation, Operation.Operator operator) {
        int left = ((PixelLiteral) operation.lhs).value;
        int right = ((PixelLiteral) operation.rhs).value;

        if (operator.equals(Operation.Operator.MIN)) {
            return new PixelLiteral(left - right);
        } else if (operator.equals(Operation.Operator.PLUS)) {
            return new PixelLiteral(left + right);
        } else if (operator.equals(Operation.Operator.MULTIPLY)) {
            return new PixelLiteral(left * right);
        } else if (operator.equals(Operation.Operator.DIVIDE)) {
            return new PixelLiteral(left / right);
        }
        return null;
    }
}

