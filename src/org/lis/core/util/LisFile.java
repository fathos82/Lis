package org.lis.core.util;

import org.lis.core.component.LisIOComponent;
import org.lis.core.component.LisValidationComponent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LisFile extends LisIOComponent<LisFile> {

    private final String source;

    public LisFile(String filePath) {
        LisValidationComponent<String> validator = new FileExtensionValidator("lis");
        validator.validate(filePath);
        source = extract(filePath);
    }

    private String extract(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public String getSource() {
        return source;
    }
}
