grammar SuperKotlin;

program: statement* EOF;

statement
    : DEC_LABEL variable '=' expr ';'       # Declaration
    | variable '=' expr ';'                 # Assigment
    | 'print' '(' expr ')' ';'              # Print
    | 'if' '(' expr ')' block
        ('else' 'if' '(' expr ')' block)*
        ('else' block)?                     # If
    | 'while' '(' expr ')' block            # Loop
    | block                                 # NestedBlock
    ;

block: '{' statement* '}';

expr
    : boolExpr
    | numberExpr
    | strExpr
    | '(' expr ')'
    ;

boolExpr
    : BOOL
    | variable
    | boolExpr '||' boolExpr
    | boolExpr '&&' boolExpr
    | '!' boolExpr
    | boolExpr ('=='|'!=') boolExpr
    | strExpr ('=='|'!=') strExpr
    | numberExpr ('=='|'!='|'<'|'>'|'<='|'>=') numberExpr
    ;

numberExpr
    : NUMBER
    | variable
    | numberExpr ('*'|'/'|'+'|'-'|'^') numberExpr
    | '-' numberExpr
    | 'round' '(' numberExpr ')'
    ;

strExpr
    : STRING
    | variable
    | strExpr '+' expr
    ;

variable: VAR_NAME;

VAR_NAME: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+ ('.' [0-9]*)?;
STRING: '"' (~["\\] | '\\' .)* '"';
BOOL: 'true' | 'false';
DEC_LABEL: 'var' | 'val';

WS: [ \t\r\n]+ -> skip;