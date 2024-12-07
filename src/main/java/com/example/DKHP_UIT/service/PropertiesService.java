package com.example.DKHP_UIT.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionStaff;
import com.example.DKHP_UIT.response.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Builder
@Data
public class PropertiesService {
    private final String propertiesFilePath = "src/main/resources/application.properties";

    // Read a property value
    public String getProperty(String key) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(inputStream);
        }
        return properties.getProperty(key);
    }

    // Update or add a property
    public void updateProperty(int year, int semester) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(inputStream);
        }

        // Update the property value
        properties.setProperty("app.currentYear", year + "");
        properties.setProperty("app.currentSemester", semester + "");

        // Save the properties back to the file
        try (OutputStream outputStream = new FileOutputStream(propertiesFilePath)) {
            properties.store(outputStream, null);
        }
    }

    public ResponseEntity updateProperty1(int year, int semester) {
        try {
            updateProperty(year, semester);
        } catch (IOException e) {
            throw new ExceptionStaff(ExceptionCode.SetSemesterAndYear);
        }

        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.SetSemesterAndYear));
    }
}
