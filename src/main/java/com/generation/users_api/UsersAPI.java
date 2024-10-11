package com.generation.users_api;

import com.generation.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class UsersAPI {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String info = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (info != null) {
                sb.append(info);
                info = reader.readLine();
            }

            Gson gsonParse = new Gson();
            List<User> users = gsonParse.fromJson(sb.toString(), new TypeToken<List<User>>(){}.getType());
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("FULL NAME: " + user.getName());
                System.out.println("EMAIL: " + user.getEmail());
                System.out.println("TELEFON: " + user.getPhone());
                System.out.println("KOMPANIYA NOMI: " + user.getCompany().getName());
                System.out.println("ZIPCODE : " + user.getAddress().getZipcode());
                System.out.println("ADDRESS : " + user.getAddress().getCity() + " " + user.getAddress().getSuite() + " " + user.getAddress().getStreet());
                System.out.println("GEO : " + user.getAddress().getGeo().getLat() + " " + user.getAddress().getGeo().getLng());
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
