package com.maher.hospitale;

import com.maher.hospitale.entities.Patient;
import com.maher.hospitale.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HospitaleApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository ;
    public static void main(String[] args) {
        SpringApplication.run(HospitaleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null , "Mohamed" , new Date() , false , 200)) ;
        patientRepository.save(new Patient(null , "Hamid" , new Date() , true , 600)) ;
        patientRepository.save(new Patient(null , "Rabie" , new Date() , false , 1000)) ;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }
}
