grammar Expr;

prog	:	stat+ ;

stat	:	expr NEWLINE		# printExp
	|	ID '=' expr NEWLINE	# assign
	|	NEWLINE			# blank
	;

expr	:	expr op=(MUL|DIV) expr	# MulDiv
	|	expr op=(ADD|SUB) expr	# AddSub
	|	INT			# int
	|	ID			# id
	|	'(' expr ')'		# parens
	;

ADD	:	'+' ;
SUB	:	'-' ;
MUL	:	'*' ;
DIV	:	'/' ;
ID	:	[a-zA-Z]+ ;
INT	:	[0-9]+ ;
NEWLINE	:	'\r' ? '\n' ;
WS	:	[ \t]+ -> skip;

