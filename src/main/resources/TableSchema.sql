create table student(
                        student_id serial primary key not null,
                        student_name varchar(50) not null,
                        email varchar(50),
                        phone_number varchar(20) check (phone_number ~ '^[0-9\+\-\(\)]+$')
    );

create table instructor(
                           instructor_id serial primary key,
                           instructor_name varchar(50) not null,
                           email varchar(50) unique
);

create table course(
                       course_id serial primary key not null,
                       course_name varchar(100) not null,
                       description varchar(200),
                       instructor_id int not null,
                       foreign key (instructor_id) references instructor(instructor_id) on delete cascade
);

create table student_course(
                               id serial primary key not null,
                               student_id int not null,
                               course_id int not null,
                               foreign key (student_id) references student(student_id) on delete cascade,
                               foreign key (course_id) references course(course_id) on delete cascade,
                               unique (student_id, course_id)
);