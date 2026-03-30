package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class SubtractOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Subtract";
    }

    @Override
    public Literal calc(Literal lhs, Literal rhs) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral)
            return new PixelLiteral(((PixelLiteral) lhs).value - ((PixelLiteral) rhs).value);
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral)
            return new PercentageLiteral(((PercentageLiteral)lhs).value - ((PercentageLiteral) rhs).value);
        if (lhs instanceof ScalarLiteral && rhs instanceof ScalarLiteral)
            return new ScalarLiteral(((ScalarLiteral)lhs).value - ((ScalarLiteral) rhs).value);
        return null;
    }
}
