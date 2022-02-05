CREATE TABLE cliente (
	id INT(10) PRIMARY KEY AUTO_INCREMENT,
	nome varchar(255) NOT NULL,
	data_cadastro TIMESTAMP NOT NULL,
	ativo SMALLINT(1) NOT NULL DEFAULT 0,
	cgccpf VARCHAR(14) NOT NULL,
	email varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE contrato (
    id_Contrato INT(10) PRIMARY KEY AUTO_INCREMENT,
    cd_Produto INT(10) NOT NULL,
    vlr_Contrato DECIMAL(17,2) NOT NULL,
    qtd_Parcelas INT(3) NOT NULL,
    status_Cobranca varchar(3) NOT NULL,
    sit_Serasa varchar(3) NOT NULL,
    cd_Cli INT(10) NOT NULL,
    FOREIGN KEY (cd_Cli) REFERENCES cliente (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

