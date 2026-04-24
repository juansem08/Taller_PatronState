package com.reality.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/coherence")
@CrossOrigin("*")
public class RealityApplication implements CommandLineRunner {

    private final DataPoint dataPoint = new DataPoint(100.0);

    public static void main(String[] args) {
        SpringApplication.run(RealityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> SIMULACIÓN INICIADA <<<");
    }

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> s = new HashMap<>();
        s.put("phase", dataPoint.getState().getPhaseName());
        s.put("value", dataPoint.processValue());
        s.put("rawReading", dataPoint.getRawSensorReading());
        return s;
    }

    @PostMapping("/next")
    public Map<String, Object> advance() {
        dataPoint.advancePhase();
        return getStatus();
    }

    @PostMapping("/reset")
    public Map<String, Object> reset() {
        dataPoint.setState(new NewtonianState());
        return getStatus();
    }
}

// --- CLASES DE LÓGICA (ESTADOS) ---

class DataPoint {
    private PhysicsState currentState;
    private double rawReading;
    public DataPoint(double r) { this.rawReading = r; this.currentState = new NewtonianState(); }
    public void setState(PhysicsState s) { this.currentState = s; }
    public PhysicsState getState() { return currentState; }
    public double getRawSensorReading() { return rawReading; }
    public double processValue() { return currentState.calculateValue(this); }
    public void advancePhase() { currentState.nextPhase(this); }
}

interface PhysicsState {
    double calculateValue(DataPoint ctx);
    void nextPhase(DataPoint ctx);
    String getPhaseName();
}

class NewtonianState implements PhysicsState {
    public double calculateValue(DataPoint ctx) { return ctx.getRawSensorReading(); }
    public void nextPhase(DataPoint ctx) { ctx.setState(new AnomalousState()); }
    public String getPhaseName() { return "NewtonianState"; }
}

class AnomalousState implements PhysicsState {
    public double calculateValue(DataPoint ctx) { return ctx.getRawSensorReading() * 0.45; }
    public void nextPhase(DataPoint ctx) { ctx.setState(new SpeculativeState()); }
    public String getPhaseName() { return "AnomalousState"; }
}

class SpeculativeState implements PhysicsState {
    public double calculateValue(DataPoint ctx) { return 500.0 + (Math.random() * 50); }
    public void nextPhase(DataPoint ctx) { ctx.setState(new QuantumEntanglementState()); }
    public String getPhaseName() { return "SpeculativeState"; }
}

class QuantumEntanglementState implements PhysicsState {
    public double calculateValue(DataPoint ctx) { return ctx.getRawSensorReading() * Math.PI; }
    public void nextPhase(DataPoint ctx) {}
    public String getPhaseName() { return "QuantumEntanglementState"; }
}
