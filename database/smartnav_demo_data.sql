-- SmartNav Demo Data (Starter)

INSERT INTO users (name,email,mobile,password,bio,profile_image,created_at) VALUES
('Vijay','vijay@example.com','9876543210','1234','Daily commuter',NULL,NOW()),
('Priya','priya@example.com','9876543211','1234','Student',NULL,NOW());

INSERT INTO route(route_name,source,destination,distance,duration,fare,transport_mode,arrival_time,departure_time,status) VALUES
('147C','Tenkasi','Sengottai','15 km','25 min',10,'BUS','09:00 AM','08:35 AM','Running'),
('99A','Rajapalayam','Tenkasi','35 km','45 min',20,'BUS','10:00 AM','09:15 AM','Running'),
('22D','Tenkasi','Tirunelveli','55 km','1 hr',50,'BUS','09:00 AM','08:00 AM','Running');
INSERT INTO route
(route_name,source,destination,distance,duration,fare,transport_mode,arrival_time,departure_time,status)
VALUES
('210A','Sengottai','Rajapalayam','38 km','50 min',25,'BUS','10:00 AM','09:10 AM','Running'),

('310B','Tirunelveli','Nagercoil','75 km','1 hr 20 min',45,'BUS','10:35 AM','09:15 AM','Running'),

('450X','Rajapalayam','Virudhunagar','48 km','1 hr 05 min',35,'BUS','11:20 AM','10:15 AM','Running'),

('520K','Tenkasi','Kadayanallur','32 km','45 min',20,'BUS','09:35 AM','08:50 AM','Running'),

('610M','Kadayanallur','Rajapalayam','24 km','35 min',15,'BUS','10:20 AM','09:45 AM','Running');

INSERT INTO bus(bus_name,bus_number,source,destination,status,duration,transfer_count,walking_distance,arrival_time,departure_time,transport_mode,fare) VALUES
('Tenkasi Express','147C','Tenkasi','Sengottai','Running','25',0,100,'09:00 AM','08:35 AM','Bus',10),
('Rajapalayam Express','99A','Rajapalayam','Tenkasi','Running','45',0,120,'10:00 AM','09:15 AM','Bus',20);
INSERT INTO bus
(bus_name,bus_number,source,destination,status,duration,transfer_count,walking_distance,arrival_time,departure_time,transport_mode,fare)
VALUES

('Sengottai Express','210A','Sengottai','Rajapalayam','Running','50',0,120,'10:00 AM','09:10 AM','Bus',25),

('Nagercoil Express','310B','Tirunelveli','Nagercoil','Running','80',0,90,'10:35 AM','09:15 AM','Bus',45),

('Virudhunagar Express','450X','Rajapalayam','Virudhunagar','Running','65',0,100,'11:20 AM','10:15 AM','Bus',35),

('Kadayanallur Local','520K','Tenkasi','Kadayanallur','Running','45',0,80,'09:35 AM','08:50 AM','Bus',20),

('Rajapalayam Shuttle','610M','Kadayanallur','Rajapalayam','Running','35',0,60,'10:20 AM','09:45 AM','Bus',15);

INSERT INTO vehicle_location(vehicle_number,vehicle_type,latitude,longitude,speed,timestamp) VALUES
('147C','BUS',8.9598,77.3152,30,NOW()),
('99A','BUS',9.4520,77.5536,32,NOW());

INSERT INTO alerts(alert_type,created_time,is_read,message,vehicle_number,priority) VALUES
('Traffic',NOW(),false,'Heavy traffic near Tenkasi','147C','HIGH');

INSERT INTO feedbacks(category,feedback,created_at) VALUES
('Bus','Very good service',NOW());

INSERT INTO issue_reports(category,issue_title,description,created_at) VALUES
('Road','Road Block','Tree fallen',NOW());

INSERT INTO search_history(source,destination,transport_type,user_id,searched_at) VALUES
('Tenkasi','Sengottai','BUS',1,NOW());

INSERT INTO saved_route(source,destination,bus_name,vehicle_number,transport_mode,duration,fare,departure_time,arrival_time,user_id,created_at) VALUES
('Tenkasi','Sengottai','Tenkasi Express','147C','BUS',25,10,'08:35 AM','09:00 AM',1,NOW());

INSERT INTO otp(mobile,otp,created_at) VALUES
('1234567890','1234',NOW());

-- SmartNav Demo Data - Part 2
-- Stops, Bus Stops, Route Stops, Vehicle Routes

