package com.gus.pet.repository;

import com.gus.pet.entity.Pet;
import com.gus.pet.entity.petSex;
import com.gus.pet.entity.petType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.gus.pet.entity.Pet.NOT_PROVIDED;

public class PetRepository {
    File file = new File("src/resources/pet_registration_form.txt");
    File folderDirectory = new File("registered-pets/");


    public void readForm(short lineNumber) {
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

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void savePet(Pet pet) {


        if (folderDirectory.exists() && folderDirectory.isDirectory()) {
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
        } else {
            folderDirectory.mkdir();
        }

    }

    public Pet loadPet(File petFile) {
        Pet oldPet = new Pet();
        if (folderDirectory.exists() && folderDirectory.isDirectory()) {
            String line;
            try (FileReader fr = new FileReader(petFile);
                 BufferedReader br = new BufferedReader(fr)) {
                oldPet.name = br.readLine().substring(4);
                line = br.readLine().substring(4);
                if (line.equals("Dog")) {
                    oldPet.type = petType.PET_TYPE_DOG;
                } else if (line.equals("Cat")) {
                    oldPet.type = petType.PET_TYPE_CAT;
                } else {
                    oldPet.type = petType.PET_TYPE_NOT_PROVIDED;
                }
                line = br.readLine().substring(4);
                if (line.equals("Male")) {
                    oldPet.sex = petSex.PET_SEX_MALE;
                } else if (line.equals("Female")) {
                    oldPet.sex = petSex.PET_SEX_FEMALE;
                } else {
                    oldPet.sex = petSex.PET_SEX_NOT_PROVIDED;
                }
                oldPet.address = br.readLine().substring(4);
                oldPet.age = br.readLine().substring(4).replace("years old","").trim();
                oldPet.weight = br.readLine().substring(4).replace("kg","").trim();
                oldPet.breed = br.readLine().substring(4);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return oldPet;
    }


    public void listAllPet() {

        if (folderDirectory.exists() && folderDirectory.isDirectory()) {
            File[] fileList = folderDirectory.listFiles();

            for (int i = 0; i < fileList.length; i++) {

               Pet pet = loadPet(fileList[i]);

                if(pet!= null){
                    System.out.print((i+1)+" - ");
                    pet.printPet();
                }

            }
            System.out.println("\n\n End of list");
        }
    }

    public boolean removePet(File petfile) {
      return petfile.delete();
    }

    public void overwritePet(File path, Pet over) {
        removePet(path);
        savePet(over);
    }

    public File[] searchPets(Pet target) {

        File[] petFound = null;
        short foundPetIndex = 0;
        if (folderDirectory.exists() && folderDirectory.isDirectory()) {
            File[] fileList = folderDirectory.listFiles();
            petFound = new File[fileList.length];
            boolean petMatch = false;
            for (int i = 0; i < fileList.length; i++) {
                Pet currentPet = loadPet(fileList[i]);
                if (currentPet.type == target.type) {
                    petMatch = true;
                    if (!target.name.equals(NOT_PROVIDED)) {
                        if (!currentPet.name.toLowerCase().contains(target.name.toLowerCase())) {
                            petMatch = false;
                        }
                    }
                    if (target.sex != petSex.PET_SEX_NOT_PROVIDED) {
                        if (currentPet.sex != target.sex) {
                            petMatch = false;
                        }
                    }
                    if (!target.address.equals(NOT_PROVIDED)) {
                        if (!currentPet.address.toLowerCase().contains(target.address.toLowerCase())) {
                            petMatch = false;
                        }
                    }
                    if (!target.age.equals(NOT_PROVIDED)) {
                        if (!currentPet.age.contains(target.age)) {
                            petMatch = false;
                        }
                    }
                    if (!target.breed.equals(NOT_PROVIDED)) {
                        if (!currentPet.breed.toLowerCase().contains(target.breed.toLowerCase())) {
                            petMatch = false;
                        }
                    }

                    if (petMatch) {
                        System.out.print((foundPetIndex + 1) + ". ");
                        currentPet.printPet();
                        petFound[foundPetIndex] = fileList[i];
                        foundPetIndex++;
                    }
                }
            }
            System.out.println("End of search.");
        }
        return petFound;
    }
}
