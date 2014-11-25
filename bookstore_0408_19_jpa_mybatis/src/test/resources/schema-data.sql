insert into bookstore.users( name,password,point,level ) values( 'User01','user01' , 5, 0);
insert into bookstore.users( name,password,point,level ) values( 'User02','user01' , 10, 1);
insert into bookstore.users( name,password,point,level ) values( 'User03','user01' , 25, 2);

insert into bookstore.books( name,author,publishDate,comment,status,rentUserId ) values('Book01','Book01 Name','2013-04-05 12:12:12','Text01', 0, 1);
insert into bookstore.books( name,author,publishDate,comment,status,rentUserId ) values('Book02','Book02 Name','2013-04-06 12:12:12','Text02', 1, 2);
insert into bookstore.books( name,author,publishDate,comment,status,rentUserId ) values('Book03','Book03 Name','2013-04-07 12:12:12','Text03', 2, 2);

insert into bookstore.histories( userId,bookId,actionType,insertDate ) values(1, 1, 0 , '2013-04-07 12:12:12' );
insert into bookstore.histories( userId,bookId,actionType,insertDate ) values(1, 1, 1 , '2013-04-17 12:12:12' );
insert into bookstore.histories( userId,bookId,actionType,insertDate ) values(1, 1, 1 , '2013-04-27 12:12:12' );