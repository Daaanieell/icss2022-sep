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
            //waardes opslaan van variabelen
            if (child instanceof VariableAssignment va)
                addVariableToScope(va);

            //checken van if clause condities
            if (child instanceof IfClause ifClause)
                transformIfClause(ifClause, node);

            checkNode(child, node);
        }

        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            removeFirstScope();
    }

    private void transformIfClause(IfClause ifClause, ASTNode parent) {
        BoolLiteral condition = (BoolLiteral) evaluateExpression(ifClause.conditionalExpression);

        if (condition.value) { //alleen de if is true
            for (ASTNode child : new ArrayList<>(ifClause.body)) {
                parent.addChild(child);

                //als een ifclause gevonden is, check die
                if (child instanceof IfClause) transformIfClause((IfClause) child, parent);
            }
        } else if (ifClause.elseClause != null) { //alleen de else is true
            for (ASTNode child : new ArrayList<>(ifClause.elseClause.body)) {
                parent.addChild(child);

                //zelfde hier
                if (child instanceof IfClause) transformIfClause((IfClause) child, parent);
            }
        } else if (ifClause.elseClause == null) { // er is geen if of else en de conditie is altijd false
            parent.removeChild(ifClause);
        }
    }

    private void transformExpression (Expression expression) {
        
    }




    // ------------------- helper functies -------------------

    private Literal evaluateExpression(Expression expression) {
        if (expression instanceof ColorLiteral)      return (ColorLiteral) expression;
        if (expression instanceof PixelLiteral)      return (PixelLiteral) expression;
        if (expression instanceof PercentageLiteral) return (PercentageLiteral) expression;
        if (expression instanceof ScalarLiteral)     return (ScalarLiteral) expression;
        if (expression instanceof BoolLiteral)       return (BoolLiteral) expression;

        if (expression instanceof VariableReference) {
            return getLiteralFromScope(((VariableReference) expression).name);
        }

        return null;
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
        Literal value = evaluateExpression((Expression) va.expression);
        variableValues.getFirst().put(va.name.name, value);
    }

    private void addNewScope() {
        variableValues.addFirst(new HashMap<>());
    }

    private void removeFirstScope() {
        variableValues.removeFirst();
    }
}
