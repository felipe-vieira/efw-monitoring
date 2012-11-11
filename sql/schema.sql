    DELIMITER ;
	
	CREATE database monitor;

	GRANT ALL PRIVILEGES ON monitor.* TO 'efwdbusr'@'localhost' IDENTIFIED BY '4c4b@Tcc';

	GRANT ALL PRIVILEGES ON monitor.* TO 'efwdbusr'@'%' IDENTIFIED BY '4c4b@Tcc';

	FLUSH PRIVILEGES;
	
	USE monitor;
	
	drop table if exists agendamento;

    drop table if exists agendamento_removido;

    drop table if exists alarme;

    drop table if exists avaliacao_solucao;

    drop table if exists banco_backup;

    drop table if exists banco_dados;

    drop table if exists banco_dados_threshold;

    drop table if exists banco_file;

    drop table if exists banco_file_coleta;

    drop table if exists banco_job;

    drop table if exists banco_job_coleta;

    drop table if exists banco_memoria_coleta;

    drop table if exists dias_semana_janela_sla;

    drop table if exists dias_semana_sla;

    drop table if exists glassfish;

    drop table if exists indisponibilidade;

    drop table if exists janela_sla;

    drop table if exists jboss;

    drop table if exists memoria;

    drop table if exists memoria_coleta;

    drop table if exists no;

    drop table if exists oracle;

    drop table if exists particao;

    drop table if exists particao_coleta;

    drop table if exists processador;

    drop table if exists processador_coleta;

    drop table if exists servidor;

    drop table if exists servidor_aplicacao;

    drop table if exists servidor_aplicacao_deployment;

    drop table if exists servidor_aplicacao_memoria;

    drop table if exists servidor_aplicacao_memoria_coleta;

    drop table if exists servidor_aplicacao_thread_coleta;

    drop table if exists servidor_aplicacao_threshold;

    drop table if exists servidor_threshold;

    drop table if exists sistema_operacional;

    drop table if exists sla;

    drop table if exists sla_calculado;

    drop table if exists solucao;

    drop table if exists sql_server;

    drop table if exists threshold;

    drop table if exists tipo_alarme;

    drop table if exists usuario;

    create table agendamento (
        agendamento_id integer not null,
        no integer not null,
        intervalo integer not null,
        horaInicio char(5),
        horaFim char(5),
        agendado boolean not null,
        ativo boolean not null,
        primary key (agendamento_id)
    );

    create table agendamento_removido (
        agendamento_removido_id integer not null,
        agendamento integer not null,
        status boolean not null,
        primary key (agendamento_removido_id)
    );

    create table alarme (
        id bigint not null,
        no integer not null,
        tipo integer not null,
        solucao bigint,
        status integer,
        criticidade integer,
        data datetime not null,
        valor decimal(19,2),
        valorLimite decimal(19,2),
        parametro varchar(255),
        primary key (id)
    );

    create table avaliacao_solucao (
        id bigint not null,
        usuario bigint,
        solucao bigint,
        avaliacao integer,
        tipoAvaliacao integer,
        primary key (id)
    );

    create table banco_backup (
        id bigint not null,
        bancoDados integer not null,
        fileName varchar(255) not null,
        backupStartDate datetime not null,
        tempoExecucao bigint not null,
        tamanho bigint not null,
        recoveryModel varchar(255),
        setCount bigint not null,
        databaseName varchar(255),
        tipo varchar(255),
        primary key (id)
    );

    create table banco_dados (
        no_id integer not null,
        port integer not null,
        usuario varchar(30),
        senha varchar(30),
        target_server_memory bigint,
        version varchar(255),
        edition varchar(255),
        status varchar(30),
        collation varchar(255),
        threshold integer,
        primary key (no_id)
    );

    create table banco_dados_threshold (
        id integer not null,
        limite_memoria decimal(5,2),
        limite_file decimal(5,2),
        limiteTempoBackup bigint,
        limiteTempoJob bigint,
        primary key (id)
    );

    create table banco_file (
        id bigint not null,
        bancoDados integer not null,
        file varchar(255) not null,
        filePath varchar(255) not null,
        maxSize bigint not null,
        situacao varchar(255) not null,
        fileName varchar(255) not null,
        ativo boolean not null,
        growth varchar(255),
        databaseName varchar(255),
        primary key (id)
    );

    create table banco_file_coleta (
        id bigint not null,
        file bigint not null,
        dataColeta datetime not null,
        size bigint not null,
        primary key (id)
    );

    create table banco_job (
        id bigint not null,
        bancoDados integer not null,
        jobName varchar(255) not null,
        owner varchar(255),
        primary key (id)
    );

    create table banco_job_coleta (
        id bigint not null,
        bancoJob bigint not null,
        dataColeta datetime not null,
        dataExecucao datetime not null,
        executionTime bigint not null,
        status integer not null,
        statusDescr varchar(30),
        logId bigint not null,
        sqlMsg varchar(4000),
        primary key (id)
    );

    create table banco_memoria_coleta (
        id bigint not null,
        bancoDados integer not null,
        dataColeta datetime not null,
        memory bigint not null,
        stolenMemory bigint,
        primary key (id)
    );

    create table dias_semana_janela_sla (
        id bigint not null,
        dia1 boolean not null,
        dia2 boolean not null,
        dia3 boolean not null,
        dia4 boolean not null,
        dia5 boolean not null,
        dia6 boolean not null,
        dia7 boolean not null,
        primary key (id)
    );

    create table dias_semana_sla (
        id bigint not null,
        dia1 boolean not null,
        dia2 boolean not null,
        dia3 boolean not null,
        dia4 boolean not null,
        dia5 boolean not null,
        dia6 boolean not null,
        dia7 boolean not null,
        primary key (id)
    );

    create table glassfish (
        no_id integer not null,
        jmxUser varchar(255),
        jmxSenha varchar(255),
        primary key (no_id)
    );

    create table indisponibilidade (
        id integer not null,
        no integer not null,
        inicio datetime not null,
        fim datetime,
        primary key (id)
    );

    create table janela_sla (
        id bigint not null,
        sla bigint,
        descricao varchar(255) not null,
        dataInicio datetime not null,
        dataFim datetime,
        horaInicio time not null,
        horaFim time not null,
        ativo boolean not null,
        primary key (id)
    );

    create table jboss (
        no_id integer not null,
        primary key (no_id)
    );

    create table memoria (
        no_id integer not null,
        totalMemoria bigint not null,
        primary key (no_id)
    );

    create table memoria_coleta (
        id bigint not null,
        memoria integer,
        data_coleta datetime not null,
        usado bigint not null,
        primary key (id)
    );

    create table no (
        no_id integer not null,
        nome varchar(255) not null,
        hostname varchar(255) not null,
        agentPort integer not null,
        ativo boolean not null,
        disponivel boolean,
        gerenciavel boolean,
        ultimaColeta datetime,
        sla bigint,
        primary key (no_id)
    );

    create table oracle (
        no_id integer not null,
        instance_name varchar(255) not null,
        primary key (no_id)
    );

    create table particao (
        particao_id integer not null,
        servidor integer not null,
        nome varchar(255) not null,
        sistema_arquivo varchar(30),
        tamanho bigint not null,
        primary key (particao_id)
    );

    create table particao_coleta (
        id bigint not null,
        particao integer,
        data_coleta datetime not null,
        usado bigint not null,
        primary key (id)
    );

    create table processador (
        no_id integer not null,
        fabricante varchar(255) not null,
        modelo varchar(255) not null,
        cores integer not null,
        clock bigint not null,
        primary key (no_id)
    );

    create table processador_coleta (
        id bigint not null,
        processador integer,
        data_coleta datetime not null,
        usado double precision not null,
        primary key (id)
    );

    create table servidor (
        no_id integer not null,
        threshold integer,
        tipoSO integer,
        primary key (no_id)
    );

    create table servidor_aplicacao (
        no_id integer not null,
        port integer not null,
        jmx_port integer,
        start_time datetime,
        uptime bigint,
        threshold integer,
        primary key (no_id)
    );

    create table servidor_aplicacao_deployment (
        deployment_id bigint not null,
        servidorAplicacao integer,
        nome varchar(255) not null,
        ativo boolean not null,
        primary key (deployment_id)
    );

    create table servidor_aplicacao_memoria (
        memoria_id bigint not null,
        servidor_aplicacao integer not null,
        init bigint not null,
        max bigint not null,
        tipo integer,
        primary key (memoria_id)
    );

    create table servidor_aplicacao_memoria_coleta (
        id bigint not null,
        memoria bigint,
        data_coleta datetime not null,
        used bigint not null,
        commited bigint not null,
        primary key (id)
    );

    create table servidor_aplicacao_thread_coleta (
        id bigint not null,
        servidorAplicacao integer,
        data_coleta datetime not null,
        thread_count bigint not null,
        cpu_time double precision not null,
        user_time double precision not null,
        primary key (id)
    );

    create table servidor_aplicacao_threshold (
        id integer not null,
        threshold_heap decimal(5,2),
        threshold_non_heap decimal(5,2),
        threshold_cpu_user_time decimal(5,2),
        threshold_cpu_cpu_time decimal(5,2),
        primary key (id)
    );

    create table servidor_threshold (
        id integer not null,
        limite_memoria decimal(5,2),
        limite_cpu decimal(5,2),
        limite_particao decimal(5,2),
        primary key (id)
    );

    create table sistema_operacional (
        no_id integer not null,
        nome varchar(255) not null,
        descricao varchar(255) not null,
        fornecedor varchar(255) not null,
        versao varchar(50),
        patch varchar(50),
        arquitetura varchar(10),
        primary key (no_id)
    );

    create table sla (
        id bigint not null,
        nome varchar(255) not null,
        meta decimal(5,2),
        horaInicio time not null,
        horaFim time not null,
        ativo boolean not null,
        ultimaColeta datetime,
        ultimaColetaMes datetime,
        primary key (id)
    );

    create table sla_calculado (
        id bigint not null,
        sla bigint not null,
        no integer not null,
        tipo integer,
        percentual decimal(5,2),
        controle datetime not null,
        tempoTotal bigint not null,
        tempoIndisponivel bigint not null,
        tempoJanela bigint not null,
        primary key (id)
    );

    create table solucao (
        id bigint not null,
        titulo varchar(30),
        descricao varchar(1024),
        data datetime not null,
        tipoAlarme integer,
        no integer,
        usuario bigint,
        tipo integer,
        subTipo integer,
        primary key (id)
    );

    create table sql_server (
        no_id integer not null,
        instance_name varchar(255) not null,
        database_name varchar(255) not null,
        primary key (no_id)
    );

    create table threshold (
        id integer not null,
        nome varchar(30) not null,
        primary key (id)
    );

    create table tipo_alarme (
        id integer not null,
        descricao varchar(30),
        mensagem varchar(200),
        threshold boolean not null,
        unidade varchar(255),
        primary key (id)
    );

    create table usuario (
        id bigint not null,
        login varchar(30),
        senha varchar(30),
        administrador boolean not null,
        ativo boolean not null,
        enviarEmail boolean not null,
        email varchar(255),
        primary key (id)
    );

    alter table agendamento 
        add index FK2B9A22452A4E5A44 (no), 
        add constraint FK2B9A22452A4E5A44 
        foreign key (no) 
        references no (no_id);

    alter table agendamento_removido 
        add index FKC14BCFCDCDB38008 (agendamento), 
        add constraint FKC14BCFCDCDB38008 
        foreign key (agendamento) 
        references agendamento (agendamento_id);

    alter table alarme 
        add index FKABA5D0343BF2F416 (solucao), 
        add constraint FKABA5D0343BF2F416 
        foreign key (solucao) 
        references solucao (id);

    alter table alarme 
        add index FKABA5D03443FD887E (tipo), 
        add constraint FKABA5D03443FD887E 
        foreign key (tipo) 
        references tipo_alarme (id);

    alter table alarme 
        add index FKABA5D0342A4E5A44 (no), 
        add constraint FKABA5D0342A4E5A44 
        foreign key (no) 
        references no (no_id);

    alter table avaliacao_solucao 
        add index FK1EDAF3263BF2F416 (solucao), 
        add constraint FK1EDAF3263BF2F416 
        foreign key (solucao) 
        references solucao (id);

    alter table avaliacao_solucao 
        add index FK1EDAF3261E1E90DA (usuario), 
        add constraint FK1EDAF3261E1E90DA 
        foreign key (usuario) 
        references usuario (id);

    alter table banco_backup 
        add index FK38179C6325FC42 (bancoDados), 
        add constraint FK38179C6325FC42 
        foreign key (bancoDados) 
        references banco_dados (no_id);

    alter table banco_dados 
        add index FK87B3727C31D1484 (threshold), 
        add constraint FK87B3727C31D1484 
        foreign key (threshold) 
        references banco_dados_threshold (id);

    alter table banco_dados 
        add index FK87B372730904CDC (no_id), 
        add constraint FK87B372730904CDC 
        foreign key (no_id) 
        references no (no_id);

    alter table banco_dados_threshold 
        add index FK71C91532755F484 (id), 
        add constraint FK71C91532755F484 
        foreign key (id) 
        references threshold (id);

    alter table banco_file 
        add index FK8CAA2A80325FC42 (bancoDados), 
        add constraint FK8CAA2A80325FC42 
        foreign key (bancoDados) 
        references banco_dados (no_id);

    alter table banco_file_coleta 
        add index FKF2B79AB1C434D51 (file), 
        add constraint FKF2B79AB1C434D51 
        foreign key (file) 
        references banco_file (id);

    alter table banco_job 
        add index FK88AAB639325FC42 (bancoDados), 
        add constraint FK88AAB639325FC42 
        foreign key (bancoDados) 
        references banco_dados (no_id);

    alter table banco_job_coleta 
        add index FKCBCE1CD8B237E766 (bancoJob), 
        add constraint FKCBCE1CD8B237E766 
        foreign key (bancoJob) 
        references banco_job (id);

    alter table banco_memoria_coleta 
        add index FKC94291A5325FC42 (bancoDados), 
        add constraint FKC94291A5325FC42 
        foreign key (bancoDados) 
        references banco_dados (no_id);

    alter table glassfish 
        add index FK5085DFD4241826AA (no_id), 
        add constraint FK5085DFD4241826AA 
        foreign key (no_id) 
        references servidor_aplicacao (no_id);

    alter table indisponibilidade 
        add index FKCE47F1852A4E5A44 (no), 
        add constraint FKCE47F1852A4E5A44 
        foreign key (no) 
        references no (no_id);

    alter table janela_sla 
        add index FK93BCC60C1F7D13CE (sla), 
        add constraint FK93BCC60C1F7D13CE 
        foreign key (sla) 
        references sla (id);

    alter table jboss 
        add index FK603F6D7241826AA (no_id), 
        add constraint FK603F6D7241826AA 
        foreign key (no_id) 
        references servidor_aplicacao (no_id);

    alter table memoria 
        add index FK3894E190E917BAEF (no_id), 
        add constraint FK3894E190E917BAEF 
        foreign key (no_id) 
        references servidor (no_id);

    alter table memoria_coleta 
        add index FK60B3D1A19F1E6D9E (memoria), 
        add constraint FK60B3D1A19F1E6D9E 
        foreign key (memoria) 
        references memoria (no_id);

    alter table no 
        add index FKDC11F7D13CE (sla), 
        add constraint FKDC11F7D13CE 
        foreign key (sla) 
        references sla (id);

    alter table oracle 
        add index FKC3DDFD7E7C79B4CB (no_id), 
        add constraint FKC3DDFD7E7C79B4CB 
        foreign key (no_id) 
        references banco_dados (no_id);

    alter table particao 
        add index FK46DC6D3B350ACFEA (servidor), 
        add constraint FK46DC6D3B350ACFEA 
        foreign key (servidor) 
        references servidor (no_id);

    alter table particao_coleta 
        add index FKAB52DF961E597FB8 (particao), 
        add constraint FKAB52DF961E597FB8 
        foreign key (particao) 
        references particao (particao_id);

    alter table processador 
        add index FKC17EC35E917BAEF (no_id), 
        add constraint FKC17EC35E917BAEF 
        foreign key (no_id) 
        references servidor (no_id);

    alter table processador_coleta 
        add index FKE3B3735C8EAF13E8 (processador), 
        add constraint FKE3B3735C8EAF13E8 
        foreign key (processador) 
        references processador (no_id);

    alter table servidor 
        add index FK52351554BB39B6E0 (threshold), 
        add constraint FK52351554BB39B6E0 
        foreign key (threshold) 
        references servidor_threshold (id);

    alter table servidor 
        add index FK5235155430904CDC (no_id), 
        add constraint FK5235155430904CDC 
        foreign key (no_id) 
        references no (no_id);

    alter table servidor_aplicacao 
        add index FKC34AAF1C4274D85 (threshold), 
        add constraint FKC34AAF1C4274D85 
        foreign key (threshold) 
        references servidor_aplicacao_threshold (id);

    alter table servidor_aplicacao 
        add index FKC34AAF1C30904CDC (no_id), 
        add constraint FKC34AAF1C30904CDC 
        foreign key (no_id) 
        references no (no_id);

    alter table servidor_aplicacao_deployment 
        add index FK266917C8500207C4 (servidorAplicacao), 
        add constraint FK266917C8500207C4 
        foreign key (servidorAplicacao) 
        references servidor_aplicacao (no_id);

    alter table servidor_aplicacao_memoria 
        add index FK9DCC5B6DE120D56D (servidor_aplicacao), 
        add constraint FK9DCC5B6DE120D56D 
        foreign key (servidor_aplicacao) 
        references servidor_aplicacao (no_id);

    alter table servidor_aplicacao_memoria_coleta 
        add index FK1B35F524E4B4A78F (memoria), 
        add constraint FK1B35F524E4B4A78F 
        foreign key (memoria) 
        references servidor_aplicacao_memoria (memoria_id);

    alter table servidor_aplicacao_thread_coleta 
        add index FKD1A76804500207C4 (servidorAplicacao), 
        add constraint FKD1A76804500207C4 
        foreign key (servidorAplicacao) 
        references servidor_aplicacao (no_id);

    alter table servidor_aplicacao_threshold 
        add index FK99225B082755F484 (id), 
        add constraint FK99225B082755F484 
        foreign key (id) 
        references threshold (id);

    alter table servidor_threshold 
        add index FKBCCC5B402755F484 (id), 
        add constraint FKBCCC5B402755F484 
        foreign key (id) 
        references threshold (id);

    alter table sistema_operacional 
        add index FK3A1146A6E917BAEF (no_id), 
        add constraint FK3A1146A6E917BAEF 
        foreign key (no_id) 
        references servidor (no_id);

    alter table sla_calculado 
        add index FK293367292A4E5A44 (no), 
        add constraint FK293367292A4E5A44 
        foreign key (no) 
        references no (no_id);

    alter table sla_calculado 
        add index FK293367291F7D13CE (sla), 
        add constraint FK293367291F7D13CE 
        foreign key (sla) 
        references sla (id);

    alter table solucao 
        add index FK86FF24CC1E1E90DA (usuario), 
        add constraint FK86FF24CC1E1E90DA 
        foreign key (usuario) 
        references usuario (id);

    alter table solucao 
        add index FK86FF24CCD31D21B2 (tipoAlarme), 
        add constraint FK86FF24CCD31D21B2 
        foreign key (tipoAlarme) 
        references tipo_alarme (id);

    alter table solucao 
        add index FK86FF24CC2A4E5A44 (no), 
        add constraint FK86FF24CC2A4E5A44 
        foreign key (no) 
        references no (no_id);

    alter table sql_server 
        add index FK6DA3EAD47C79B4CB (no_id), 
        add constraint FK6DA3EAD47C79B4CB 
        foreign key (no_id) 
        references banco_dados (no_id);
