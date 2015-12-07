package com.gaohuan.shiro.service;

import com.gaohuan.shiro.dao.ClientDao;
import com.gaohuan.shiro.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gh on 2015/12/7.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    public Client createClient(Client client) {
        return clientDao.createClient(client);
    }

    public Client updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    public void deleteClient(Long clientId) {
        clientDao.deleteClient(clientId);
    }

    public Client findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public Client findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }

    public Client findByClientSecret(String secret) {
        return clientDao.findByClientSecret(secret);
    }
}
