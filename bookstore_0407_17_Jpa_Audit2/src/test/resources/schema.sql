USE BOOKSTORE;

DROP TABLE IF EXISTS histories;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS books_audit;
DROP TABLE IF EXISTS books_audit_h;
DROP TABLE IF EXISTS revinfo;
DROP TABLE IF EXISTS users;

create table books (
  id Integer AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  author varchar(50) NOT NULL,
  publishDate timestamp NOT NULL,
  comment varchar(255),
  status Integer NOT NULL,
  rentUserId Integer
);

create table books_audit (
  id integer auto_increment primary key,
  name varchar(255) not null,
  author varchar(50) not null,
  publishdate timestamp not null,
  comment varchar(255),
  status integer not null,
  rentuserid integer,
  created_by varchar(20) default null,
  created_date timestamp not null   ,
  last_modified_by varchar(20) default null,
  last_modified_date timestamp not null
 
);

create table books_audit_h (
  id integer auto_increment primary key,
  name varchar(255) not null,
  author varchar(50) not null,
  publishdate timestamp not null,
  comment varchar(255),
  status integer not null,
  rentuserid integer,
  created_by varchar(20) default null,
  created_date timestamp not null   ,
  last_modified_by varchar(20) default null,
  last_modified_date timestamp not null,
  audit_revision int not null,
  action_type int,
  audit_revision_end int, -- 0 추가, 1 수정, 2 삭제
  audit_revision_end_ts timestamp
);

create table revinfo (
       rev int not null auto_increment,
       revtstmp bigint not null,
       primary key (rev, revtstmp)
);

create table users (
    id Integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    password varchar(12) NOT NULL,
    point Integer NOT NULL,
    level Integer NOT NULL  
);


ALTER TABLE bookstore.books ADD CONSTRAINT books_users_FK FOREIGN KEY (rentUserId) REFERENCES bookstore.users(id) ON DELETE SET NULL;


create table histories (
    id Integer AUTO_INCREMENT PRIMARY KEY,
    userId Integer NOT NULL,
    bookId Integer NOT NULL,
    actionType Integer NOT NULL,
    insertDate timestamp NOT NULL
);

ALTER TABLE bookstore.histories ADD CONSTRAINT history_userFK FOREIGN KEY (userId) REFERENCES bookstore.users(id);
ALTER TABLE bookstore.histories ADD CONSTRAINT history_bookFK FOREIGN KEY (bookId) REFERENCES bookstore.books(id);

