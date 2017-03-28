package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

    private AST ast;

    private Stack<ASTNode> stack;
    private HashMap<String, Operation.Operator> operators = new HashMap<>();

    public ASTListener() {
        ast = new AST();
        operators.put("+", Operation.Operator.PLUS);
        operators.put("-", Operation.Operator.MIN);
        operators.put("*", Operation.Operator.MULTIPLY);
        operators.put("/", Operation.Operator.DIVIDE);
    }

    public AST getAST() {
        return ast;
    }

    @Override
    public void enterStylesheet(@NotNull ICSSParser.StylesheetContext ctx) {
        stack = new Stack<>();
        stack.push(ast.root);
    }

    @Override
    public void enterStyle_rule(@NotNull ICSSParser.Style_ruleContext ctx) {
        Stylerule stylerule = new Stylerule();
        stack.push(stylerule);
    }

    @Override
    public void exitStyle_rule(@NotNull ICSSParser.Style_ruleContext ctx) {
        ASTNode astNode = stack.pop();
        stack.peek().addChild(astNode);
    }

    @Override
    public void enterSelector(@NotNull ICSSParser.SelectorContext ctx) {
        String s = "";
        if (ctx.IDENTIFIER() != null) {
            s = ctx.IDENTIFIER().getText();
        }
        s += ctx.STRING().getText();
        stack.push(new Selector(s));
    }

    @Override
    public void exitSelector(@NotNull ICSSParser.SelectorContext ctx) {
        Selector s = (Selector) stack.pop();
        ((Stylerule) stack.peek()).selector = s;
    }

    @Override
    public void enterConstant(@NotNull ICSSParser.ConstantContext ctx) {
        Assignment a = new Assignment();
        a.name = new ConstantReference(ctx.CONSTANT_NAME().getText());
        a.value = getValue(ctx.value());
        stack.push(a);
    }

    @Override
    public void exitConstant(@NotNull ICSSParser.ConstantContext ctx) {
        ast.root.addChild(stack.pop());
    }

    @Override
    public void enterDeclaration(@NotNull ICSSParser.DeclarationContext ctx) {
        Declaration declaration = new Declaration();
        declaration.property = ctx.STRING().toString();
        declaration.value = getValue(ctx.value());
        stack.peek().addChild(declaration);
    }

    // fetch the value, if it happens to be an operation fetch it recursively until we got everything.
    private Value getValue(ICSSParser.ValueContext vc) {
        Value value = null;
        if (vc.COLOUR() != null) {
            value = new ColorLiteral(vc.COLOUR().getText());
        } else if (vc.SIZE() != null) {
            if (vc.SIZE().getText().endsWith("%")) {
                value = new PercentageLiteral(vc.SIZE().getText());
            } else {
                value = new PixelLiteral(vc.SIZE().getText());
            }
        } else if (vc.CONSTANT_NAME() != null) {
            value = new ConstantReference(vc.CONSTANT_NAME().getText());
        } else if (vc.OPERATOR() != null) {
            Operation operation = new Operation();
            operation.lhs = getValue(vc.value().get(0));
            operation.operator = operators.get(vc.OPERATOR().getText());
            operation.rhs = getValue(vc.value().get(1));
            value = operation;
        }
        return value;
    }
}