package com.gus.pet.repository;

import com.gus.pet.entity.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    File file = new File("src/resources/pet_registration_form.txt");


    public boolean readForm(short lineNumber) {

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line = null;
            if(lineNumber == 1){
                line = br.readLine();
            }
            else{
                    line = br.readLine();
                for (short indexLine = 1; indexLine < (lineNumber); indexLine++){
                    line = br.readLine();
                    line = br.readLine();
                }
            }
            if ((line != null)){
                System.out.println(line);
                return true;
            }
            else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return  false;

    }

    public void savePet(Pet pet){

        File folderDirectory = new File("registered-pets");
        boolean isDirectoryCreated = folderDirectory.mkdir();


            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
            System.out.println(dateTime);
            String petFormat = dateTime.format(formatter);
            StringBuilder sb = new StringBuilder();
            String path = "registered-pets/";
            sb.append(path).append(petFormat).append("-").append(pet.name.replace(" ","").toUpperCase()).append(".txt");
            String petFilePathWithName = sb.toString();
            File petFile = new File(petFilePathWithName);
            System.out.println("File Path and Name: "+petFilePathWithName);
            System.out.println("Name: " + pet.name);
            System.out.println("City: " + pet.addrCity);
            System.out.println("Street: " + pet.addrStreet);
            System.out.println("House Number: " + pet.addrHouseNumber);
            System.out.println("Age: " + pet.age);
            System.out.println("Weight: " + pet.weight);
            System.out.println("Breed: " + pet.breed);
            try (FileWriter fw = new FileWriter(petFile, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("File Write Success");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
}
