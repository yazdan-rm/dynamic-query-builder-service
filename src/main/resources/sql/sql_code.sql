INSERT INTO person (id, name)
VALUES
    (1, 'Alice'),
    (2, 'Bob'),
    (3, 'Charlie');


INSERT INTO job (id, job_name, salary, fk_person)
VALUES
    (1, 'Software Engineer', 80000, 1),
    (2, 'Data Scientist', 95000, 2),
    (3, 'Product Manager', 100000, 3);


INSERT INTO location (id, city, fk_job)
VALUES
    (1, 'New York', 1),
    (2, 'San Francisco', 2),
    (3, 'Los Angeles', 3);
