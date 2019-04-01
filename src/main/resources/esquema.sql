drop table if exists tipo;
drop table if exists bahia;
drop table if exists vehiculo;
drop table if exists parqueo;

create table tipo (  
    id_tipo identity primary key,  
    nombre varchar(50) not null
);

create table bahia (  
    id_bahia identity primary key,
    numero int not null,
    estado varchar(50) not null,
    id_tipo int
);

ALTER TABLE bahia
    ADD FOREIGN KEY (id_tipo) 
    REFERENCES tipo(id_tipo);
    
create table vehiculo (  
    id_vehiculo identity primary key,  
    placa varchar(6) not null,
    cilidranje varchar(10),
    id_tipo int,
    id_bahia int
);

ALTER TABLE vehiculo
    ADD FOREIGN KEY (id_tipo) 
    REFERENCES tipo(id_tipo);

ALTER TABLE vehiculo
    ADD FOREIGN KEY (id_bahia) 
    REFERENCES bahia(id_bahia);
    
create table parqueo (  
    id_parqueo identity primary key,  
    fecha_inicial varchar(20) not null,
    fecha_fin varchar(20),
    costo varchar(20),
    estado varchar(50) not null,
    id_vehiculo int
);

ALTER TABLE parqueo
    ADD FOREIGN KEY (id_vehiculo) 
    REFERENCES vehiculo(id_vehiculo);