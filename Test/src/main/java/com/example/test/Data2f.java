package com.example.test;

@Entity
@Table(name = "temperature_data")
public class TemperatureData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Double temperature;

    // Getters and setters
}

@Repository
public interface TemperatureDataRepository extends JpaRepository<TemperatureData, Long> {
    List<TemperatureData> findByCategory(String category);
}

@Service
public class TemperatureDataService {
    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    public List<TemperatureData> fetchDataByCategory(String category) {
        return temperatureDataRepository.findByCategory(category);
    }

    public List<TemperatureData> fetchRawData() {
        return temperatureDataRepository.findAll();
    }
}

@RestController
@RequestMapping("/api/dbms")
public class DBMSController {
    @Autowired
    private TemperatureDataService temperatureDataService;

    @GetMapping("/fetchData")
    public ResponseEntity<List<TemperatureData>> fetchData(@RequestParam("category") String category) {
        List<TemperatureData> data = temperatureDataService.fetchDataByCategory(category);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/fetchRawData")
    public ResponseEntity<List<TemperatureData>> fetchRawData() {
        List<TemperatureData> rawData = temperatureDataService.fetchRawData();
        return ResponseEntity.ok(rawData);
    }
}
