create table someentity (
  id int GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
  field varchar(255),
  primary key(id)
);

create table related (
  id int GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
  other_id int not null,
  primary key(id),
  foreign key(other_id) references someentity(id)
);

create table base (
  id int GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
  type_id int not null,
  base_field varchar(255),
  primary key(id)
);

create table derived (
  id int GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
  base_id int not null,
  derived_field varchar(255),
  primary key(id),
  foreign key(base_id) references base(id)
);
