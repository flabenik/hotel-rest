INSERT INTO pessoa (nome, documento, telefone) VALUES
	('FLAVIA BENIK', '92516345054', '41999115124'),
	('NIGRI BENIK', '19287376093', '41999115125'),
	('JESS LUNA', '82862727067', '41987765342'),
	('KEANU REEVES', '25773634007', '41999999999'),
	('CARRIE-ANNE MOSS', '67623620010', '41999999988');


INSERT INTO hospedagem (data_entrada, data_saida, adicional_veiculo, valor_estadia, id_pessoa) VALUES
	('2024-05-27T08:00:00', '2024-05-30T10:17:00', true, '540',1),
	('2024-05-27T08:00:00', '2024-06-02T10:17:00', false,'900',2),
	('2024-06-01T08:00:00', '2024-06-02T10:17:00', true, '340',3),
	('2024-06-02T08:00:00', '2024-06-05T17:00:00', false, '750',4),
	('2024-06-02T08:00:00', '2024-06-05T17:00:00', true, '830',5),
	('2024-06-02T08:00:00', '2024-06-05T17:00:00', true, '830',1);

INSERT INTO pessoa_hospedagem (id_pessoa, id_hospedagem) VALUES
	(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5),
	(1,6);