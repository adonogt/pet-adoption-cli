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

//        File folderDirectory = new File("registered-pets");
//        boolean isDirectoryCreated = folderDirectory.mkdir();
//
//
//        if(isDirectoryCreated){
//            LocalDateTime dateTime = LocalDateTime.now();
//            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
//            System.out.println(dateTime);
//            String petFormat = dateTime.format(formatter);
//            StringBuilder sb = new StringBuilder();
//            String path = "registered-pets";
//            sb.append(path).append(petFormat).append("-").append(pet.name.replace(" ","").toUpperCase());
//            String petFilePathWithName = sb;
//            File petFile = new File(sb);
//            try (FileWriter fw = new FileWriter(file, true);
//                 BufferedWriter bw = new BufferedWriter(fw)) {
//                bw.write("The DevDojo is beautiful, the best course in Brazil!!!");
//                bw.newLine();
//                bw.write("Java is the Way!!");
//                bw.flush();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
    }
}
