package com.salac.petstore.api;

import com.salac.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salac/debug")
public class DebugController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/db")
    public ResponseEntity<String> checkDb() {
        try {
            long count = petRepository.count();
            return ResponseEntity.ok("pet count=" + count);
        } catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("DB check failed: ").append(ex.toString());
            for (StackTraceElement e : ex.getStackTrace()) {
                sb.append("\n    at ").append(e.toString());
            }
            return ResponseEntity.status(500).body(sb.toString());
        }
    }
}
