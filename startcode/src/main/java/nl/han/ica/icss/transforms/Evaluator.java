package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Evaluator implements Transform {

    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();
        checkNode(ast.root, null);
    }

    //zelfde als bij checker, door boom heen lopen
    private void checkNode(ASTNode node, ASTNode parent) {
        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            addNewScope();

        for (ASTNode child : node.getChildren()) {
            //checks op type node
            if (child instanceof VariableAssignment variableAssignment) addVariableToScope(variableAssignment);
            if (child instanceof Declaration declaration) transformDeclaration(declaration);
            if (child instanceof IfClause ifClause) transformIfClause(ifClause, node);
            checkNode(child, node);
        }

        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            removeFirstScope();
    }

    private void transformIfClause(IfClause ifClause, ASTNode parent) {
        BoolLiteral condition = (BoolLiteral) evaluateExpression(ifClause.conditionalExpression);

        ArrayList<ASTNode> body = condition.value ? ifClause.body : ifClause.elseClause.body;
        if (condition.value) { //alleen de if is true
            for (ASTNode child : body) {
                parent.addChild(child);
                parent.removeChild(ifClause);

                //als een ifclause gevonden is, check die
                if (child instanceof IfClause) transformIfClause((IfClause) child, parent);
            }
        } else if (ifClause.elseClause != null) { //alleen de else is true
            for (ASTNode child : body) {
                parent.addChild(child);
                parent.removeChild(ifClause);

                //zelfde hier
                if (child instanceof IfClause) transformIfClause((IfClause) child, parent);
            }
        } else if (ifClause.elseClause == null) { // er is geen else en de conditie is altijd false
            parent.removeChild(ifClause);
        }
    }

    private void transformDeclaration(Declaration declaration) {
        declaration.expression = evaluateExpression(declaration.expression);
    }

    // ------------------- helper functies -------------------

    private Literal evaluateExpression(Expression expression) {
        return switch (expression) {
            case ColorLiteral colorLiteral                 -> colorLiteral;
            case PixelLiteral pixelLiteral                 -> pixelLiteral;
            case PercentageLiteral percentageLiteral       -> percentageLiteral;
            case ScalarLiteral scalarLiteral               -> scalarLiteral;
            case BoolLiteral boolLiteral                   -> boolLiteral;
            case VariableReference variableReference       -> getLiteralFromScope(variableReference.name);
            case Operation operation                       -> operation.calc(evaluateExpression(operation.lhs), evaluateExpression(operation.rhs));
            default                                        -> throw new RuntimeException("onbekende expression: " + expression);
        };
    }

    private Literal getLiteralFromScope(String name) {
        for (int i = 0; i < variableValues.getSize(); i++) {
            if (variableValues.get(i).containsKey(name)) {
                return variableValues.get(i).get(name);
            }
        }
        return null;
    }

    private void addVariableToScope(VariableAssignment va) {
        Literal value = evaluateExpression(va.expression);
        variableValues.getFirst().put(va.name.name, value);
    }

    private void addNewScope() {
        variableValues.addFirst(new HashMap<>());
    }

    private void removeFirstScope() {
        variableValues.removeFirst();
    }
}
