package com.ameri.operation;

import java.io.BufferedReader;
import java.io.IOException;

public class Reader {

    private final BufferedReader reader;

    public Reader(BufferedReader reader) {
        this.reader = reader;
    }

    public String getInformation() throws IOException {
        String line = reader.readLine();
        String body="";
        while(line !=null){
            body+=line;
            line= reader.readLine();
        }

        return body;
    }
}
