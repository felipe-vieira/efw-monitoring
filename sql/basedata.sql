/* Alarmes de N�s */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(1,'N� indispon�vel', 'N�o foi poss�vel se conectar ao n�',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(2,'N� n�o gerenciavel', 'N�o foi poss�vel se conectar ao agente, verifique se o agente est� iniciado no n�',0);

/* Alarmes de Servidor */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(3,'Threshold CPU', 'O uso de CPU atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(4,'Threshold Memoria', 'O uso de Memoria atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(5,'Threshold Parti��o', 'O uso da particao ? atingiu o limite estabelecido.',1,'%');

/* Alarmes de servidor de aplica��o */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(6,'Nenhum deployment', 'Nenhum deployment encontrado no servidor de aplica��o',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(7,'Deployment desativado', 'O deployment ? foi desativado',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(8,'Threshold Memoria Heap', 'O uso de memoria heap atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(9,'Threshold Memoria Non-heap', 'O uso da memoria non-heap atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(10,'Threshold CPU TIME', 'O uso de CPU Time atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(11,'Threshold CPU USER', 'O uso do CPU USER ? atingiu o limite estabelecido.',1,'%');

/* Alarmes de banco de dados */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(12,'Threshold File Max Size', 'O arquivo ? atingiu o limite estabelecido',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(13,'Threshold Memoria BD', 'O uso de memoria do BD atingiu o limite estabelecido',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(14,'Status do Job','O job ? foi concluido com o status ?.',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(15,'Tempo de Backup','O tempo desde o ultimo backup excedeu o tempo limite estabelecido',1,'s');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(16,'Status do BD','O status do BD de dados foi alterado para: ?',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(17,'Tempo de Execu��o do Job','O tempo de execu��o do job ? excedeu o limite estabelecido',1,'s');

/* Alarmes de SLA */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(18,'SLA Di�rio', 'O SLA di�rio n�o antingiu a meta.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(19,'SLA Mensal', 'O SLA mensal est� abaixo da meta',1,'%');

/* Usuario ADM */
insert into usuario (id,login,senha,administrador,ativo) VALUES(1,'adm','pdcpdc',1,1);