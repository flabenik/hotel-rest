CREATE TABLE IF NOT EXISTS public.pessoa
(
	id serial CONSTRAINT pk_pessoa_id PRIMARY KEY,
    nome character varying(80) NOT NULL,
    documento character varying(80) NOT NULL,
    telefone character varying(80) NOT NULL
);


CREATE TABLE IF NOT EXISTS public.hospedagem
(
	id serial CONSTRAINT pk_hospedagem_id PRIMARY KEY,
    data_entrada TIMESTAMP NOT NULL,
    data_saida TIMESTAMP NOT NULL,
    adicional_veiculo BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS public.pessoa_hospedagem
(
    id serial CONSTRAINT pk_pessoa_hospedagem_id PRIMARY KEY,
	id_hospedagem  bigint constraint fk_codhospedagem REFERENCES hospedagem(id) ON DELETE restrict,
	id_pessoa  bigint constraint fk_codpessoa REFERENCES pessoa(id) ON DELETE restrict
);
