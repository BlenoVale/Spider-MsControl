
CREATE SCHEMA IF NOT EXISTS `SpiderMsControl` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `SpiderMsControl` ;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Perfil` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Funcionalidade` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(60) NOT NULL COMMENT '',
  `login` VARCHAR(45) NOT NULL COMMENT '',
  `senha` TEXT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Projeto` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `status` INT NOT NULL COMMENT '',
  `dataInicio` DATE NOT NULL COMMENT '',
  `dataInatividade` DATE NULL COMMENT '',
  `dataFim` DATE NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`acessa` (
  `dataDeInicio` DATE NULL COMMENT '',
  `Projeto_id` INT NOT NULL COMMENT '',
  `Usuario_id` INT NOT NULL COMMENT '',
  `Perfil_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`Projeto_id`, `Usuario_id`, `Perfil_id`)  COMMENT '',
  INDEX `fk_acessa_Usuario1_idx` (`Usuario_id` ASC)  COMMENT '',
  INDEX `fk_acessa_Perfil1_idx` (`Perfil_id` ASC)  COMMENT '',
  CONSTRAINT `fk_acessa_Projeto`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acessa_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `SpiderMsControl`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acessa_Perfil1`
    FOREIGN KEY (`Perfil_id`)
    REFERENCES `SpiderMsControl`.`Perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Medida` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `projeto_id` INT NOT NULL COMMENT '',
  `nome` TEXT NOT NULL COMMENT '',
  `definicao` TEXT NOT NULL COMMENT '',
  `pontoDeVista` VARCHAR(100) NOT NULL COMMENT '',
  `mnemonico` VARCHAR(45) NOT NULL COMMENT '',
  `escala` VARCHAR(45) NOT NULL COMMENT '',
  `faixaInicio` DOUBLE NOT NULL COMMENT '',
  `faixaFim` DOUBLE NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ObjetivoDeMedicao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Projeto_id` INT NOT NULL COMMENT '',
  `nome` TEXT NULL COMMENT '',
  `nivelObjetivo` VARCHAR(45) NULL COMMENT '',
  `proposito` TEXT NULL COMMENT '',
  `foco` TEXT NULL COMMENT '',
  `ambiente` TEXT NULL COMMENT '',
  `pontoDeVista` TEXT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ObjetivoDeMedicao_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ObjetivoDeMedicao_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ObjetivoDeQuestao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ObjetivoDeMedicao_id` INT NOT NULL COMMENT '',
  `nome` TEXT NOT NULL COMMENT '',
  `pontoDeVista` VARCHAR(45) NOT NULL COMMENT '',
  `tipoDeDerivacao` VARCHAR(45) NOT NULL COMMENT '',
  `dataLevantamento` DATE NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ObjetivoDeQuestao_ObjetivoDeMedicao1_idx` (`ObjetivoDeMedicao_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ObjetivoDeQuestao_ObjetivoDeMedicao1`
    FOREIGN KEY (`ObjetivoDeMedicao_id`)
    REFERENCES `SpiderMsControl`.`ObjetivoDeMedicao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Indicador` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ObjetivoDeQuestao_id` INT NOT NULL COMMENT '',
  `nome` TEXT NOT NULL COMMENT '',
  `descricao` TEXT NOT NULL COMMENT '',
  `pontoDeVista` VARCHAR(100) NOT NULL COMMENT '',
  `mnemonico` VARCHAR(45) NOT NULL COMMENT '',
  `entidadeDeMedida` VARCHAR(45) NOT NULL COMMENT '',
  `propriedadeDeMedidade` VARCHAR(45) NULL COMMENT '',
  `aprovacao` INT NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `prioridade` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_Indicador_ObjetivoDeQuestao1_idx` (`ObjetivoDeQuestao_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Indicador_ObjetivoDeQuestao1`
    FOREIGN KEY (`ObjetivoDeQuestao_id`)
    REFERENCES `SpiderMsControl`.`ObjetivoDeQuestao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Analise` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Indicador_id` INT NOT NULL COMMENT '',
  `criterioDeAnalise` TEXT NOT NULL COMMENT '',
  `DataCriação` DATE NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `analiseDE` DATE NOT NULL COMMENT '',
  `analiseATE` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_Analise_Indicador1_idx` (`Indicador_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Analise_Indicador1`
    FOREIGN KEY (`Indicador_id`)
    REFERENCES `SpiderMsControl`.`Indicador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Coleta` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `Medida_id` INT NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `valorDaColeta` DOUBLE NULL COMMENT '',
  `usado` TINYINT(1) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_Coleta_Medida1_idx` (`Medida_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Coleta_Medida1`
    FOREIGN KEY (`Medida_id`)
    REFERENCES `SpiderMsControl`.`Medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ProcedimentoDeColeta` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Medida_id` INT NOT NULL COMMENT '',
  `projeto_id` INT NOT NULL COMMENT '',
  `responsavelPelaColeta` VARCHAR(45) NOT NULL COMMENT '',
  `momento` VARCHAR(45) NOT NULL COMMENT '',
  `periodicidade` VARCHAR(45) NOT NULL COMMENT '',
  `proximaPeriodicidade` VARCHAR(45) NULL COMMENT '',
  `frequencia` INT NOT NULL COMMENT '',
  `passosColeta` TEXT NOT NULL COMMENT '',
  `tipoDeColeta` VARCHAR(45) NOT NULL COMMENT '',
  `calculo` VARCHAR(45) NOT NULL COMMENT '',
  `ferramentasUtilizada` VARCHAR(45) NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `porcentagem` DOUBLE NOT NULL COMMENT '',
  `contadorColeta` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ProcedimentoDeColeta_Medida1_idx` (`Medida_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ProcedimentoDeColeta_Medida1`
    FOREIGN KEY (`Medida_id`)
    REFERENCES `SpiderMsControl`.`Medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ProcedimentoDeAnalise` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `responsavel` VARCHAR(45) NOT NULL COMMENT '',
  `composicao` VARCHAR(45) NOT NULL COMMENT '',
  `formula` TEXT NOT NULL COMMENT '',
  `periodicidade` VARCHAR(45) NOT NULL COMMENT '',
  `frequencia` TEXT NOT NULL COMMENT '',
  `graficoNome` VARCHAR(45) NOT NULL COMMENT '',
  `metaOk` DOUBLE NOT NULL COMMENT '',
  `metaAlerta` DOUBLE NOT NULL COMMENT '',
  `metaCritico` DOUBLE NOT NULL COMMENT '',
  `criterioOk` TEXT NOT NULL COMMENT '',
  `criterioAlerta` TEXT NOT NULL COMMENT '',
  `criterioCritico` TEXT NOT NULL COMMENT '',
  `acoesOk` TEXT NOT NULL COMMENT '',
  `acoesAlerta` TEXT NOT NULL COMMENT '',
  `acoesCritico` TEXT NOT NULL COMMENT '',
  `dataComunicacao` DATE NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `Indicador_id` INT NOT NULL COMMENT '',
  `projeto_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ProcedimentoDeAnalise_Indicador1_idx` (`Indicador_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ProcedimentoDeAnalise_Indicador1`
    FOREIGN KEY (`Indicador_id`)
    REFERENCES `SpiderMsControl`.`Indicador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroMedida` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `Medida_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroMedida_Medida1_idx` (`Medida_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroMedida_Medida1`
    FOREIGN KEY (`Medida_id`)
    REFERENCES `SpiderMsControl`.`Medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroProcedimentoColeta` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `ProcedimentoDeColeta_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroProcedimentoColeta_ProcedimentoDeColeta1_idx` (`ProcedimentoDeColeta_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroProcedimentoColeta_ProcedimentoDeColeta1`
    FOREIGN KEY (`ProcedimentoDeColeta_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeColeta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroProcedimentoAnalise` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `ProcedimentoDeAnalise_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroProcedimentoAnalise_ProcedimentoDeAnalise1_idx` (`ProcedimentoDeAnalise_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroProcedimentoAnalise_ProcedimentoDeAnalise1`
    FOREIGN KEY (`ProcedimentoDeAnalise_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeAnalise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroObjetivoMedicao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `ObjetivoDeMedicao_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroObjetivoMedicao_ObjetivoDeMedicao1_idx` (`ObjetivoDeMedicao_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroObjetivoMedicao_ObjetivoDeMedicao1`
    FOREIGN KEY (`ObjetivoDeMedicao_id`)
    REFERENCES `SpiderMsControl`.`ObjetivoDeMedicao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroObjetivoQuestao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `ObjetivoDeQuestao_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroObjetivoQuestao_ObjetivoDeQuestao1_idx` (`ObjetivoDeQuestao_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroObjetivoQuestao_ObjetivoDeQuestao1`
    FOREIGN KEY (`ObjetivoDeQuestao_id`)
    REFERENCES `SpiderMsControl`.`ObjetivoDeQuestao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroProjeto` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Projeto_id` INT NOT NULL COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroProjeto_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroProjeto_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Possui` (
  `Funcionalidade_id` INT NOT NULL COMMENT '',
  `Perfil_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`Funcionalidade_id`, `Perfil_id`)  COMMENT '',
  INDEX `fk_Funcionalidade_has_Perfil_Perfil1_idx` (`Perfil_id` ASC)  COMMENT '',
  INDEX `fk_Funcionalidade_has_Perfil_Funcionalidade1_idx` (`Funcionalidade_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Funcionalidade_has_Perfil_Funcionalidade1`
    FOREIGN KEY (`Funcionalidade_id`)
    REFERENCES `SpiderMsControl`.`Funcionalidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionalidade_has_Perfil_Perfil1`
    FOREIGN KEY (`Perfil_id`)
    REFERENCES `SpiderMsControl`.`Perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Indicador_has_Medida` (
  `Indicador_id` INT NOT NULL COMMENT '',
  `Medida_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`Indicador_id`, `Medida_id`)  COMMENT '',
  INDEX `fk_Indicador_has_Medida_Medida1_idx` (`Medida_id` ASC)  COMMENT '',
  INDEX `fk_Indicador_has_Medida_Indicador1_idx` (`Indicador_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Indicador_has_Medida_Indicador1`
    FOREIGN KEY (`Indicador_id`)
    REFERENCES `SpiderMsControl`.`Indicador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Indicador_has_Medida_Medida1`
    FOREIGN KEY (`Medida_id`)
    REFERENCES `SpiderMsControl`.`Medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroIndicador` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` INT NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `descricao` TEXT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `Indicador_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroIndicador_Indicador1_idx` (`Indicador_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroIndicador_Indicador1`
    FOREIGN KEY (`Indicador_id`)
    REFERENCES `SpiderMsControl`.`Indicador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`EntidadeMedida` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Projeto_id` INT NULL COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC)  COMMENT '',
  INDEX `fk_EntidadeMedida_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  CONSTRAINT `fk_EntidadeMedida_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ValorMedida` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Medida_id` INT NOT NULL COMMENT '',
  `valor` DOUBLE NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `usado` TINYINT(1) NOT NULL COMMENT '',
  INDEX `fk_ValorColeto_Medida1_idx` (`Medida_id` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `fk_ValorColeto_Medida1`
    FOREIGN KEY (`Medida_id`)
    REFERENCES `SpiderMsControl`.`Medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ValorIndicador` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Indicador_id` INT NOT NULL COMMENT '',
  `valor` DOUBLE NOT NULL COMMENT '',
  `data` DATE NULL COMMENT '',
  INDEX `fk_ValorIndicador_Indicador1_idx` (`Indicador_id` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `fk_ValorIndicador_Indicador1`
    FOREIGN KEY (`Indicador_id`)
    REFERENCES `SpiderMsControl`.`Indicador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`datasProcedimentoColeta` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ProcedimentoDeColeta_id` INT NOT NULL COMMENT '',
  `dia` VARCHAR(45) NULL COMMENT '',
  `dataInicio` DATE NULL COMMENT '',
  `dataFim` DATETIME NULL COMMENT '',
  `emUso` INT NOT NULL COMMENT '',
  INDEX `fk_datasProcedimentoColeta_ProcedimentoDeColeta1_idx` (`ProcedimentoDeColeta_id` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `fk_datasProcedimentoColeta_ProcedimentoDeColeta1`
    FOREIGN KEY (`ProcedimentoDeColeta_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeColeta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Resultados` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `idProjeto` INT NOT NULL COMMENT '',
  `titulo` TEXT NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `nomeUsuario` VARCHAR(45) NOT NULL COMMENT '',
  `interpretacao` TEXT NOT NULL COMMENT '',
  `tomadaDeDecisao` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`MeiosComunicacao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `Projeto_id` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_MeiosComunicacao_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC)  COMMENT '',
  CONSTRAINT `fk_MeiosComunicacao_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`MeiosProcedimentoAnalise` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_meioComunicacao` INT NOT NULL COMMENT '',
  `ProcedimentoDeAnalise_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_MeiosProcedimentoAnalise_ProcedimentoDeAnalise1_idx` (`ProcedimentoDeAnalise_id` ASC)  COMMENT '',
  CONSTRAINT `fk_MeiosProcedimentoAnalise_ProcedimentoDeAnalise1`
    FOREIGN KEY (`ProcedimentoDeAnalise_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeAnalise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`PerfisInteressadosProcedimentoAnalise` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_perfilInteressado` INT NOT NULL COMMENT '',
  `ProcedimentoDeAnalise_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_PerfisInteressadosProcedimentoAnalise_ProcedimentoDeAnal_idx` (`ProcedimentoDeAnalise_id` ASC)  COMMENT '',
  CONSTRAINT `fk_PerfisInteressadosProcedimentoAnalise_ProcedimentoDeAnalise1`
    FOREIGN KEY (`ProcedimentoDeAnalise_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeAnalise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`PerfilInteressado` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `Projeto_id` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_PerfilInteressado_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC)  COMMENT '',
  CONSTRAINT `fk_PerfilInteressado_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`RegistroDataComunicacao` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ProcedimentoDeAnalise_id` INT NOT NULL COMMENT '',
  `DataComunicacao` DATE NOT NULL COMMENT '',
  `Tipo` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_RegistroDataComunicacao_ProcedimentoDeAnalise1_idx` (`ProcedimentoDeAnalise_id` ASC)  COMMENT '',
  CONSTRAINT `fk_RegistroDataComunicacao_ProcedimentoDeAnalise1`
    FOREIGN KEY (`ProcedimentoDeAnalise_id`)
    REFERENCES `SpiderMsControl`.`ProcedimentoDeAnalise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Relatorios` (
  `idRelatorio` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `tipoRelatorio` VARCHAR(45) NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `autor` VARCHAR(45) NOT NULL COMMENT '',
  `observacao` TEXT NULL COMMENT '',
  `Projeto_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idRelatorio`)  COMMENT '',
  INDEX `fk_Relatorios_Projeto1_idx` (`Projeto_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Relatorios_Projeto1`
    FOREIGN KEY (`Projeto_id`)
    REFERENCES `SpiderMsControl`.`Projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`Analise_has_Resultados` (
  `Analise_id` INT NOT NULL COMMENT '',
  `Resultados_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`Analise_id`, `Resultados_id`)  COMMENT '',
  INDEX `fk_Analise_has_Resultados_Resultados1_idx` (`Resultados_id` ASC)  COMMENT '',
  INDEX `fk_Analise_has_Resultados_Analise1_idx` (`Analise_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Analise_has_Resultados_Analise1`
    FOREIGN KEY (`Analise_id`)
    REFERENCES `SpiderMsControl`.`Analise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Analise_has_Resultados_Resultados1`
    FOREIGN KEY (`Resultados_id`)
    REFERENCES `SpiderMsControl`.`Resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SpiderMsControl`.`ParticipantesE_Interessados` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Resultados_id` INT NOT NULL COMMENT '',
  `tipo` VARCHAR(45) NOT NULL COMMENT '',
  `ParticipanteEInteressado` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ParticipantesE_Interessados_Resultados1_idx` (`Resultados_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ParticipantesE_Interessados_Resultados1`
    FOREIGN KEY (`Resultados_id`)
    REFERENCES `SpiderMsControl`.`Resultados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into  perfil values(1, 'Alta Administração'),
      (2, 'Gerente de Projeto'),
      (3, 'Analista de Medição'),
      (4, 'Bibliotecário de Medição'),
      (5, 'Usuário de Medição');

insert into funcionalidade values(1, 'Objetivos - Objetivo da Medição'),
      (2, 'Objetivos - Necessidade de Informações'),
      (3, 'Indicadores - Definição'),
      (4, 'Indicadores - Aprovação'),
      (5, 'Indicadores - Valor'),
      (6, 'Indicadores - Análise'),
      (7, 'Medidas - Definição'),
      (8, 'Medidas - Coleta'),
      (9, 'Procedimentos - Coleta'),
      (10, 'Procedimentos - Análise'),
      (11, 'Artefatos - Plano de Medição'),
      (12, 'Artefatos - Relatório'),
      (13, 'Resultados');

insert into entidademedida  values (1, null, 'Organização'),
       (2, null, 'Projeto'),
       (3, null, 'Processo'),
       (4, null, 'Atividade'),
       (5, null, 'Recurso humano'),
       (6, null, 'Recurso de hardware'),
       (7, null, 'Recurso de software'),
       (9, null, 'Artefato');

insert into  meioscomunicacao (nome) values('Reunião'),
      ('WhatsApp'),
      ('E-mail'),
      ('Hangouts'),
      ('Facebook'),
      ('Ligação Telefônica'),
      ('SMS');
      
insert into  perfilinteressado (nome) values('Alta Administração'),
      ('Gerente de Projetos'),
      ('Bibliotecário de Medição'),
      ('Análista de Medição'),
      ('Usuário de Medição');

CREATE USER 'spider_msc'@'localhost' IDENTIFIED BY 'spider_msc';
FLUSH PRIVILEGES;
GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, LOCK TABLES  ON `spider\_rm`.* TO 'spider_rm'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;