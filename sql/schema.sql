CREATE database monitor;

GRANT ALL PRIVILEGES ON monitor.* TO 'efwdbusr'@'localhost' IDENTIFIED BY '4c4b@Tcc';

GRANT ALL PRIVILEGES ON monitor.* TO 'efwdbusr'@'%' IDENTIFIED BY '4c4b@Tcc';

FLUSH PRIVILEGES;