INSERT INTO stops
(stop_name,stop_type,latitude,longitude,visit_count,vehicle_number,vehicle_type,average_stop_time,stop_duration,main_stop_count,major_stop_count,mini_stop_count,delay_detected)
VALUES
('Tenkasi Bus Stand','MAIN',8.9598,77.3152,150,'147C','BUS',3.5,5,25,18,5,false),
('Tenkasi Railway Station','MAJOR',8.9610,77.3180,120,'99A','BUS',2.8,4,20,15,4,false),
('Courtallam','MAIN',8.9292,77.2756,110,'101','BUS',3.0,5,18,12,3,false),
('Ilanji','MINI',8.9440,77.3020,60,'147C','BUS',1.5,2,8,5,2,false),
('Sengottai','MAIN',8.9735,77.2499,180,'147C','BUS',4.0,6,28,20,6,false),
('Puliyangudi','MAJOR',9.1740,77.3970,90,'22D','BUS',2.2,3,12,8,2,false),
('Kadayanallur','MAIN',9.0720,77.3410,95,'22D','BUS',2.5,4,15,10,3,false),
('Pavoorchatram','MAJOR',8.9180,77.2900,75,'99A','BUS',2.0,3,10,7,2,false),
('Ayikudi','MINI',8.9810,77.3330,45,'147C','BUS',1.2,2,5,3,1,false),
('Rajapalayam','MAIN',9.4518,77.5533,210,'99A','BUS',4.5,6,30,22,7,false);
INSERT INTO stops
(stop_name,stop_type,latitude,longitude,visit_count,vehicle_number,vehicle_type,average_stop_time,stop_duration,main_stop_count,major_stop_count,mini_stop_count,delay_detected)
VALUES

('Alangulam','MAJOR',8.8648,77.4992,85,'22D','BUS',2.5,4,12,8,2,false),

('Melapalayam','MAJOR',8.7139,77.7567,95,'22D','BUS',2.8,4,15,10,3,false),

('Tirunelveli Bus Stand','MAIN',8.7139,77.7567,250,'22D','BUS',4.5,6,35,25,8,false),

('Puliyarai','MAJOR',8.9670,77.2240,70,'210A','BUS',2.0,3,10,7,2,false),

('Palayamkottai','MAJOR',8.7280,77.7350,120,'310B','BUS',2.8,4,15,10,3,false),

('Valliyur','MAIN',8.3824,77.6143,140,'310B','BUS',3.2,5,18,12,4,false),

('Nagercoil','MAIN',8.1780,77.4344,300,'310B','BUS',5.0,7,40,28,10,false),

('Srivilliputhur','MAIN',9.5127,77.6337,160,'450X','BUS',3.5,5,20,15,5,false),

('Sivakasi','MAIN',9.4493,77.7970,180,'450X','BUS',3.8,5,22,16,5,false),

('Virudhunagar','MAIN',9.5851,77.9579,220,'450X','BUS',4.2,6,28,20,6,false),

('Sankarankovil Road','MINI',9.1800,77.5400,60,'610M','BUS',1.5,2,6,4,2,false);

INSERT INTO bus_stop(bus_number,stop_name,arrival_time,stop_order) VALUES
('147C','Tenkasi Bus Stand','08:35 AM',1),
('147C','Ilanji','08:42 AM',2),
('147C','Courtallam','08:50 AM',3),
('147C','Sengottai','09:00 AM',4),
('99A','Rajapalayam','09:15 AM',1),
('99A','Kadayanallur','09:40 AM',2),
('99A','Pavoorchatram','09:50 AM',3),
('99A','Tenkasi Bus Stand','10:00 AM',4);
INSERT INTO bus_stop(bus_number,stop_name,arrival_time,stop_order) VALUES

-- 22D : Tenkasi -> Tirunelveli
('22D','Tenkasi Bus Stand','08:00 AM',1),
('22D','Alangulam','08:20 AM',2),
('22D','Melapalayam','08:40 AM',3),
('22D','Tirunelveli Bus Stand','09:00 AM',4),

-- 210A : Sengottai -> Rajapalayam
('210A','Sengottai','09:10 AM',1),
('210A','Puliyarai','09:25 AM',2),
('210A','Kadayanallur','09:40 AM',3),
('210A','Rajapalayam','10:00 AM',4),

-- 310B : Tirunelveli -> Nagercoil
('310B','Tirunelveli Bus Stand','09:15 AM',1),
('310B','Palayamkottai','09:30 AM',2),
('310B','Valliyur','10:00 AM',3),
('310B','Nagercoil','10:35 AM',4),

-- 450X : Rajapalayam -> Virudhunagar
('450X','Rajapalayam','10:15 AM',1),
('450X','Srivilliputhur','10:35 AM',2),
('450X','Sivakasi','10:55 AM',3),
('450X','Virudhunagar','11:20 AM',4),

-- 520K : Tenkasi -> Kadayanallur
('520K','Tenkasi Bus Stand','08:50 AM',1),
('520K','Pavoorchatram','09:05 AM',2),
('520K','Puliyangudi','09:20 AM',3),
('520K','Kadayanallur','09:35 AM',4),

-- 610M : Kadayanallur -> Rajapalayam
('610M','Kadayanallur','09:45 AM',1),
('610M','Sankarankovil Road','09:55 AM',2),
('610M','Srivilliputhur','10:10 AM',3),
('610M','Rajapalayam','10:20 AM',4);

