package com.example.createFileBatch.writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class HeadersCSV implements FlatFileHeaderCallback {

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write("Name,Age,Address");
    }
}
