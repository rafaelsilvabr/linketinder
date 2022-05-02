--INSERT INTO dados_candidatos(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
--VALUES ('Fernando', 'Silva', '1990-12-02','fernandinho@gmail.com', '123.456.789-10', 'Brasil', '123.456-78', 'Um programador difirenciado', '123456'),
--	   ('Juliano', 'Resende', '2000-10-01','julianinho@gmail.com', '123.456.789-10', 'Brasil', '111-111-11', 'Um programador mediano', '654321');

--SELECT * FROM dados_candidatos;

--INSERT INTO competencias(nome)
--VALUES ('Java'),
--	('Python'),
--	('Groovy'),
--	('Spring');

--SELECT * FROM competencias;

--INSERT INTO competencias_candidatos(competencia, candidato)
--VALUES((SELECT id FROM competencias WHERE nome = 'Groovy'), (SELECT id FROM dados_candidatos WHERE email = 'fernandinho@gmail.com'));

--SELECT * FROM competencias_candidatos;

--INSERT INTO dados_empresas(nome, cnpj, email, descricao, pais, cep, senha)
--VALUES ('padaria do zé', '00000000', 'paezihosgostosos@gmail.com', 'Uma padaria para TIs', 'Brasil', '74855-888', '845187');

--SELECT * FROM dados_empresas;

--INSERT INTO vagas (nome, descricao, local_vaga)
--VALUES ('Dev FrontEnd Jr.', 'Buscamos um programador iniciante',(SELECT id FROM dados_empresas WHERE email = 'paezihosgostosos@gmail.com'));

--SELECT * FROM vagas;

--INSERT INTO competencias_vagas (competencia, vaga)
--VALUES ((SELECT id FROM competencias WHERE nome = 'Groovy'),(SELECT id FROM vagas WHERE nome = 'Dev FrontEnd Jr.' AND local_vaga = (SELECT id FROM dados_empresas WHERE email = 'paezihosgostosos@gmail.com')));

