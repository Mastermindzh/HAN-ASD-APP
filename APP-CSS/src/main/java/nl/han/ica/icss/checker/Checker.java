package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Checker {

    private enum ValueType {
        PIXELVALUE,
        PERCENTAGE,
        COLORVALUE,
        UNDEFINED
    }

    private HashMap<String, Value> symboltable;
    private ArrayList<String> colourProperties = new ArrayList<>();
    private ArrayList<String> sizeProperties = new ArrayList<>();


    public Checker() {
        colourProperties.add("color");
        colourProperties.add("background-color");
        sizeProperties.add("width");
        sizeProperties.add("height");
    }

    public void check(AST ast) {
        //Clear
        symboltable = new HashMap<>();

        //fix symboltable
        findAssignments(ast.root);
        //Save the symboltable.
        ast.symboltable = symboltable;

        checkNode(ast.root);

        //Save the verdict
        if (ast.getErrors().isEmpty()) {
            ast.checked = true;
        }
    }

    private void findAssignments(ASTNode node) {
        if (node instanceof Assignment) {
            checkAssignment((Assignment) node);
        }

        if (node.getChildren().size() > 0) {
            node.getChildren().forEach(this::findAssignments);
        }
    }

    private void checkNode(ASTNode node) {
        if (node instanceof Assignment) {
            checkAssignment((Assignment) node);
        } else if (node instanceof ConstantReference) {
            checkReference((ConstantReference) node);
        } else if (node instanceof Operation) {
            checkOperation((Operation) node);
        } else if (node instanceof Declaration) {
            checkDeclaration((Declaration) node);
        }

        if (node.getChildren().size() > 0) {
            node.getChildren().forEach(this::checkNode);
        }
    }

    private void checkDeclaration(Declaration node) {
        if (node.value instanceof ColorLiteral) {
            if (!colourProperties.contains(node.property)) {
                node.setError("You can only assign a colour value to a colour property.");
            }
        } else if (node.value instanceof PercentageLiteral || node.value instanceof PixelLiteral) {
            if (!sizeProperties.contains(node.property)) {
                node.setError("You can only assign a size value to a size property.");
            }
        }
    }

    private ValueType checkOperation(Operation node) {

        ValueType left;
        ValueType right;

        if (node.lhs instanceof Operation) {
            left = checkOperation((Operation) node.lhs);
        } else {
            left = checkValueType(node.lhs);
        }
        if (node.rhs instanceof Operation) {
            right = checkOperation((Operation) node.rhs);
        } else {
            right = checkValueType(node.rhs);
        }
        if (left != right) {
            node.setError("Operation error");
            return ValueType.UNDEFINED;
        } else {
            return left;
        }

    }

    /**
     * Returns the valueType
     * @param v value to check type of
     * @return ValueType of v
     */
    private ValueType checkValueType(Value v) {
        if (v instanceof ConstantReference) {
            ConstantReference value = (ConstantReference) v;
            if (!isCircularReference(value)) {
                if (symboltable.get(value.name) == null) {
                    return ValueType.UNDEFINED;
                } else {
                    return checkValueType(symboltable.get(value.name));
                }
            }
        } else if (v instanceof Operation) {
            return checkOperation((Operation) v);
        } else {
            if (v instanceof PercentageLiteral) {
                return ValueType.PERCENTAGE;
            } else if (v instanceof PixelLiteral) {
                return ValueType.PIXELVALUE;
            } else if (v instanceof ColorLiteral) {
                return ValueType.COLORVALUE;
            }
        }

        return ValueType.UNDEFINED;
    }

    /**
     * varifies whether an assignment is valid.
     * @param node assignment to check
     */
    private void checkAssignment(Assignment node) {
        String nodeName = node.name.name;
        boolean error = false;

        // check whether assignment would cause a loop
        if (node.value instanceof ConstantReference) {
            ConstantReference reference = ((ConstantReference) node.value);
            if (reference.name.equals(nodeName)) {
                node.setError("You can't assign a constant to itself.");
                error = true;
            } else {
                isCircularReference(reference);
            }
        }
        // if assignment is an operation, check the operation
        if (node.value instanceof Operation) {
            checkOperation((Operation) node.value);
        }

        // if no error has been found add it to symboltable.
        if (!error) {
            symboltable.put(nodeName, node.value);
        }
    }

    /**
     * checks whether reference is a circular reference
     * @param node Reference to check.
     * @return true if reference is circular, else: false.
     */
    private boolean isCircularReference(ConstantReference node) {
        ArrayList<String> trail = new ArrayList<>();
        trail.add(node.name);

        String previousName = node.name;
        boolean running = true;
        boolean isCircular = false;
        while ((symboltable.get(previousName) instanceof ConstantReference) && running) {
            ConstantReference newReference = (ConstantReference) symboltable.get(previousName);
            if (!trail.contains(newReference.name)) {
                trail.add(newReference.name);
                previousName = newReference.name;
            } else {
                node.setError("Circular reference detected.");
                running = false;
                isCircular = true;
            }
        }

        return isCircular;
    }

    /**
     * Check whether reference is used but never assigned
     * @param node reference to check
     */
    private void checkReference(ConstantReference node) {
        if (!symboltable.containsKey(node.name)) {
            node.setError(String.format("Constant %s used but not assigned", node.name));
        }
    }

}
