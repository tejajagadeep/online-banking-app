create table permissions (
	permission_id bigint not null, 
	permission_name varchar(255), 
	primary key (permission_id)
);

CREATE TABLE role_permissions (
    role_permission_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (role_permission_id)
);

CREATE TABLE roles (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user_roles (
    user_role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (user_role_id)
);

CREATE TABLE users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6),
    password VARCHAR(255) NOT NULL,
    updated_at DATETIME(6),
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

