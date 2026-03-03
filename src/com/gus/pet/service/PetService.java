package com.gus.pet.service;

import com.gus.pet.entity.Pet;
import com.gus.pet.entity.petSex;
import com.gus.pet.entity.petType;
import com.gus.pet.repository.PetRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gus.pet.entity.Pet.NOT_PROVIDED;


public class PetService {
    private Scanner input;

    public PetService(Scanner keyboard) {
        this.input = keyboard;
    }

    PetRepository repository = new PetRepository();


    public void registerPet() {
        Pet pet = new Pet();
        short counter = 1;
        String answer;
        String regex;
        Pattern patter;
        Matcher matcher;

        while (counter < 8) {
            switch (counter) {
                case 1:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)+([a-zA-Z])+";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.name = matcher.group();
                    } else {
                        throw new IllegalArgumentException("You must write a name and a surname!");
                    }
                    counter++;
                    break;
                case 2:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    if (answer.equals("Dog")) {
                        pet.type = petType.PET_TYPE_DOG;
                    } else if (answer.equals("Cat")) {
                        pet.type = petType.PET_TYPE_CAT;
                    } else {
                        pet.type = petType.PET_TYPE_NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 3:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    if (answer.equals("Male")) {
                        pet.sex = petSex.PET_SEX_MALE;
                    } else if (answer.equals("Female")) {
                        pet.sex = petSex.PET_SEX_FEMALE;
                    } else {
                        pet.sex = petSex.PET_SEX_NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 4:
                    repository.readForm(counter);
                    StringBuilder sb = new StringBuilder();
                    System.out.println("\n.i - Write your house number:");
                    answer = input.nextLine();
                    sb.append(answer).append(", ");
                    System.out.println("\n.ii - Write your street:");
                    answer = input.nextLine();
                    sb.append(answer).append(", ");
                    System.out.println("\n.iii - Write your city:");
                    answer = input.nextLine();
                    sb.append(answer);
                    pet.addr = sb.toString();
                    counter++;
                    break;
                case 5:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.age = matcher.group();
                        float age = Float.parseFloat(pet.age);
                        if (age > 20.00F) {
                            throw new IllegalArgumentException("Exceed age limits");
                        }
                    } else {
                        pet.age = NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 6:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.weight = matcher.group();
                        float weight = Float.parseFloat(pet.weight);
                        if (weight > 60.00F || weight < 0.5F) {
                            throw new IllegalArgumentException("Exceed weight limits");
                        }
                    } else {
                        pet.weight = NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 7:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)*([a-zA-Z])*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.breed = matcher.group();
                    } else {
                        pet.breed = NOT_PROVIDED;
                    }
                    counter++;
                    repository.savePet(pet);
                    break;

            }
        }
    }

    public void updatePet() {

    }

    public void deletePet() {

    }

    public void listAllPets() {
        repository.listAllPetFiles();
    }

    public void listPetWithFilters() {
        Pet pet = new Pet();
        String choice;
        byte criteria = 0;
        System.out.println("Select the animal type: ");
        choice = input.nextLine();
        if (choice.equals("Dog")) {
            pet.type = petType.PET_TYPE_DOG;
        } else if (choice.equals("Cat")) {
            pet.type = petType.PET_TYPE_CAT;
        } else {
            pet.type = petType.PET_TYPE_NOT_PROVIDED;
        }

        if (pet.type == petType.PET_TYPE_NOT_PROVIDED) {
            System.out.println("Option invalid");
            return;
        }

        System.out.println("Select up to 2 search criteria:");
        System.out.println("1 - Name");
        System.out.println("2 - Sex");
        System.out.println("3 - Address");
        System.out.println("4 - Age");
        System.out.println("5 - Weight");
        System.out.println("6 - Breed");

        String regex = "\\d";
        Pattern patter = Pattern.compile(regex);
        choice = input.nextLine();
        Matcher matcher = patter.matcher(choice);
        if (matcher.find()) {
            int result = Integer.parseInt(matcher.group());
            switch (result) {
                case 1:
                    choice = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)+([a-zA-Z])+";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(choice);
                    if (matcher.find()) {
                        pet.name = matcher.group();
                    } else {
                        throw new IllegalArgumentException("You must write a name and a surname!");
                    }
                    break;
                case 2:
                    choice = input.nextLine();
                    if (choice.equals("Male")) {
                        pet.sex = petSex.PET_SEX_MALE;
                    } else if (choice.equals("Female")) {
                        pet.sex = petSex.PET_SEX_FEMALE;
                    } else {
                        pet.sex = petSex.PET_SEX_NOT_PROVIDED;
                    }
                    break;
                case 3:
                    StringBuilder sb = new StringBuilder();
                    System.out.println("\n.i - Write your house number:");
                    choice = input.nextLine();
                    sb.append(choice);
                    pet.addr = sb.toString();
                    break;
                case 4:
                    choice = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(choice);
                    if (matcher.find()) {
                        pet.age = matcher.group();
                        float age = Float.parseFloat(pet.age);
                        if (age > 20.00F) {
                            throw new IllegalArgumentException("Exceed age limits");
                        }
                    } else {
                        pet.age = NOT_PROVIDED;
                    }
                    break;
                case 5:

                    choice = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(choice);
                    if (matcher.find()) {
                        pet.weight = matcher.group();
                        float weight = Float.parseFloat(pet.weight);
                        if (weight > 60.00F || weight < 0.5F) {
                            throw new IllegalArgumentException("Exceed weight limits");
                        }
                    } else {
                        pet.weight = NOT_PROVIDED;
                    }
                    break;
                case 6:

                    choice = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)*([a-zA-Z])*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(choice);
                    if (matcher.find()) {
                        pet.breed = matcher.group();
                    } else {
                        pet.breed = NOT_PROVIDED;
                    }
                    repository.savePet(pet);
                    break;
            }
        }

    }


}
