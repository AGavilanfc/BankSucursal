package com.optimissa.training.clientservice.repository;

import com.optimissa.training.clientservice.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class ClientRepository implements IClientRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;// Vidal

    public ClientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Client> getClients() {
        String query = "SELECT * FROM CLIENT";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public List<Client> selectByStartWith(String select, String data) {
        String query = "SELECT * FROM CLIENT WHERE "+ select +" LIKE ?";

        return jdbc.query(query, new BeanPropertyRowMapper<>(Client.class), data+"%");

    }

    @Override
    public List<Client> getUserBylimits(int limit, int page) {
        int offset = (page-1)*limit;
        String query = "SELECT * FROM CLIENT LIMIT ? OFFSET ?";

        return jdbc.query(query, new BeanPropertyRowMapper<>(Client.class),limit,offset);

    }

    public int getUserBylimitsCount() {
        String query = "SELECT COUNT(*) FROM CLIENT";
        return jdbc.queryForObject(query, Integer.class);

    }



    public Client getClientById(int id) {
        String query = "SELECT * FROM CLIENT WHERE ID = ?";
        return jdbc.queryForObject(query, new BeanPropertyRowMapper<>(Client.class), id);
    }

    public Client getClientByIdentifier(String identifier) {
        String query = "SELECT * FROM CLIENT WHERE IDENTIFIER = ?";
        return jdbc.queryForObject(query, new BeanPropertyRowMapper<>(Client.class), identifier);
    }

    public Client getLastClient() {
        String query = "SELECT * FROM CLIENT WHERE ID = (SELECT MAX(ID) FROM CLIENT)";
        return jdbc.queryForObject(query, new BeanPropertyRowMapper<>(Client.class));
    }

    @GetMapping("/get-by-userId")
    public List<Client> getClientByUserId(int id) {
        String query = "SELECT * FROM CLIENT WHERE USER_ID = ?";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Client.class), id);
    }

    public int insertClient(Client newClient) {
        String query = "INSERT INTO CLIENT (NAME, LAST_NAME1, LAST_NAME2, EMAIL, PHONE, IDENTIFIER, USER_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(query,
                newClient.getName(),
                newClient.getLastName1(),
                newClient.getLastName2(),
                newClient.getEmail(),
                newClient.getPhone(),
                newClient.getIdentifier(),
                newClient.getUserId()
        );
    }

    public int deleteClient(int id) {
        String query = "UPDATE CLIENT SET ACTIVE = 0 WHERE ID = ?";
        return jdbc.update(query, id);
    }

    public int updateClient(Client modifiedClient, int id) {

        String query = "UPDATE CLIENT SET NAME = :name, " +
                "LAST_NAME1 = :lastName1, " +
                "LAST_NAME2 = :lastName2, " +
                "EMAIL = :email, " +
                "PHONE = :phone, " +
                "IDENTIFIER = :identifier, " +
                "USER_ID = :userId " +
                "WHERE ID = :id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", modifiedClient.getName())
                .addValue("lastName1", modifiedClient.getLastName1())
                .addValue("lastName2", modifiedClient.getLastName2())
                .addValue("email", modifiedClient.getEmail())
                .addValue("phone", modifiedClient.getPhone())
                .addValue("identifier", modifiedClient.getIdentifier())
                .addValue("userId", modifiedClient.getUserId())
                .addValue("id", id);


        return namedJdbc.update(query, params);
    }
    public int updateUserStatus(int id, int activate) {
        String query = "UPDATE CLIENT SET ACTIVE = ? WHERE ID = ?";

        return jdbc.update(query, activate, id);

    }


}
