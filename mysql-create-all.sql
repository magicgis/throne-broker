create table mqtt_account (
  id                            bigint auto_increment not null,
  email                         varchar(255) not null,
  password                      varchar(255) not null,
  active                        tinyint(1) default 0 not null,
  name                          varchar(20),
  constraint uq_mqtt_account_email unique (email),
  constraint pk_mqtt_account primary key (id)
);

