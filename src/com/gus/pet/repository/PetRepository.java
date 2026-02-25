package com.gus.pet.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
}
