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
    private JavaBackEndService javaBackEndService; // Inject JavaBackEnd-tjänst

    @GetMapping("/fetchProcessedData")
    public ResponseEntity<Object> fetchProcessedDataFromDB() {
        try {
            // Anropa metoden i JavaBackEnd-tjänsten för att hämta bearbetad data från DBMS
            ProcessedData processedData = javaBackEndService.fetchProcessedDataFromDB();
            // Konvertera data till ett format som är användarvänligt för frontend om det behövs
            FrontendData frontendData = convertToFrontendFormat(processedData);
            // Returnera data som ett JSON-svar
            return ResponseEntity.ok(frontendData);
        } catch (Exception e) {
            // Hantera eventuella fel här
        }
    }

    @GetMapping("/fetchRawData")
    public ResponseEntity<Object> fetchRawDataFromDB() {
        try {
            // Anropa metoden i JavaBackEnd-tjänsten för att hämta rå temperaturdata från DBMS
            RawData rawData = javaBackEndService.fetchRawDataFromDB();
            // Returnera rådata som ett JSON-svar
            return ResponseEntity.ok(rawData);
        } catch (Exception e) {
            // Hantera eventuella fel här
        }
    }

    // Hjälpmetod för att konvertera data till ett användarvänligt format för frontend om det behövs
    private FrontendData convertToFrontendFormat(ProcessedData processedData) {
        // Implementera din logik för datakonvertering här
        // Till exempel, skapa en ny FrontendData-objekt med önskad struktur och data
        FrontendData frontendData = new FrontendData();
        frontendData.setData1(processedData.getSomeValue1());
        frontendData.setData2(processedData.getSomeValue2());
        // ...
        return frontendData;
    }
}
