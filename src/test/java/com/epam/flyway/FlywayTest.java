package com.epam.flyway;

import java.util.List;

import com.epam.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlywayTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Order(1)
    void databaseHasBeenInitialized() {

        jdbcTemplate.execute(
                "insert into test_user (username, first_name, last_name) values('ivanspresov', 'Ivan', 'Spresov')"
        );

        final List<User> users = jdbcTemplate
                .query("SELECT username, first_name, last_name FROM test_user", (rs, rowNum) -> new User(
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
        for (User user : users) {
            System.out.println(user);
        }

        Assertions.assertThat(users).isNotEmpty();
    }

    @Test
    @Order(2)
    void insertIntoCompanyTable() {
        //GIVEN
        final String createCompany = "INSERT INTO company(name) VALUES('ЕРАТ')";
        final String queryCompany = "SELECT name FROM company";
        //WHEN
        jdbcTemplate.execute(createCompany);
        //THEN
        List<String> companies = jdbcTemplate.queryForList(queryCompany, String.class);
        assertEquals(1, companies.size());
        assertEquals("ЕРАТ", companies.get(0));
    }

    @Test
    @Order(3)
    void employeeTable() {
        //GIVEN
        final String createCompany = "INSERT INTO company(name) VALUES('ЕРАТ')";
        final String queryCompany = "SELECT id FROM company";
        final String createUser = "insert into test_user (username, first_name, last_name) values('ivanspresov', 'Ivan', 'Spresov')";
        final String queryUser = "SELECT id FROM test_user";

        final String createEmployee = "INSERT INTO employee(company_id, user_id) VALUES(?, ?)";
        jdbcTemplate.execute(createCompany);
        jdbcTemplate.execute(createUser);

        Long companyId = jdbcTemplate.queryForList(queryCompany, Long.class).get(0);
        Long userId = jdbcTemplate.queryForList(queryUser, Long.class).get(0);
        //WHEN
        int rows = jdbcTemplate.update(createEmployee, companyId, userId);
        //THEN
        assertEquals(1, rows);
    }
}