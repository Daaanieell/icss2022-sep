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




//--- PARSER: ---
stylesheet: content EOF | EOF;
content: (variable_declaration | rule)*;

//variables
variable_declaration: CAPITAL_IDENT ASSIGNMENT_OPERATOR value SEMICOLON;

//css rules
rule: (LOWER_IDENT | ID_IDENT | CLASS_IDENT) OPEN_BRACE body CLOSE_BRACE;

body: declaration+;
declaration: property (expression | value) SEMICOLON;

//TODO: this is mostly dupe of factor..
value: LOWER_IDENT | CAPITAL_IDENT | COLOR | PIXELSIZE | PERCENTAGE | SCALAR | TRUE | FALSE;
property: LOWER_IDENT COLON;

//expressiosn
expression: term (add_op term)*;
term: factor (mult_op factor)*;
factor: LOWER_IDENT | CAPITAL_IDENT | COLOR | PIXELSIZE | PERCENTAGE | SCALAR;

add_op: '+' | '-';
mult_op: '*' | '/';

//TODO continue oen level 2/3


