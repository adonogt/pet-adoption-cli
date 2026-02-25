package com.gus.pet.service;

import com.gus.pet.entity.Pet;
import com.gus.pet.repository.PetRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PetService {
    private Scanner input;

    public PetService(Scanner keyboard) {
        this.input = keyboard;
    }

    PetRepository repository = new PetRepository();
    Pet pet = new Pet();


    public void registerPet() {
        short counter = 1;

        while (counter < 8) {
            switch (counter) {
                case 1:
                    repository.readForm(counter);
                    String answer = input.nextLine();
                    String regex = "([a-zA-Z])+(\\s)+([a-zA-Z])";
                    Pattern patter = Pattern.compile(regex);
                    Matcher matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.name = matcher.group();

                    } else {
                        System.out.println("Not Informed");
                    }
                    counter++;
                    break;
                case 2:
                    repository.readForm(counter);
                    counter++;
                    break;
                case 3:
                    repository.readForm(counter);
                    counter++;
                    break;
                case 4:
                    repository.readForm(counter);
                    counter++;
                    break;
                case 5:
                    repository.readForm(counter);
                    counter++;
                    break;
                case 6:
                    repository.readForm(counter);
                    counter++;
                    break;
                case 7:
                    repository.readForm(counter);
                    counter++;
                    break;

            }
        }
    }

    public void updatePet() {

    }

    public void deletePet() {

    }

    public void listAllPets() {

    }

    public void listPetWithFilters() {

    }


}
