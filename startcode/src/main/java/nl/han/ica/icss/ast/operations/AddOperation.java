package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class AddOperation extends Operation {
    private OperationHelper oh = new OperationHelper();

    @Override
    public String getNodeLabel() {
        return "Add";
    }

    @Override
    public Literal calc(Literal lhs, Literal rhs) {
        int lhsValue = oh.getValue(lhs);
        int rhsValue = oh.getValue(rhs);
        int sum = lhsValue + rhsValue;

        if (lhs instanceof  PixelLiteral || rhs instanceof PixelLiteral) //pixel
            return new PixelLiteral(sum);
        if (lhs instanceof  PercentageLiteral || rhs instanceof PercentageLiteral) //percentage
            return new PercentageLiteral(sum);
        if (lhs instanceof  ScalarLiteral || rhs instanceof ScalarLiteral) //scalar
            return new ScalarLiteral(sum);

        throw new RuntimeException("add calc broke with lhs: " + lhsValue + ", rhs: " + rhsValue);
    }
}
