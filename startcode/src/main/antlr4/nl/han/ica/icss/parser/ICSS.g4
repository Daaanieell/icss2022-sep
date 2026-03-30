grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//toevoeging:
DIV: '/';


//--- PARSER: ---

stylesheet: (variable_assignment | stylerule)* EOF;

//variable
variable_assignment: CAPITAL_IDENT ASSIGNMENT_OPERATOR expression SEMICOLON;

//css rule
stylerule: (LOWER_IDENT | ID_IDENT | CLASS_IDENT) OPEN_BRACE (declaration | if_clause | variable_assignment)* CLOSE_BRACE;

declaration: property expression SEMICOLON;

property: LOWER_IDENT COLON;

//expressions
expression: term (add_op term)*;
term: factor (mult_op factor)*;
factor: LOWER_IDENT | CAPITAL_IDENT | COLOR | PIXELSIZE | PERCENTAGE | SCALAR | TRUE | FALSE;

add_op: PLUS | MIN;
mult_op: MUL | DIV;

//if statement
if_clause: IF BOX_BRACKET_OPEN (CAPITAL_IDENT | TRUE | FALSE) BOX_BRACKET_CLOSE OPEN_BRACE (declaration | if_clause | variable_assignment)* CLOSE_BRACE else_clause?;
else_clause: ELSE OPEN_BRACE (declaration | if_clause)* CLOSE_BRACE;