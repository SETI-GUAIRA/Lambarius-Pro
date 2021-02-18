INSERT INTO permissao VALUES (1,'CAD_USUARIO');
INSERT INTO permissao VALUES (2,'CAD_ILHA');
INSERT INTO permissao VALUES (3,'CAD_PORTO');
INSERT INTO permissao VALUES (4,'CAD_PESCADOR');
INSERT INTO permissao VALUES (5,'CAD_ASSOCIACAO');


INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 2);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 3);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 5);


INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 2);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 3);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 5);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
	(SELECT codigo FROM usuario WHERE email = 'benjamin@guaira.pr.gov.br'), 1);

