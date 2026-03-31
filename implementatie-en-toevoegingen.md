# Implementatie en toevoegingen

Hier wordt kort beschreven welke eisen (alle must's en sommige should's) geimplementeerd zijn en toevoegingen.

## Implementatie

Eisen:

- 4.1 Algemene eisen: alles
- 4.2 Parseren: alles
- 4.3 Checken: CH00, CH01, CH03, CH05 en CH06
- 4.4 Transformeren: alles
- 4.6 zie [toevoegingen](#toevoegingen)

## Toevoegingen

Hier wordt omschreven wat toegevoegd werd dat buiten de casus valt, samen met links naar de relevant bestanden.

- Variabelen mogen niet van type veranderen
    - Checker: [`startcode/src/main/java/nl/han/ica/icss/checker/Checker.java`](startcode/src/main/java/nl/han/ica/icss/checker/Checker.java)

- Deelsommen
    - Divide: [`startcode/src/main/java/nl/han/ica/icss/ast/operations/DivideOperation.java`](startcode/src/main/java/nl/han/ica/icss/ast/operations/DivideOperation.java)
    - Parser: [`startcode/src/main/java/nl/han/ica/icss/parser/ASTListener.java`](startcode/src/main/java/nl/han/ica/icss/parser/ASTListener.java)
    - Grammatica: [`startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4`](startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4)
