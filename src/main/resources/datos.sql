insert into tipo (nombre) values ('Carro');
insert into tipo (nombre) values ('Moto');

insert into bahia (numero, estado, id_tipo) values (1, 'Disponible', 1);

insert into vehiculo (placa, cilidranje, id_tipo, id_bahia) values ('QWE213', null, 1, 1);

insert into parqueo (fecha_inicial, fecha_fin, costo, estado, id_vehiculo) values ('2019-04-01 19:40:37', null, null, 'Disponible', 1);