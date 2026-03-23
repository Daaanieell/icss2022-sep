package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
         variableTypes = new HANLinkedList<>();
         checkStylesheet(ast.root);
    }

    private void checkStylesheet(Stylesheet node) {
        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : node.getChildren()) {
            if (child instanceof VariableAssignment) checkVariables((VariableAssignment) child);
            if (child instanceof Stylerule) checkStylerules((Stylerule) child);
        }
        variableTypes.removeFirst();
    }

    private void checkStylerules(Stylerule stylerule) {
//        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : stylerule.getChildren()) {
            if (child instanceof Declaration) checkDeclaration((Declaration) child);
            if (child instanceof IfClause) checkIfClause((IfClause) child);
        }
//        variableTypes.removeFirst();
    }

    private void checkDeclaration(Declaration declaration) {
        //TODO
        checkExpression(declaration.expression);


    }

    private void checkExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            if (!searchVariable(((VariableReference) expression).name)) {
                expression.setError("bestaat niet");
            }
        }
    }

    private void checkIfClause(IfClause ifClause) {
        //TODO
    }

    private void checkVariables(VariableAssignment variableAssignment) {
        HashMap map = variableTypes.getFirst();
        System.out.println(variableAssignment.getChildren());

        String variableName = variableAssignment.name.name;
        System.out.println("naam: " + variableName);

        map.put(variableName, variableAssignment);
    }

    private boolean searchVariable(String key) {
        for (int current = 0; current < variableTypes.getSize(); current++) {
            HashMap map = variableTypes.get(current);
            if (map.containsKey(key)) return true;
        }
        return false;
    }
}
