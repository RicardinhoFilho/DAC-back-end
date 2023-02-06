package com.bantads.orquestrador.bantadsorquestrador.services.rest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.client.RestTemplate;

import com.bantads.orquestrador.bantadsorquestrador.dto.ClienteDTO;
import com.bantads.orquestrador.bantadsorquestrador.utils.Urls;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestCliente {
    static public ClienteDTO GetClienteByCpf(String cpf) {
        try {

            URL url = new URL(Urls.API_CLIENTE_URL + "/cliente/por-cpf/" + cpf);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            int responsecode = conn.getResponseCode();

            Scanner scanner = new Scanner(url.openStream());
            String inline = "";

            // Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            System.out.println(inline);
            ObjectMapper m = new ObjectMapper();
            ClienteDTO data = m.readValue(inline, ClienteDTO.class);

            // ClienteDTO cliente = (ClienteDTO) test;
            return data;
        } catch (Exception ex) {

            return null;
        }

    }

    static public ClienteDTO GetClienteByEmail(String email) {
        try {

            URL url = new URL(Urls.API_CLIENTE_URL + "/cliente/por-email/" + email);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            int responsecode = conn.getResponseCode();

            Scanner scanner = new Scanner(url.openStream());
            String inline = "";

            // Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            System.out.println(inline);
            ObjectMapper m = new ObjectMapper();
            ClienteDTO data = m.readValue(inline, ClienteDTO.class);

            // ClienteDTO cliente = (ClienteDTO) test;
            return data;
        } catch (Exception ex) {

            return null;
        }

    }
}
