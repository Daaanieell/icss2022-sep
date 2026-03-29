package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        checkNode(ast.root, null);
    }

    private void checkNode(ASTNode node, ASTNode parent) {
        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            addNewScope();

        for (ASTNode child : node.getChildren()) {
            //zet de variable in de huidige scope
            if (child instanceof VariableAssignment)
                addVariableToScope((VariableAssignment) child);

            //als een variableassigment de parent is, dan moet de reference niet gelezen worden als een echte reference
            //dit checkt dus of een variablereference wel een reference is
            if (!(parent instanceof VariableAssignment) && child instanceof VariableReference)
                checkVariableReference((VariableReference) child);

            //checkt of kleuren in een expression zitten, controleert variabelen en literals
            if (child instanceof Operation) checkNoColorInOperation((Operation) child);

            //todo bool checks in een if statement
            if (child instanceof IfClause) checkIfStatementCondition((IfClause) child);

            checkNode(child, node);
        }

        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            addNewScope();
    }

    //checkt of een if statement een bool conditie heeft
    private void checkIfStatementCondition(IfClause ifClause) {
        //conditie/variabelen uitlezen
        ExpressionType condition = getExpressionType(ifClause.conditionalExpression);

        if (condition != ExpressionType.BOOL) {
            ifClause.setError("condition is not boolean: " + condition);
        }

        //TODO mogen expressions?? check ch05
    }

    private void checkNoColorInOperation(Operation operation) {
        //de expressiontype wordt uit de linker en rechterkant gehaald
        //variable reference wordt dus ook omgezet naar een expression type
        ExpressionType lhs = getExpressionType(operation.lhs);
        ExpressionType rhs = getExpressionType(operation.rhs);

        //basecase, kleur gevonden of er wordt niks gevonden = geen error
        if (lhs == ExpressionType.COLOR || rhs == ExpressionType.COLOR) {
            operation.setError("kleur in operatie");
        }

        //recursie
        if (operation.lhs instanceof Operation) checkNoColorInOperation((Operation) operation.lhs);
        if (operation.rhs instanceof Operation) checkNoColorInOperation((Operation) operation.rhs);
    }

    // ------------------- helper functies -------------------

    //expressiontype uit een waarde halen, hiermee kunnen checks uitgevoerd worden op een waarde
    private ExpressionType getExpressionType(Expression expression) {
        if (expression instanceof ColorLiteral)     return ExpressionType.COLOR;
        if (expression instanceof PixelLiteral)     return ExpressionType.PIXEL;
        if (expression instanceof PercentageLiteral) return ExpressionType.PERCENTAGE;
        if (expression instanceof ScalarLiteral)    return ExpressionType.SCALAR;
        if (expression instanceof BoolLiteral)      return ExpressionType.BOOL;

        if (expression instanceof VariableReference) {
            VariableReference ref = (VariableReference) expression;
            return getTypeFromScope(ref.name);
        }

        return ExpressionType.UNDEFINED;
    }

    //een variabel expressiontype uit de scope halen
    //dit is nodig om te weten of de juiste expressiontype wel bestaat
    private ExpressionType getTypeFromScope(String name) {
        for (int i = 0; i < variableTypes.getSize(); i++) {
            if (variableTypes.get(i).containsKey(name)) {
                return variableTypes.get(i).get(name);
            }
        }
        return ExpressionType.UNDEFINED;
    }

    private void checkVariableReference(VariableReference ref) {
        if (!searchVariable(ref.name)) {
            ref.setError(ref.name + " bestaat niet");
        }
    }

    //zet een waarde in de huidige scope (de nieuwste hashmap die toegevoegd wordt)
    private void addVariableToScope(VariableAssignment variableAssignment) {
        HashMap<String, ExpressionType> map = getFirstScope();
        String name = variableAssignment.name.name;
        ExpressionType type = getExpressionType((Expression) variableAssignment.expression);
        map.put(name, type);
    }

    //check of een variable bestaat
    private boolean searchVariable(String key) {
        for (int i = 0; i < variableTypes.getSize(); i++) {
            if (variableTypes.get(i).containsKey(key)) return true;
        }
        return false;
    }

    private void addNewScope() {
        variableTypes.addFirst(new HashMap<>());
    }

    private void removeFirstScope() {
        variableTypes.removeFirst();
    }

    private HashMap getFirstScope() {
        return variableTypes.getFirst();
    }
}
