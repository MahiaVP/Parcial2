package ui;

import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {
    private String fileName;
    private FileWriter fileWriter;

    public TextFileWriter(String fileName){
        this.fileName = fileName;
    }

    public void openFile() throws IOException{
        fileWriter = new FileWriter(fileName);
    }

    public void writeToFile(String content) throws IOException{
        if(fileWriter == null){
            throw new IllegalStateException("File is not open. Call openFile() first.");
        }
        fileWriter.write(content);
    }

    public void closeFile() throws IOException{
        if (fileWriter != null){
            fileWriter.close();
        }
    }
}
