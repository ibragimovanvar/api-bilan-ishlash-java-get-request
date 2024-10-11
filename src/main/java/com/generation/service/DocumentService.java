package com.generation.service;

import com.generation.model.Comment;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DocumentService {

    public void createDocumentForComments(List<Comment> comments) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph headerParahraph = document.createParagraph();
        headerParahraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = headerParahraph.createRun();
        run.setText("TARMOQDAGI HAMMA BERILGAN IZOHLAR");
        run.setFontSize(17);
        run.setBold(true);
        run.addBreak();

        for (Comment comment : comments) {
            XWPFParagraph commentParahraph = document.createParagraph();
            XWPFRun commentParahraphRun = commentParahraph.createRun();
            commentParahraphRun.setBold(true);
            commentParahraphRun.setFontSize(11);
            commentParahraphRun.setText("COMMENT ID: " + comment.getId());
            commentParahraphRun.addBreak();
            commentParahraphRun.setText("POST ID: " + comment.getPostId());
            commentParahraphRun.addBreak();
            commentParahraphRun.setText("EMAIL : " + comment.getEmail());
            commentParahraphRun.addBreak();
            commentParahraphRun.setText("IZOH NOMI : " + comment.getName());
            commentParahraphRun.addBreak();
            commentParahraphRun.setText("BERILGAN IZOH : " + comment.getBody());
            commentParahraphRun.addBreak();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = formatter.format(LocalDateTime.now());

        XWPFParagraph dateParagraph = document.createParagraph();
        XWPFRun dateParagraphRun = dateParagraph.createRun();
        dateParagraphRun.setText("Dokument yaratilgan sana: " + formattedDate);

        try(FileOutputStream out = new FileOutputStream("tarmoqdagi-commentlar.docx")) {
            document.write(out);
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
