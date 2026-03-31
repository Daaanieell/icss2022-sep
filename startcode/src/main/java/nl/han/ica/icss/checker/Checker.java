package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        checkNode(ast.root, null);
    }

    private void checkNode(ASTNode node, ASTNode parent) {
        //scope toevoegen bij iets wat kinderen kan hebben
        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            addNewScope();

        for (ASTNode child : node.getChildren()) {
            //zet de variable in de huidige scope
            if (child instanceof VariableAssignment) {
                isPropertyValid((VariableAssignment) child);
                addVariableToScope((VariableAssignment) child);
            }

            //als een variableassigment de parent is, dan moet de reference niet gelezen worden als een echte reference
            //dit checkt dus of een variablereference wel een reference is
            if (!(parent instanceof VariableAssignment) && child instanceof VariableReference)
                checkVariableReference((VariableReference) child);

            //checkt of inhoud van rekensom wel mag (px + px en niet px + %)
            if (child instanceof Operation) checkOperationTypes((Operation) child);

            if (child instanceof IfClause) checkIfStatementCondition((IfClause) child);

            checkNode(child, node);
        }

        if (node instanceof Stylesheet || node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause)
            addNewScope();
    }

    public void isPropertyValid(VariableAssignment variableAssignment) {
        if (variableAssignment.expression instanceof ScalarLiteral)
            variableAssignment.setError("scalar is ongeldige type variabel");

    }

    //checkt of een if statement een bool conditie heeft

    //note: volgens ch05 was het niet nodig dat een expression geëvalueerd moest worden?
    // voor nu is het puur checken op bools of variabelen die bools zijn
    private void checkIfStatementCondition(IfClause ifClause) {
        //conditie/variabelen uitlezen
        ExpressionType condition = getExpressionType(ifClause.conditionalExpression);

        if (condition != ExpressionType.BOOL) {
            ifClause.setError("condition is not boolean: " + condition);
        }
    }
    private void checkOperationTypes(Operation op) {
        checkNoColorInOperation(op);
        checkAddSubtractTypes(op);
        checkMultiplyTypes(op);
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

    //zelfde als bij nocolorinoperation
    private void checkAddSubtractTypes(Operation operation) {
        ExpressionType lhs = getExpressionType(operation.lhs);
        ExpressionType rhs = getExpressionType(operation.rhs);

        if (operation instanceof AddOperation || operation instanceof SubtractOperation) {
            if (lhs == ExpressionType.PIXEL && rhs != ExpressionType.PIXEL
                    || lhs == ExpressionType.PERCENTAGE && rhs != ExpressionType.PERCENTAGE) {
                operation.setError("ongeldige optelwaardes, lhs: " + lhs + " en rhs: " + rhs);
            }
        }

        if (operation.lhs instanceof Operation) checkAddSubtractTypes((Operation) operation.lhs);
        if (operation.rhs instanceof Operation) checkAddSubtractTypes((Operation) operation.rhs);
    }


    //zelfde als bij nocolorinoperation
    private void checkMultiplyTypes(Operation operation) {
        ExpressionType lhs = getExpressionType(operation.lhs);
        ExpressionType rhs = getExpressionType(operation.rhs);

        if (operation instanceof MultiplyOperation) {
            boolean hasScalar = lhs == ExpressionType.SCALAR || rhs == ExpressionType.SCALAR;
            if (!hasScalar) operation.setError("vermenigvulden ontbreekt scalar, lhs: " + lhs + " en rhs: " + rhs);
        }

        if (operation.lhs instanceof Operation) checkMultiplyTypes((Operation) operation.lhs);
        if (operation.rhs instanceof Operation) checkMultiplyTypes((Operation) operation.rhs);
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

        //dit zet sommen om naar een expressiontype, de lhs is 'leidend' hierin
        if (expression instanceof Operation operation) {
            return getExpressionType(operation.lhs);
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
        didVariableTypeChange(variableAssignment);

        HashMap<String, ExpressionType> map = getFirstScope();
        String name = variableAssignment.name.name;
        ExpressionType type = getExpressionType((Expression) variableAssignment.expression);
        map.put(name, type);
    }


    //variabelen mogen niet van type veranderen: Var := 5px --X-> Var := 50%
    private void didVariableTypeChange(VariableAssignment variableAssignment) {
        String key = variableAssignment.name.name;

        if (!searchVariable(key)) return; // de variabele is nieuw

        ExpressionType previousType = getTypeFromScope(key);
        ExpressionType newType = getExpressionType( variableAssignment.expression);

        //vergelijken van expressiontype
        if (previousType != newType)
            variableAssignment.setError(
                    key + " krijgt een waarde met ongeldig type, vorige: " + previousType + ", nieuwe: " + newType
            );
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
