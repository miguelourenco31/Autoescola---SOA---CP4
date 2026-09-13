alter table instrucoes
    add column cancelada boolean not null default false,
    add column motivo_cancelamento varchar(50);