INSERT INTO route_stops(route_id,stop_name,stop_order,latitude,longitude) VALUES
(1,'Tenkasi Bus Stand',1,8.9598,77.3152),
(1,'Ilanji',2,8.9440,77.3020),
(1,'Courtallam',3,8.9292,77.2756),
(1,'Sengottai',4,8.9735,77.2499),
(2,'Rajapalayam',1,9.4518,77.5533),
(2,'Kadayanallur',2,9.0720,77.3410),
(2,'Pavoorchatram',3,8.9180,77.2900),
(2,'Tenkasi Bus Stand',4,8.9598,77.3152);

INSERT INTO vehicle_route(vehicle_number,route_id,active) VALUES
('147C',1,true),
('99A',2,true),
('101',3,true),
('22D',4,true),
('18A',5,true);

-- SmartNav Demo Data - Part 3
-- Trains + additional demo records

INSERT INTO train(train_name,train_number,source,destination,status) VALUES
('Pothigai Express','12662','Tenkasi','Chennai','Running'),
('Nellai Express','12631','Tirunelveli','Chennai','Running'),
('Sengottai Passenger','56701','Tenkasi','Sengottai','Running'),
('Madurai Passenger','56702','Tenkasi','Madurai','Running'),
('Tenkasi DEMU','76801','Tenkasi','Tirunelveli','Running'),
('Courtallam Special','76802','Tenkasi','Courtallam','Running'),
('Rajapalayam Passenger','76803','Rajapalayam','Tenkasi','Running'),
('Southern Express','12695','Madurai','Tenkasi','Running'),
('Western Link','76804','Sengottai','Tenkasi','Running'),
('Green Line','76805','Puliyangudi','Tenkasi','Running');

INSERT INTO users(name,email,mobile,password,bio,profile_image,created_at) VALUES
('Suresh','suresh@example.com','9876543215','1234','Daily traveller',NULL,NOW()),
('Divya','divya@example.com','9876543216','1234','College Student',NULL,NOW()),
('Rahul','rahul@example.com','9876543217','1234','Office Employee',NULL,NOW()),
('Lakshmi','lakshmi@example.com','9876543218','1234','Tourist',NULL,NOW()),
('Manoj','manoj@example.com','9876543219','1234','Business',NULL,NOW());

INSERT INTO alerts(alert_type,created_time,is_read,message,vehicle_number,priority) VALUES
('Accident',NOW(),false,'Minor accident near Sengottai','147C','HIGH'),
('Diversion',NOW(),false,'Route diverted via Ilanji','99A','MEDIUM'),
('Flood',NOW(),false,'Water logging near Courtallam','101','HIGH'),
('Delay',NOW(),true,'Bus delayed by 10 minutes','22D','LOW'),
('Maintenance',NOW(),false,'Road maintenance near Kadayanallur','18A','MEDIUM');

INSERT INTO feedbacks(category,feedback,created_at) VALUES
('App','Excellent navigation',NOW()),
('Train','Train timings are accurate',NOW()),
('Route','Very useful route suggestions',NOW());

INSERT INTO issue_reports(category,issue_title,description,created_at) VALUES
('Bus','Late Arrival','Bus reached 20 minutes late',NOW()),
('App','Voice Search','Voice search misunderstood destination',NOW()),
('Map','GPS Drift','Current location shifted slightly',NOW());

INSERT INTO search_history(source,destination,transport_type,user_id,searched_at) VALUES
('Tenkasi','Courtallam','BUS',1,NOW()),
('Sengottai','Tenkasi','BUS',2,NOW()),
('Rajapalayam','Tenkasi','BUS',3,NOW()),
('Tenkasi','Tirunelveli','TRAIN',4,NOW());

INSERT INTO saved_route(source,destination,bus_name,vehicle_number,transport_mode,duration,fare,departure_time,arrival_time,user_id,created_at) VALUES
('Rajapalayam','Tenkasi','Rajapalayam Express','99A','BUS',45,20,'09:15 AM','10:00 AM',2,NOW()),
('Tenkasi','Courtallam','Courtallam Shuttle','101','BUS',15,8,'08:15 AM','08:30 AM',3,NOW());

INSERT INTO vehicle_location(vehicle_number,vehicle_type,latitude,longitude,speed,timestamp) VALUES
('101','BUS',8.9292,77.2756,25,NOW()),
('22D','BUS',8.7139,77.7567,40,NOW()),
('18A','BUS',8.9735,77.2499,22,NOW());
INSERT INTO vehicle_location
(vehicle_number,vehicle_type,latitude,longitude,speed,timestamp)
VALUES

('210A','BUS',8.9735,77.2499,28,NOW()),
('310B','BUS',8.7139,77.7567,42,NOW()),
('450X','BUS',9.4518,77.5533,36,NOW()),
('520K','BUS',8.9598,77.3152,24,NOW()),
('610M','BUS',9.0720,77.3410,20,NOW());
