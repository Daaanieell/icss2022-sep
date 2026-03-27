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
        addNewScope();
        walk(ast.root, null);
        removeFirstScope();
    }

    //TODO een betere naamm..
    private void walk(ASTNode node, ASTNode parent) {
        if (node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause) addNewScope();

        for (ASTNode child : node.getChildren()) {

            //zet de variable in de huidige scope
            if (child instanceof VariableAssignment)
                addVariableToScope((VariableAssignment) child);

            //als een variableassigment de parent is, dan moet de reference niet gelezen worden als een echte reference
            //dit checkt dus of een variabelreference wel een reference is
            if (!(parent instanceof VariableAssignment) && child instanceof VariableReference)
                checkVariableReference((VariableReference) child);
            walk(child, node);
        }

        if (node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause) removeFirstScope();
    }

    private void checkExpression(ASTNode expr) {
        if (expr instanceof VariableReference) {
            checkVariableReference((VariableReference) expr);
        }
        for (ASTNode child : expr.getChildren()) {
            checkExpression(child);
        }
    }

    // ------------------- helper functies -------------------

//    private void isVariableInScope(VariableAssignment variableAssignment) {
//        if (searchVariable(variableAssignment.name.name)) {
//            variableAssignment.setError(variableAssignment.name + " bestaat niet");
//        }
//    }

    private void checkVariableReference(VariableReference ref) {
        if (!searchVariable(ref.name)) {
            ref.setError(ref.name + " bestaat niet");
        }
    }
    private void addVariableToScope(VariableAssignment variableAssignment) {
        HashMap map = getFirstScope();
        String variableName = variableAssignment.name.name;
        map.put(variableName, variableAssignment);
    }

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
