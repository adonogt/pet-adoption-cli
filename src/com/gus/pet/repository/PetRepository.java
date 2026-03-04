package com.gus.pet.repository;

import com.gus.pet.entity.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetRepository {
    File file = new File("src/resources/pet_registration_form.txt");
    File folderDirectory = new File("registered-pets");


    public boolean readForm(short lineNumber) {

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line = null;
            if (lineNumber == 1) {
                line = br.readLine();
            } else {
                line = br.readLine();
                for (short indexLine = 1; indexLine < (lineNumber); indexLine++) {
                    line = br.readLine();
                    line = br.readLine();
                }
            }
            if ((line != null)) {
                System.out.println(line);
                return true;
            } else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

    public void savePet(Pet pet) {


        if(folderDirectory.exists()&& folderDirectory.isDirectory()) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
            String petFormat = dateTime.format(formatter);
            StringBuilder sb = new StringBuilder();
            String path = "registered-pets/";
            sb.append(path).append(petFormat).append("-").append(pet.name.replace(" ", "").toUpperCase()).append(".txt");
            String petFilePathWithName = sb.toString();
            File petFile = new File(petFilePathWithName);
            try (FileWriter fw = new FileWriter(petFile, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {

                bw.write("1 - ");
                bw.write(pet.name);
                bw.newLine();
                bw.write("2 - ");
                bw.write(pet.type.getType());
                bw.newLine();
                bw.write("3 - ");
                bw.write(pet.sex.getSex());
                bw.newLine();
                bw.write("4 - ");
                bw.write(pet.address);
                bw.newLine();
                bw.write("5 - ");
                bw.write(pet.age);
                bw.write(" years old");
                bw.newLine();
                bw.write("6 - ");
                bw.write(pet.weight);
                bw.write(" kg");
                bw.newLine();
                bw.write("7 - ");
                bw.write(pet.breed);
                bw.newLine();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            folderDirectory.mkdir();
        }

    }

    public String readPetFile(File petFile){

        StringBuilder sb = new StringBuilder();

        try (FileReader fr = new FileReader(petFile);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
               sb.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public void listAllPet(){
        if(folderDirectory.exists()&& folderDirectory.isDirectory())
        {
            File[] fileList = folderDirectory.listFiles();

            for (int i = 0; i < fileList.length; i++) {
                //System.out.println(fileList[i].getName());
                System.out.print("\n "+(i+1)+" - ");
                try (FileReader fr = new FileReader(fileList[i]);
                     BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.print(line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("\n\n End of list");
        }
    }

    public void listPetWithFilters(String keyword){

        Pattern pattern = Pattern.compile(keyword);

        if(folderDirectory.exists()&& folderDirectory.isDirectory())
        {
            File[] fileList = folderDirectory.listFiles();

            for (int i = 0; i < fileList.length; i++) {
                Matcher matcher = pattern.matcher(readPetFile(fileList[i]));
                if (matcher.find()) {
                    System.out.println("\nResults");
                    System.out.print(+(i+1)+" - ");
                    System.out.println(readPetFile(fileList[i]));
                    System.out.println("\nEnd of Results");
                }
            }
        }
    }


}
