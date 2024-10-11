package com.generation.comments_api;

import com.generation.model.Comment;
import com.generation.service.DocumentService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
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
            System.err.println(e.getMessage());
        }

        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.of(2024, 10, 9, 17, 38)).getSeconds());

    }
}