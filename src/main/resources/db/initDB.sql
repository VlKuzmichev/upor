DROP TABLE IF EXISTS departments cascade;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS devs_comm;
DROP TABLE IF EXISTS devs_wire;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE departments
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL,
    full_name        VARCHAR
);
CREATE UNIQUE INDEX depart_unique_name_idx ON departments (name);

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  full_name        VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  department_id    INTEGER                 NOT NULL,
  FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_department_email_idx ON users (department_id, email);

CREATE TABLE user_roles
(
  user_id          INTEGER                 NOT NULL,
  role             VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE devs_comm (
                         id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                         date_time   TIMESTAMP         NOT NULL,
                         reg_list    VARCHAR(30),
                         type_comm   VARCHAR(50)       NOT NULL,
                         location    VARCHAR(50)       NOT NULL,
                         defect      VARCHAR(255)      NOT NULL,
                         description TEXT              NOT NULL,
                         resp_empl   VARCHAR(30)       NOT NULL,
                         status      BOOL DEFAULT TRUE NOT NULL,
                         user_id     INTEGER           NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE devs_wire (
                         id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                         date_time   TIMESTAMP         NOT NULL,
                         reg_list    VARCHAR(30),
                         type_comm   VARCHAR(50)       NOT NULL,
                         location    VARCHAR(50)       NOT NULL,
                         defect      VARCHAR(255)      NOT NULL,
                         description TEXT              NOT NULL,
                         resp_empl   VARCHAR(30)       NOT NULL,
                         status      BOOL DEFAULT TRUE NOT NULL,
                         user_id     INTEGER           NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);