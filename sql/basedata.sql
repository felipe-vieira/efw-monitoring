/* Alarmes de Nós */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(1,'Nó indisponível', 'Não foi possível se conectar ao nó',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(2,'Nó não gerenciavel', 'Não foi possível se conectar ao agente, verifique se o agente está iniciado no nó',0);

/* Alarmes de Servidor */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(3,'Threshold CPU', 'O uso de CPU atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(4,'Threshold Memoria', 'O uso de Memoria atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(5,'Threshold Particão', 'O uso da particao ? atingiu o limite estabelecido.',1,'%');

/* Alarmes de servidor de aplicação */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(6,'Nenhum deployment', 'Nenhum deployment encontrado no servidor de aplicação',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(7,'Deployment desativado', 'O deployment ? foi desativado',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(8,'Threshold Memoria Heap', 'O uso de memoria heap atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(9,'Threshold Memoria Non-heap', 'O uso da memoria non-heap atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(10,'Threshold CPU TIME', 'O uso de CPU Time atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(11,'Threshold CPU USER', 'O uso do CPU USER ? atingiu o limite estabelecido.',1,'%');

/* Alarmes de banco de dados */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(12,'Threshold File Max Size', 'O arquivo ? atingiu o limite estabelecido',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(13,'Threshold Memoria BD', 'O uso de memoria atingiu o limite estabelecido',1,'%');
