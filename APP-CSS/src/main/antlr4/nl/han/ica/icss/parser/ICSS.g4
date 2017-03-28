grammar ICSS;

stylesheet: (constant | style_rule)*  EOF;
style_rule: selector block;
selector: IDENTIFIER? STRING;
block: '{' declaration* style_rule* '}';
declaration: STRING ':' value LINE_END;
value: SIZE | COLOUR | CONSTANT_NAME | value OPERATOR value;
constant: CONSTANT_NAME '=' value LINE_END;

OPERATOR: ('-' | '+' | '*' | '/');
IDENTIFIER: '.' | '#';
SIZE: [0-9]+ ('px' | '%');
STRING: (NUMBER | LETTER)+ '-'? (NUMBER | LETTER)*;
COLOUR: '#' [0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F];
NUMBER: [0-9];
LETTER: [a-zA-Z];
CONSTANT_NAME: '$' STRING;
LINE_END: ';';
WS: [ \t\r\n]+ -> skip;