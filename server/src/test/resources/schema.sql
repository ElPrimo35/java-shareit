drop table if exists users cascade;
drop table if exists requests cascade;
drop table if exists bookings cascade;
drop table if exists items cascade;
drop table if exists comments cascade;

CREATE TABLE IF NOT EXISTS users (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(512) NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

create table if not exists requests (
  id INTEGER generated by default as identity not null,
  description varchar(255) not null,
  requestor_id INTEGER not null,
  created TIMESTAMP WITHOUT TIME ZONE NOT null,
  PRIMARY KEY (id),
  foreign key(requestor_id) references users(id)
);

CREATE TABLE IF NOT EXISTS items (
  id INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(512) NOT NULL,
  is_available BOOLEAN NOT NULL,
  owner_id INT,
  request_id INT,
  PRIMARY KEY (id),
  foreign key(owner_id) references users(id),
  foreign key(request_id) references requests(id)
);

CREATE TABLE IF NOT EXISTS bookings (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  end_date TIMESTAMP WITHOUT TIME ZONE NOT null,
  item_id INTEGER not null,
  booker_id INTEGER not null,
  status varchar(128) not null,
  PRIMARY KEY (id),
  foreign key(booker_id) references users(id),
  foreign key(item_id) references items(id)
);

create table if not exists comments (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT null,
  text varchar(512) not null,
  item_id integer not null,
  author_id integer not null,
  created TIMESTAMP WITHOUT TIME ZONE NOT null,
  primary key(id),
  foreign key(item_id) references items(id),
  foreign key(author_id) references users(id)
);