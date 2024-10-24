use online_banking_userdb;

-- Create the 'users' table
CREATE TABLE users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    PRIMARY KEY (user_id)
) ENGINE=InnoDB;

-- Create the 'roles' table
CREATE TABLE roles (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
) ENGINE=InnoDB;

-- Create the 'permissions' table
CREATE TABLE permissions (
    permission_id BIGINT NOT NULL AUTO_INCREMENT,
    permission_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (permission_id)
) ENGINE=InnoDB;

-- Create the 'role_permissions' table (Many-to-Many relationship between roles and permissions)
CREATE TABLE role_permissions (
    role_permission_id BIGINT NOT NULL AUTO_INCREMENT,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(permission_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Create the 'user_roles' table (Many-to-Many relationship between users and roles)
CREATE TABLE user_roles (
    user_role_id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Insert users
INSERT INTO users (username, password, created_at, updated_at)
VALUES ('administrator_user', '$2a$12$ntO5mjL92pnA8HFpf9xP0OPonobkBAG0rUh.1DHGUGkyaJetXhqxK', NOW(), NOW());


-- Insert roles
INSERT INTO roles (role_name) VALUES
                                  ('Customer'),
                                  ('Bank Teller'),
                                  ('Loan Officer'),
                                  ('Account Manager'),
                                  ('Administrator'),
                                  ('Compliance Officer'),
                                  ('IT Support'),
                                  ('Auditor'),
                                  ('Financial Analyst');

-- Insert user_roles
INSERT INTO user_roles (role_id, user_id) VALUES (1, 1);

-- Insert permissions
INSERT INTO permissions (permission_name) VALUES
                                              ('CREATE_ACCOUNT'),
                                              ('VIEW_ACCOUNT'),
                                              ('TRANSFER_FUNDS'),
                                              ('PAY_BILLS'),
                                              ('UPDATE_INFO'),
                                              ('APPLY_LOAN'),
                                              ('VIEW_TRANSACTIONS'),
                                              ('PROCESS_DEPOSITS'),
                                              ('PROCESS_WITHDRAWALS'),
                                              ('HANDLE_CASH_TRANSACTIONS'),
                                              ('APPROVE_LOAN_APPLICATIONS'),
                                              ('MANAGE_LOAN_ACCOUNTS'),
                                              ('PROVIDE_FINANCIAL_ADVICE'),
                                              ('MONITOR_ACCOUNT_ACTIVITY'),
                                              ('CREATE_USER_ACCOUNTS'),
                                              ('GENERATE_REPORTS'),
                                              ('REVIEW_COMPLIANCE_ISSUES'),
                                              ('CONDUCT_AUDITS'),
                                              ('ACCESS_FINANCIAL_RECORDS'),
                                              ('ANALYZE_FINANCIAL_DATA');

-- Mapping for Customer
INSERT INTO role_permissions (role_id, permission_id) VALUES
                                                          (1, 1),  -- CREATE_ACCOUNT
                                                          (1, 2),  -- VIEW_ACCOUNT
                                                          (1, 3),  -- TRANSFER_FUNDS
                                                          (1, 4),  -- PAY_BILLS
                                                          (1, 5),  -- UPDATE_INFO
                                                          (1, 6);  -- APPLY_LOAN

-- Mapping for Bank Teller
INSERT INTO role_permissions (role_id, permission_id) VALUES
                                                          (2, 2),  -- VIEW_ACCOUNT
                                                          (2, 8),  -- PROCESS_DEPOSITS
                                                          (2, 9);  -- PROCESS_WITHDRAWALS

-- Mapping for Loan Officer
INSERT INTO role_permissions (role_id, permission_id) VALUES
                                                          (3, 6),  -- APPLY_LOAN
                                                          (3, 10); -- APPROVE_LOAN_APPLICATIONS

-- Mapping for Account Manager
INSERT INTO role_permissions (role_id, permission_id) VALUES
                                                          (4, 1),  -- CREATE_ACCOUNT
                                                          (4, 2),  -- VIEW_ACCOUNT
                                                          (4, 12); -- PROVIDE_FINANCIAL_ADVICE

-- Mapping for Administrator
INSERT INTO role_permissions (role_id, permission_id) VALUES
                                                          (5, 14), -- CREATE_USER_ACCOUNTS
                                                          (5, 15); -- GENERATE_REPORTS

-- Mapping for Compliance Officer
INSERT INTO role_permissions (role_id, permission_id) VALUES
    (6, 17); -- REVIEW_COMPLIANCE_ISSUES

-- Mapping for IT Support
INSERT INTO role_permissions (role_id, permission_id) VALUES
    (7, 18); -- ACCESS_FINANCIAL_RECORDS

-- Mapping for Auditor
INSERT INTO role_permissions (role_id, permission_id) VALUES
    (8, 19); -- ANALYZE_FINANCIAL_DATA

-- Mapping for Financial Analyst
INSERT INTO role_permissions (role_id, permission_id) VALUES
    (9, 20); -- ACCESS_FINANCIAL_RECORDS         -- Bank_Teller permissions

-- View the data


-- View the data
SELECT * FROM users;
SELECT * FROM user_roles order by user_role_id;
SELECT * FROM roles order by role_id;
SELECT * FROM permissions order by permission_id;
SELECT * FROM role_permissions order by role_permission_id;

-- Role and Permission Mapping
select role_name, permission_name 
from roles 
join role_permissions using (role_id)
join permissions using (permission_id);
