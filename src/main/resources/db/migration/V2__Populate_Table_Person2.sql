INSERT INTO pessoa (nome, documento, telefone) VALUES
	('FLAVIA BENIK', 'dsadasda', '41999115124'),
	('NIGRI BENIK', 'dsddas', '41999115124'),
	('JESS LUNA', 'dsdas', '41987765342');

INSERT INTO hospedagem (data_entrada, data_saida, adicional_veiculo) VALUES
	('2024-05-27T08:00:00', '2024-05-30T10:17:00', true),
	('2024-05-27T08:00:00', '2024-06-02T10:17:00', false),
	('2024-06-01T08:00:00', '2024-06-02T10:17:00', true);

INSERT INTO pessoa_hospedagem (id_pessoa, id_hospedagem) VALUES
	(1,1),
	(2,2),
	(3,3);