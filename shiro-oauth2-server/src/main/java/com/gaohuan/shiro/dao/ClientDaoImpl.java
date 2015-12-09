package com.gaohuan.shiro.dao;

import com.gaohuan.shiro.entity.Client;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gh on 2015/12/7.
 */
@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Client createClient(final Client client) {
        final String sql = "INSERT  INTO  oauth2_client(client_name,client_id,client_secret) VALUES (?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(sql, new String[]{"id"});
                stmt.setString(1, client.getClientName());
                stmt.setString(2, client.getClientId());
                stmt.setString(3, client.getClientSecret());
                return stmt;
            }
        }, keyHolder);

        client.setId(keyHolder.getKey().longValue());

        return client;
    }

    public Client updateClient(Client client) {
        if (client == null) {
            return null;
        }
        String sql = "update oauth2_client set client_name=?,client_secret=?,client_id=? where id=?";
        jdbcTemplate.update(sql, client.getClientName(), client.getClientSecret(), client.getClientId(), client.getId());

        return client;
    }

    public void deleteClient(Long clientId) {
        if (clientId == null) return;
        jdbcTemplate.update("delete FROM oauth2_client WHERE  id=?", clientId);
    }

    public Client findOne(Long clientId) {
        String sql = "SELECT  * FROM oauth2_client where id=?";
        List<Client> clients = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if (CollectionUtils.isEmpty(clients)) {
            return null;
        }
        return clients.get(0);
    }

    public List<Client> findAll() {
        String sql = "SELECT  * FROM oauth2_client ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class));

    }

    public Client findByClientId(String clientId) {
        String sql = "SELECT  * FROM oauth2_client where client_id=?";
        List<Client> clients = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if (CollectionUtils.isEmpty(clients)) {
            return null;
        }
        return clients.get(0);
    }

    public Client findByClientSecret(String secret) {
        String sql = "SELECT  * FROM oauth2_client where client_secret=?";
        List<Client> clients = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), secret);
        if (CollectionUtils.isEmpty(clients)) {
            return null;
        }
        return clients.get(0);
    }
}
