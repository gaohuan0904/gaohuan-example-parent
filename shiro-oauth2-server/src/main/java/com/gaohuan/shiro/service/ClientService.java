package com.gaohuan.shiro.service;

import com.gaohuan.shiro.entity.Client;

import java.util.List;

public interface ClientService {

    public Client createClient(Client client);

    public Client updateClient(Client client);

    public void deleteClient(Long clientId);

    public Client findOne(Long clientId);

    public List<Client> findAll();

    public Client findByClientId(String clientId);

    public Client findByClientSecret(String secret);

}