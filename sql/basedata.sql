/* Alarmes de Nós */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(1,'Nó indisponível', 'Não foi possível se conectar ao nó',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(2,'Nó não gerenciavel', 'Não foi possível se conectar ao agente, verifique se o agente está iniciado no nó',0);

/* Alarmes de Servidor */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(3,'Threshold CPU', 'O uso de CPU atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(4,'Threshold Memoria', 'O uso de Memoria atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(5,'Threshold Particão', 'O uso da particao ? atingiu o limite estabelecido.',1,'%');
