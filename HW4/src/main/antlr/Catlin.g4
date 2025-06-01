grammar Catlin;

program: statement* EOF;

statement
    : type=('var' | 'val') variable '=' expression ';'      # Declaration
    | variable '=' expression ';'                           # Assigment
    | 'print' '(' expression ')' ';'                        # Print
    | 'if' '(' expression ')' block ('else' block)?         # If
    | 'while' '(' expression ')' block                      # While
    | block                                                 # NestedBlock
    | statement LINE_COMMENT                                # Comment
    ;

block: '{' statement* '}';

expression
    : '(' expression ')'                            # Brackets
    | op=('!'|'-') expression                       # PrefixOp
    | expression op=('*'|'/') expression            # MulDivOp
    | expression op=('+'|'-') expression            # AddSubOp
    | expression op=('=='|'!=') expression          # EqualOp
    | expression op=('<'|'>'|'<='|'>=') expression  # CompOp
    | expression op=('||'|'&&') expression          # BoolOp
    | 'round' '(' expression ')'                    # Round
    | BOOL                                          # Bool
    | LONG                                          # Long
    | DOUBLE                                        # Double
    | STRING                                        # String
    | variable                                      # Var
    ;

variable: VAR_NAME;

VAR_NAME: [a-zA-Z_][a-zA-Z0-9_]*;
LONG: [0-9]+;
DOUBLE: [0-9]+ '.' [0-9]+;
STRING: '"' (~["\\] | '\\' .)* '"';
BOOL: 'true' | 'false';

LINE_COMMENT: '//' ~[\r\n]* -> skip;

WS: [ \t\r\n]+ -> skip;