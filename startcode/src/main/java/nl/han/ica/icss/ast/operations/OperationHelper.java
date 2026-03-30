package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class OperationHelper {

    public int getValue(Literal literal) {
        if (literal instanceof PixelLiteral pixelLiteral)      return pixelLiteral.value;
        if (literal instanceof PercentageLiteral percentageLiteral) return percentageLiteral.value;
        if (literal instanceof ScalarLiteral scalarLiteral)      return scalarLiteral.value;
        throw new RuntimeException("literal klopt niet");
    }

}
