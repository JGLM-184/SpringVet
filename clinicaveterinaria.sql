-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30/04/2026 às 19:57
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `clinicaveterinaria`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `animais`
--

CREATE TABLE `animais` (
  `id` bigint(20) NOT NULL,
  `castrado` bit(1) NOT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `nasc` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `raca` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `tutor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `animais`
--

INSERT INTO `animais` (`id`, `castrado`, `cor`, `especie`, `nasc`, `nome`, `raca`, `sexo`, `tutor_id`) VALUES
(1, b'1', 'Caramelo', 'Cachorro', '2020-03-10', 'Thor', 'Labrador', 'Macho', 1),
(2, b'1', 'Preto', 'Gato', '2021-07-15', 'Mingau', 'SRD', 'Macho', 2),
(3, b'0', 'Branco', 'Cachorro', '2022-01-20', 'Luna', 'Shih Tzu', 'Fêmea', 3),
(4, b'1', 'Cinza', 'Gato', '2019-11-05', 'Nina', 'Persa', 'Fêmea', 4),
(5, b'1', 'Marrom', 'Cachorro', '2018-09-13', 'Bob', 'Beagle', 'Macho', 5),
(6, b'0', 'Preto/Branco', 'Cachorro', '2023-04-08', 'Pipoca', 'Border Collie', 'Fêmea', 6),
(7, b'1', 'Laranja', 'Gato', '2020-12-22', 'Simba', 'SRD', 'Macho', 1),
(8, b'1', 'Branco/Marrom', 'Cachorro', '2017-06-30', 'Meg', 'Poodle', 'Fêmea', 2),
(9, b'1', 'Cinza', 'Gato', '2022-08-11', 'Tom', 'British Shorthair', 'Macho', 7),
(10, b'0', 'Preto', 'Cachorro', '2023-02-20', 'Max', 'Doberman', 'Macho', 8),
(11, b'1', 'Branco', 'Coelho', '2024-01-15', 'Algodão', 'Lion Head', 'Fêmea', 9),
(12, b'0', 'Caramelo', 'Cachorro', '2021-06-10', 'Mel', 'Vira Lata', 'Fêmea', 10),
(13, b'1', 'Marrom/Branco', 'Cachorro', '2020-09-18', 'Tobby', 'Jack Russell', 'Macho', 11),
(14, b'1', 'Verde', 'Ave', '2022-03-09', 'Loro', 'Papagaio', 'Macho', 12),
(15, b'0', 'Cinza', 'Gato', '2025-01-05', 'Kiara', 'SRD', 'Fêmea', 8),
(16, b'1', 'Branco', 'Cachorro', '2019-12-12', 'Pandora', 'Maltês', 'Fêmea', 7),
(19, b'1', 'Caramelo', 'Cachorro', '2021-03-05', 'Spoc', 'Labrador', 'Macho', 15);

-- --------------------------------------------------------

--
-- Estrutura para tabela `consultas`
--

CREATE TABLE `consultas` (
  `id` bigint(20) NOT NULL,
  `data_criacao` date DEFAULT NULL,
  `data_hora` datetime(6) DEFAULT NULL,
  `forma_pagamento` varchar(255) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `paga` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  `animal_id` bigint(20) DEFAULT NULL,
  `veterinario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `consultas`
--

INSERT INTO `consultas` (`id`, `data_criacao`, `data_hora`, `forma_pagamento`, `motivo`, `observacao`, `paga`, `status`, `valor`, `animal_id`, `veterinario_id`) VALUES
(1, '2026-04-30', '2026-05-02 09:00:00.000000', 'PIX', 'Vacinação anual', 'Animal saudável', b'0', 'AGENDADA', 120.00, 1, 1),
(2, '2026-04-30', '2026-05-02 10:30:00.000000', 'Cartão de crédito', 'Coceira na pele', 'Irritação nas patas', b'0', 'AGENDADA', 180.00, 2, 3),
(3, '2026-04-30', '2026-05-03 14:00:00.000000', 'Dinheiro', 'Consulta de rotina', 'Primeira visita', b'0', 'AGENDADA', 100.00, 9, 6),
(4, '2026-04-30', '2026-05-04 16:00:00.000000', 'PIX', 'Dor ao andar', 'Suspeita muscular', b'0', 'AGENDADA', 220.00, 10, 7),
(5, '2026-04-20', '2026-04-22 11:00:00.000000', 'PIX', 'Exame cardíaco', 'Tudo normal', b'1', 'FINALIZADA', 250.00, 1, 2),
(6, '2026-04-18', '2026-04-19 15:30:00.000000', 'Cartão de débito', 'Otite', 'Medicação prescrita', b'1', 'FINALIZADA', 160.00, 8, 1),
(7, '2026-04-10', '2026-04-12 09:30:00.000000', 'PIX', 'Retorno pós-cirurgia', 'Boa recuperação', b'1', 'FINALIZADA', 200.00, 5, 4),
(8, '2026-04-14', '2026-04-15 13:00:00.000000', 'PIX', 'Consulta dermatológica', 'Tutor cancelou', b'0', 'CANCELADA', 180.00, 7, 3),
(9, '2026-04-11', '2026-04-13 17:00:00.000000', 'Dinheiro', 'Avaliação geral', 'Não compareceu', b'0', 'CANCELADA', 110.00, 6, 1),
(10, '2026-04-25', '2026-04-28 08:30:00.000000', 'PIX', 'Consulta não realizada', 'Paciente faltou', b'0', 'AGENDADA', 130.00, 11, 8),
(11, '2026-04-30', '2026-04-30 18:00:00.000000', 'PIX', 'Revisão pós-vacina', 'Retorno programado', b'1', 'FINALIZADA', 90.00, 1, 1),
(12, '2026-04-30', '2026-04-30 18:40:00.000000', 'Cartão de crédito', 'Consulta oftalmológica', 'Olhos lacrimejando', b'0', 'AGENDADA', 210.00, 12, 9),
(13, '2026-04-30', '2026-04-30 19:20:00.000000', 'PIX', 'Avaliação nutricional', 'Animal acima do peso', b'0', 'AGENDADA', 140.00, 13, 10),
(14, '2026-04-30', '2026-04-30 20:00:00.000000', 'Dinheiro', 'Exame ortopédico', 'Claudicação leve', b'0', 'AGENDADA', 260.00, 14, 6),
(15, '2026-04-30', '2026-04-30 20:40:00.000000', 'PIX', 'Consulta dermatológica', 'Queda de pelos', b'0', 'AGENDADA', 190.00, 15, 7),
(16, '2026-04-30', '2026-04-30 21:20:00.000000', 'Cartão de débito', 'Vacinação múltipla', 'Carteira em dia', b'0', 'AGENDADA', 130.00, 16, 8),
(17, '2026-04-30', '2026-04-29 09:00:00.000000', 'PIX', 'Check-up anual', 'Exames preventivos', b'0', 'AGENDADA', 170.00, 9, 9),
(18, '2026-04-30', '2026-04-29 14:30:00.000000', 'Cartão de crédito', 'Retorno clínico', 'Melhora observada', b'0', 'AGENDADA', 100.00, 10, 10),
(19, '2026-04-30', '2026-04-28 16:00:00.000000', 'PIX', 'Avaliação cardíaca', 'Sopro leve', b'0', 'AGENDADA', 280.00, 11, 6),
(20, '2026-04-30', '2026-05-09 11:00:00.000000', 'Dinheiro', 'Consulta geral', 'Sem alterações aparentes', b'0', 'AGENDADA', 110.00, 12, 7),
(21, '2026-04-30', '2026-05-10 13:30:00.000000', 'PIX', 'Retorno dermatológico', 'Coceira reduziu', b'0', 'AGENDADA', 150.00, 13, 8),
(22, '2026-04-30', '2026-05-10 15:30:00.000000', 'Cartão de crédito', 'Dor articular', 'Animal idoso', b'0', 'AGENDADA', 240.00, 14, 9),
(24, '2026-04-30', '2026-05-03 19:30:00.000000', 'PIX', 'Consulta de rotina', 'Animal com comportamento normal', b'0', 'CANCELADA', 75.00, 19, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tutor`
--

CREATE TABLE `tutor` (
  `id` bigint(20) NOT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tutor`
--

INSERT INTO `tutor` (`id`, `cpf`, `email`, `nome`, `telefone`) VALUES
(1, '11111111111', 'carlos.silva@email.com', 'Carlos Henrique Silva', '11990000001'),
(2, '22222222222', 'mariana.lima@email.com', 'Mariana Lima Costa', '11990000002'),
(3, '33333333333', 'rafael.souza@email.com', 'Rafael Souza Martins', '11990000003'),
(4, '44444444444', 'patricia.alves@email.com', 'Patricia Alves Rocha', '11990000004'),
(5, '55555555555', 'fernanda.gomes@email.com', 'Fernanda Gomes Ribeiro', '11990000005'),
(6, '66666666666', 'lucas.melo@email.com', 'Lucas Melo Ferreira', '11990000006'),
(7, '77777777777', 'bruna.nunes@email.com', 'Bruna Nunes Castro', '11990000007'),
(8, '88888888888', 'diego.araujo@email.com', 'Diego Araújo Lima', '11990000008'),
(9, '99999999999', 'camila.pires@email.com', 'Camila Pires Souza', '11990000009'),
(10, '10101010101', 'rodrigo.moraes@email.com', 'Rodrigo Moraes Santos', '11990000010'),
(11, '11122233344', 'aline.teixeira@email.com', 'Aline Teixeira Ramos', '11990000011'),
(12, '55544433322', 'gustavo.leal@email.com', 'Gustavo Leal Pereira', '11990000012'),
(15, '12365478910', 'ricardo.almeida.teste@gmail.com', 'Ricardo Almeida', '11900000009');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `role`, `senha`) VALUES
(1, 'admin@springvet.com', 0, '$2a$10$vjQMQXd1xhDfqFCEG0YmceZzziWwgsfg39xdXJXy0m7vbPlu57GU2'),
(2, 'recepcao@springvet.com', 1, '$2a$10$vjQMQXd1xhDfqFCEG0YmceZzziWwgsfg39xdXJXy0m7vbPlu57GU2');

-- --------------------------------------------------------

--
-- Estrutura para tabela `veterinarios`
--

CREATE TABLE `veterinarios` (
  `id` bigint(20) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `crmv` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `especialidade` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `veterinarios`
--

INSERT INTO `veterinarios` (`id`, `ativo`, `crmv`, `email`, `especialidade`, `nome`, `telefone`) VALUES
(1, b'1', 'SP10001', 'ana.vet@email.com', 'Clínico Geral', 'Dra. Ana Beatriz', '11980000001'),
(2, b'1', 'SP10002', 'marcos.vet@email.com', 'Cardiologia', 'Dr. Marcos Vinicius', '11980000002'),
(3, b'1', 'SP10003', 'juliana.vet@email.com', 'Dermatologia', 'Dra. Juliana Prado', '11980000003'),
(4, b'1', 'SP10004', 'felipe.vet@email.com', 'Ortopedia', 'Dr. Felipe Andrade', '11980000004'),
(5, b'0', 'SP10005', 'renata.vet@email.com', 'Oftalmologia', 'Dra. Renata Torres', '11980000005'),
(6, b'1', 'SP10006', 'paula.vet@email.com', 'Neurologia', 'Dra. Paula Mendes', '11980000006'),
(7, b'1', 'SP10007', 'ricardo.vet@email.com', 'Odontologia', 'Dr. Ricardo Neves', '11980000007'),
(8, b'1', 'SP10008', 'samara.vet@email.com', 'Endocrinologia', 'Dra. Samara Lopes', '11980000008'),
(9, b'1', 'SP10009', 'bruno.vet@email.com', 'Silvestres', 'Dr. Bruno Tavares', '11980000009'),
(10, b'1', 'SP10010', 'carla.vet@email.com', 'Anestesiologia', 'Dra. Carla Figueiredo', '11980000010');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `animais`
--
ALTER TABLE `animais`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4yoruwfs4w8a7ihqsf2jgrd3f` (`tutor_id`);

--
-- Índices de tabela `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKflgco1wqqgtg9nqfgsdhi2l1k` (`animal_id`),
  ADD KEY `FKie02mmq1fkts2aibvpaxc4qv7` (`veterinario_id`);

--
-- Índices de tabela `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `veterinarios`
--
ALTER TABLE `veterinarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `animais`
--
ALTER TABLE `animais`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de tabela `consultas`
--
ALTER TABLE `consultas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de tabela `tutor`
--
ALTER TABLE `tutor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `veterinarios`
--
ALTER TABLE `veterinarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `animais`
--
ALTER TABLE `animais`
  ADD CONSTRAINT `FK4yoruwfs4w8a7ihqsf2jgrd3f` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`id`);

--
-- Restrições para tabelas `consultas`
--
ALTER TABLE `consultas`
  ADD CONSTRAINT `FKflgco1wqqgtg9nqfgsdhi2l1k` FOREIGN KEY (`animal_id`) REFERENCES `animais` (`id`),
  ADD CONSTRAINT `FKie02mmq1fkts2aibvpaxc4qv7` FOREIGN KEY (`veterinario_id`) REFERENCES `veterinarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
