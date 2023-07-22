INSERT INTO schedule (type,days,time_duration,created_date,created_by) VALUES ('WEEK','Week Days','6.30-7.00',CURRENT_TIMESTAMP,'shehan');
INSERT INTO schedule (type,days,time_duration,created_date,created_by) VALUES ('WEEKEND','Saturday','5.30-6.30',CURRENT_TIMESTAMP,'shehan');
INSERT INTO schedule (type,days,time_duration,created_date,created_by) VALUES ('WEEK','Sunday','5.30-6.30',CURRENT_TIMESTAMP,'shehan');
INSERT INTO schedule (type,days,time_duration,created_date,created_by) VALUES ('WEEK','Sunday','8.30-9.30',CURRENT_TIMESTAMP,'shehan');
INSERT INTO schedule (type,days,time_duration,created_date,created_by) VALUES ('WEEK','Sunday','6.00-7.30',CURRENT_TIMESTAMP,'shehan');

--ROLES will be manually added since roles are not frequently inserting
INSERT INTO role (role_name,created_date,created_by,modified_by,modified_date) VALUES ('ADMIN',CURRENT_TIMESTAMP,'DBA',null,null);
INSERT INTO role (role_name,created_date,created_by,modified_by,modified_date) VALUES ('USER',CURRENT_TIMESTAMP,'DBA',null,null);