package com.generation;

import com.generation.service.DocumentService;
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

public class CommentsAPI {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/comments");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String readLine = reader.readLine();
            while (readLine != null){
                sb.append(readLine);
                readLine = reader.readLine();
            }

            Gson gson = new Gson();
            List<Comment> comments = gson.fromJson(sb.toString(), new TypeToken<List<Comment>>(){});

            DocumentService documentService = new DocumentService();
            documentService.createDocumentForComments(comments);

        } catch (IOException e) {
            System.err.println(e);
        }


    }
}