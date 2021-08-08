CREATE TABLE employee
(
    id INT GENERATED ALWAYS AS IDENTITY,
    company_id INT,
    user_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_company FOREIGN KEY(company_id) REFERENCES company(id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES test_user(id)
);