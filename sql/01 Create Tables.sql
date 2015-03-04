USE elearning;


create table account(
	id bigint not null auto_increment,
	first_name varchar(60) not null,
	last_name varchar(60) not null,
	email_id varchar(60) not null,
	active_ind boolean not null default 1,
	version bigint not null,
	primary key(id)
);


create table address(
	id bigint not null auto_increment,
	account_id bigint not null,
	street_name varchar(255),
	suburb varchar(60),
	state varchar(60),
	country varchar(60),
	zip_code varchar(12),
	version bigint not null,
	primary key(id)
);

alter table address 
        add index FK_ADDRESS_ACCOUNT (account_id),
        add constraint FK_ADDRESS_ACCOUNT 
        foreign key (account_id) 
        references account (id);

		
create table course(
	id bigint not null auto_increment,
	code varchar(10) not null,
	title varchar(255) not null,
	active_ind boolean not null default 1,
	version bigint not null,
	primary key(id)
);
create unique index `IDX_COURSE_CODE` on course (code);

create table training(
	id bigint not null auto_increment,
	account_id bigint not null,
	course_id bigint not null,
	start_date date not null,
	end_date date,
	grade char(2),
	comments varchar(400),
	version bigint not null,
	primary key(id)
);		

create unique index `IDX_ACC_CRSE` on training (account_id, course_id);
alter table training
        add index FK_TRAINING_ACCOUNT (account_id),
        add constraint FK_TRAINING_ACCOUNT 
        foreign key (account_id) 
        references account (id)
		on delete cascade;
alter table training
        add index FK_TRAINING_COURSE (course_id),
        add constraint FK_TRAINING_COURSE 
        foreign key (course_id) 
        references course (id)
		on delete cascade;		
		
		