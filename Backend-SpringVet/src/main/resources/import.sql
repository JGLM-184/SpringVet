INSERT INTO tutor (id, nome, cpf, telefone, email) VALUES (1, 'João', '12345678910', '11900000001', 'joao@gmail.com');
INSERT INTO tutor (id, nome, cpf, telefone, email) VALUES (2, 'Amanda', '109876543210', '11900000002', 'amanda@gmail.com');
INSERT INTO tutor (id, nome, cpf, telefone, email) VALUES (3, 'Andrea', '12312312344', '11900000003', 'andrea@gmail.com');
INSERT INTO tutor (id, nome, cpf, telefone, email) VALUES (4, 'Daniel', '45645645677', '11900000004', 'daniel@gmail.com');

INSERT INTO tutor (id, nome, cpf, telefone, email) VALUES (5, 'Teste', '0000011111', '11900000004', 'teste@gmail.com');


INSERT INTO animais (id, nome, especie, raca, cor, sexo, nasc, castrado, tutor_id) VALUES (1, 'Kiko', 'Gato', 'Vira lata', 'Frajola', 'Masculino', '2019-01-05', false, 3);
INSERT INTO animais (id, nome, especie, raca, cor, sexo, nasc, castrado, tutor_id) VALUES (2, 'Nenem', 'Cachorro', 'Pinscher', 'Branco/Marrom', 'Masculino', '2019-02-08', true, 1);
INSERT INTO animais (id, nome, especie, raca, cor, sexo, nasc, castrado, tutor_id) VALUES (3, 'Spoc', 'Cachorro', 'Labrador', 'Caramelo', 'Masculino', '2020-05-10', true, 2);
INSERT INTO animais (id, nome, especie, raca, cor, sexo, nasc, castrado, tutor_id) VALUES (4, 'Ozi', 'Cachorro', 'Beagle', 'Tricolor', 'Masculino', '2020-08-16', true, 4);

INSERT INTO veterinarios( id, nome, crmv, especialidade, telefone, email, ativo) VALUES (1, 'Fabiana', '123654', 'Geral', '11900000005', 'fabiana@gmail.com', true);
INSERT INTO veterinarios( id, nome, crmv, especialidade, telefone, email, ativo) VALUES (2, 'Geraldo', '164686', 'Cardiologia', '11900000006', 'geraldo@gmail.com', true);
