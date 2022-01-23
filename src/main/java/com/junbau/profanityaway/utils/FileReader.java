package com.junbau.profanityaway.utils;

import java.io.*;
import java.util.ArrayList;

public class FileReader {

    private ArrayList<String> textList;

    public FileReader(File file) {
        this.textList = readFile(file);
    }

    public ArrayList<String> getTextList() {
        return textList;
    }

    private ArrayList<String> readFile (File file) {
        try {
            ArrayList<String> hashOfText = new ArrayList<>();
            FileInputStream fs = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String line;
            while((line = br.readLine()) != null) hashOfText.add(line);
            return hashOfText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not read file");
        return null;
    }
}
