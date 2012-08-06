/* Alarmes de N�s */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(1,'N� indispon�vel', 'N�o foi poss�vel se conectar ao n�',0);
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold) VALUES(2,'N� n�o gerenciavel', 'N�o foi poss�vel se conectar ao agente, verifique se o agente est� iniciado no n�',0);

/* Alarmes de Servidor */
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(3,'Threshold CPU', 'O uso de CPU atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(4,'Threshold Memoria', 'O uso de Memoria atingiu o limite estabelecido.',1,'%');
INSERT INTO tipo_alarme(id,descricao,mensagem,threshold,unidade) VALUES(5,'Threshold Partic�o', 'O uso da particao ? atingiu o limite estabelecido.',1,'%');
