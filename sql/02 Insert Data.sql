USE elearning;

insert into address values (1,'6-10 Railway Pde','Westmead','NSW','Australia','2145',0);

insert into account values (1,'Shyam','Prakash','prakash.shyam@gmail.com',1,1,0);

insert into course values (1, 'CRSE01', 'Mathematics',1,0);
insert into course values (2, 'CRSE02', 'Physics',1,0);
insert into course values (3, 'CRSE03', 'Computing',1,0);
		
insert into training values (1,1,1,now(),'','A+','Good',0);		
insert into training values (2,1,2,now(),'','A','',0);

commit;
