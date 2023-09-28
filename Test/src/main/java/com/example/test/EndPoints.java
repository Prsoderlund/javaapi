package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b2f")
public class B2FController {

    @Autowired
    private JavaBackEndService javaBackEndService; // Inject your JavaBackEnd service

    @GetMapping("/fetchProcessedData")
    public ResponseEntity<Object> fetchProcessedDataFromDB() {
        try {
            // Call the JavaBackEnd service method to fetch processed data from DBMS
            ProcessedData processedData = javaBackEndService.fetchProcessedDataFromDB();
            // Convert data to a Frontend-friendly format if needed
            FrontendData frontendData = convertToFrontendFormat(processedData);
            // Return the data as a JSON response
            return ResponseEntity.ok(frontendData);
        } catch (Exception e) {
            // Handle any errors and return appropriate error messages
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching processed data from DBMS.");
        }
    }

    @GetMapping("/fetchRawData")
    public ResponseEntity<Object> fetchRawDataFromDB() {
        try {
            // Call the JavaBackEnd service method to fetch raw temperature data from DBMS
            RawData rawData = javaBackEndService.fetchRawDataFromDB();
            // Return the raw data as a JSON response
            return ResponseEntity.ok(rawData);
        } catch (Exception e) {
            // Handle any errors and return appropriate error messages
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching raw temperature data from DBMS.");
        }
    }

    // Helper method to convert data to Frontend-friendly format if needed
    private FrontendData convertToFrontendFormat(ProcessedData processedData) {
        // Implement your data conversion logic here
        // For example, create a new FrontendData object with the desired structure and data
        FrontendData frontendData = new FrontendData();
        frontendData.setData1(processedData.getSomeValue1());
        frontendData.setData2(processedData.getSomeValue2());
        // ...
        return frontendData;
    }
}
