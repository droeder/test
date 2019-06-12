CREATE TABLE employee (
  emp_id INTEGER NOT NULL,
  emp_name VARCHAR(120),
  dep_id INTEGER NOT NULL,
  job INTEGER NOT NULL,
  PRIMARY KEY(emp_id),
  FOREIGN KEY (dep_id) REFERENCES department(department_id),
  FOREIGN KEY (job) REFERENCES job(job_id)
);

CREATE TABLE department (
  department_id INTEGER NOT NULL,
  dep_name VARCHAR(120),
  PRIMARY KEY(department_id)
);


CREATE TABLE job (
    job_id INTEGER NOT NULL,
    job_name VARCHAR(120),
    PRIMARY KEY(job_id)
);

INSERT INTO job (job_id, job_name) VALUES (1, 'devOps');

INSERT INTO department (department_id, dep_name) VALUES (1, 'development');
INSERT INTO department (department_id, dep_name) VALUES (2, 'sales');


INSERT INTO employee (emp_id, emp_name, dep_id, job_id) VALUES (101, 'Chairman', 1, 1);
INSERT INTO employee (emp_id, emp_name, dep_id, job_id) VALUES (102, 'Mairman', 2, 1);
