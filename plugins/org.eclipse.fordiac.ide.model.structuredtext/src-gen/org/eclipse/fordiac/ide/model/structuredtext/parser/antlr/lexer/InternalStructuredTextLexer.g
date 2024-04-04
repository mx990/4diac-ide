/*
 * generated by Xtext 2.25.0
 */
lexer grammar InternalStructuredTextLexer;

@header {
package org.eclipse.fordiac.ide.model.structuredtext.parser.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

LDATE_AND_TIME : ('L'|'l')('D'|'d')('A'|'a')('T'|'t')('E'|'e')'_'('A'|'a')('N'|'n')('D'|'d')'_'('T'|'t')('I'|'i')('M'|'m')('E'|'e');

DATE_AND_TIME : ('D'|'d')('A'|'a')('T'|'t')('E'|'e')'_'('A'|'a')('N'|'n')('D'|'d')'_'('T'|'t')('I'|'i')('M'|'m')('E'|'e');

LTIME_OF_DAY : ('L'|'l')('T'|'t')('I'|'i')('M'|'m')('E'|'e')'_'('O'|'o')('F'|'f')'_'('D'|'d')('A'|'a')('Y'|'y');

TIME_OF_DAY : ('T'|'t')('I'|'i')('M'|'m')('E'|'e')'_'('O'|'o')('F'|'f')'_'('D'|'d')('A'|'a')('Y'|'y');

END_REPEAT : ('E'|'e')('N'|'n')('D'|'d')'_'('R'|'r')('E'|'e')('P'|'p')('E'|'e')('A'|'a')('T'|'t');

END_WHILE : ('E'|'e')('N'|'n')('D'|'d')'_'('W'|'w')('H'|'h')('I'|'i')('L'|'l')('E'|'e');

CONSTANT : ('C'|'c')('O'|'o')('N'|'n')('S'|'s')('T'|'t')('A'|'a')('N'|'n')('T'|'t');

CONTINUE : ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('I'|'i')('N'|'n')('U'|'u')('E'|'e');

END_CASE : ('E'|'e')('N'|'n')('D'|'d')'_'('C'|'c')('A'|'a')('S'|'s')('E'|'e');

END_FOR : ('E'|'e')('N'|'n')('D'|'d')'_'('F'|'f')('O'|'o')('R'|'r');

END_VAR : ('E'|'e')('N'|'n')('D'|'d')'_'('V'|'v')('A'|'a')('R'|'r');

WSTRING : ('W'|'w')('S'|'s')('T'|'t')('R'|'r')('I'|'i')('N'|'n')('G'|'g');

END_IF : ('E'|'e')('N'|'n')('D'|'d')'_'('I'|'i')('F'|'f');

REPEAT : ('R'|'r')('E'|'e')('P'|'p')('E'|'e')('A'|'a')('T'|'t');

RETURN : ('R'|'r')('E'|'e')('T'|'t')('U'|'u')('R'|'r')('N'|'n');

STRING : ('S'|'s')('T'|'t')('R'|'r')('I'|'i')('N'|'n')('G'|'g');

ARRAY : ('A'|'a')('R'|'r')('R'|'r')('A'|'a')('Y'|'y');

DWORD : ('D'|'d')('W'|'w')('O'|'o')('R'|'r')('D'|'d');

ELSIF : ('E'|'e')('L'|'l')('S'|'s')('I'|'i')('F'|'f');

FALSE : ('F'|'f')('A'|'a')('L'|'l')('S'|'s')('E'|'e');

LDATE : ('L'|'l')('D'|'d')('A'|'a')('T'|'t')('E'|'e');

LREAL : ('L'|'l')('R'|'r')('E'|'e')('A'|'a')('L'|'l');

LTIME : ('L'|'l')('T'|'t')('I'|'i')('M'|'m')('E'|'e');

LWORD : ('L'|'l')('W'|'w')('O'|'o')('R'|'r')('D'|'d');

SUPER : ('S'|'s')('U'|'u')('P'|'p')('E'|'e')('R'|'r');

UDINT : ('U'|'u')('D'|'d')('I'|'i')('N'|'n')('T'|'t');

ULINT : ('U'|'u')('L'|'l')('I'|'i')('N'|'n')('T'|'t');

UNTIL : ('U'|'u')('N'|'n')('T'|'t')('I'|'i')('L'|'l');

USINT : ('U'|'u')('S'|'s')('I'|'i')('N'|'n')('T'|'t');

WCHAR : ('W'|'w')('C'|'c')('H'|'h')('A'|'a')('R'|'r');

WHILE : ('W'|'w')('H'|'h')('I'|'i')('L'|'l')('E'|'e');

BOOL : ('B'|'b')('O'|'o')('O'|'o')('L'|'l');

BYTE : ('B'|'b')('Y'|'y')('T'|'t')('E'|'e');

CASE : ('C'|'c')('A'|'a')('S'|'s')('E'|'e');

CHAR : ('C'|'c')('H'|'h')('A'|'a')('R'|'r');

DATE : ('D'|'d')('A'|'a')('T'|'t')('E'|'e');

DINT : ('D'|'d')('I'|'i')('N'|'n')('T'|'t');

ELSE : ('E'|'e')('L'|'l')('S'|'s')('E'|'e');

EXIT : ('E'|'e')('X'|'x')('I'|'i')('T'|'t');

LINT : ('L'|'l')('I'|'i')('N'|'n')('T'|'t');

LTOD : ('L'|'l')('T'|'t')('O'|'o')('D'|'d');

REAL : ('R'|'r')('E'|'e')('A'|'a')('L'|'l');

SINT : ('S'|'s')('I'|'i')('N'|'n')('T'|'t');

THEN : ('T'|'t')('H'|'h')('E'|'e')('N'|'n');

TIME : ('T'|'t')('I'|'i')('M'|'m')('E'|'e');

TRUE : ('T'|'t')('R'|'r')('U'|'u')('E'|'e');

UINT : ('U'|'u')('I'|'i')('N'|'n')('T'|'t');

WORD : ('W'|'w')('O'|'o')('R'|'r')('D'|'d');

B : '.''%'('B'|'b');

D : '.''%'('D'|'d');

W : '.''%'('W'|'w');

X : '.''%'('X'|'x');

AND : ('A'|'a')('N'|'n')('D'|'d');

FOR : ('F'|'f')('O'|'o')('R'|'r');

INT : ('I'|'i')('N'|'n')('T'|'t');

MOD : ('M'|'m')('O'|'o')('D'|'d');

NOT : ('N'|'n')('O'|'o')('T'|'t');

TOD : ('T'|'t')('O'|'o')('D'|'d');

VAR : ('V'|'v')('A'|'a')('R'|'r');

XOR : ('X'|'x')('O'|'o')('R'|'r');

AsteriskAsterisk : '*''*';

FullStopFullStop : '.''.';

ColonEqualsSign : ':''=';

LessThanSignEqualsSign : '<''=';

LessThanSignGreaterThanSign : '<''>';

EqualsSignGreaterThanSign : '=''>';

GreaterThanSignEqualsSign : '>''=';

AT : ('A'|'a')('T'|'t');

BY : ('B'|'b')('Y'|'y');

DO : ('D'|'d')('O'|'o');

DT : ('D'|'d')('T'|'t');

IF : ('I'|'i')('F'|'f');

LT : ('L'|'l')('T'|'t');

OF : ('O'|'o')('F'|'f');

OR : ('O'|'o')('R'|'r');

TO : ('T'|'t')('O'|'o');

NumberSign : '#';

Ampersand : '&';

LeftParenthesis : '(';

RightParenthesis : ')';

Asterisk : '*';

PlusSign : '+';

Comma : ',';

HyphenMinus : '-';

FullStop : '.';

Solidus : '/';

Colon : ':';

Semicolon : ';';

LessThanSign : '<';

EqualsSign : '=';

GreaterThanSign : '>';

E : ('E'|'e');

T : ('T'|'t');

LeftSquareBracket : '[';

RightSquareBracket : ']';

fragment RULE_LETTER : ('a'..'z'|'A'..'Z'|'_');

fragment RULE_DIGIT : '0'..'9';

fragment RULE_BIT : '0'..'1';

fragment RULE_OCTAL_DIGIT : '0'..'7';

fragment RULE_HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F');

RULE_TIMEOFDAY : ('L'|'l')? ('T'|'t') (('I'|'i') ('M'|'m') ('E'|'e') '_')? ('O'|'o') (('F'|'f') '_')? ('D'|'d') (('A'|'a') ('Y'|'y'))? '#' RULE_UNSIGNED_INT ':' RULE_UNSIGNED_INT ':' RULE_UNSIGNED_INT ('.' RULE_UNSIGNED_INT)?;

RULE_TIME : ('L'|'l')? ('T'|'t') (('I'|'i') ('M'|'m') ('E'|'e'))? '#' ('+'|'-')? RULE_UNSIGNED_INT ('.' RULE_UNSIGNED_INT)? (('D'|'d')|('H'|'h')|('M'|'m')|('S'|'s')|('M'|'m') ('S'|'s')|('U'|'u') ('S'|'s')|('N'|'n') ('S'|'s')) ('_'? RULE_UNSIGNED_INT ('.' RULE_UNSIGNED_INT)? (('D'|'d')|('H'|'h')|('M'|'m')|('S'|'s')|('M'|'m') ('S'|'s')|('U'|'u') ('S'|'s')|('N'|'n') ('S'|'s')))*;

RULE_DATETIME : ('L'|'l')? ('D'|'d') (('A'|'a') ('T'|'t') ('E'|'e') '_' ('A'|'a') ('N'|'n') ('D'|'d') '_')? ('T'|'t') (('I'|'i') ('M'|'m') ('E'|'e'))? '#' RULE_UNSIGNED_INT '-' RULE_UNSIGNED_INT '-' RULE_UNSIGNED_INT '-' RULE_UNSIGNED_INT ':' RULE_UNSIGNED_INT ':' RULE_UNSIGNED_INT ('.' RULE_UNSIGNED_INT)?;

RULE_DATE : ('L'|'l')? ('D'|'d') (('A'|'a') ('T'|'t') ('E'|'e'))? '#' RULE_UNSIGNED_INT '-' RULE_UNSIGNED_INT '-' RULE_UNSIGNED_INT ('.' RULE_UNSIGNED_INT)?;

RULE_ID : RULE_LETTER (RULE_LETTER|RULE_DIGIT)*;

RULE_BINARY_INT : '2#' ('_'? RULE_BIT)+;

RULE_OCTAL_INT : '8#' ('_'? RULE_OCTAL_DIGIT)+;

RULE_HEX_INT : '16#' ('_'? RULE_HEX_DIGIT)+;

RULE_UNSIGNED_INT : RULE_DIGIT ('_'? RULE_DIGIT)*;

RULE_S_BYTE_CHAR_STR : '\'' RULE_S_BYTE_CHAR_VALUE* '\'';

RULE_D_BYTE_CHAR_STR : '"' RULE_D_BYTE_CHAR_VALUE* '"';

fragment RULE_S_BYTE_CHAR_VALUE : (RULE_COMMON_CHAR_VALUE|'$\''|'"'|'$' RULE_HEX_DIGIT RULE_HEX_DIGIT);

fragment RULE_D_BYTE_CHAR_VALUE : (RULE_COMMON_CHAR_VALUE|'\''|'$"'|'$' RULE_HEX_DIGIT RULE_HEX_DIGIT RULE_HEX_DIGIT RULE_HEX_DIGIT);

fragment RULE_COMMON_CHAR_VALUE : (' '|'!'|'#'|'%'|'&'|'('..'/'|'0'..'9'|':'..'@'|'A'..'Z'|'['..'`'|'a'..'z'|'{'..'~'|'$$'|'$L'|'$N'|'$P'|'$R'|'$T');

RULE_ML_COMMENT : ('/*' ( options {greedy=false;} : . )*'*/'|'(*' ( options {greedy=false;} : . )*'*)');

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;