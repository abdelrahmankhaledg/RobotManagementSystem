--RobotDynamicState
create table robot_dynamic_state (serial_number varchar(255) not null, battery_capacity integer, robot_state varchar(255), primary key (serial_number))

--Robot
create table robot (serial_number varchar(255) not null, robot_model varchar(255), weight_limit integer, primary key (serial_number))
alter table if exists robot add constraint robot_dynamic_state_fk foreign key (serial_number) references robot_dynamic_state

--Medication
create table medication (name varchar(255) not null, code varchar(255), image_url varchar(255), weight integer, primary key (name))

