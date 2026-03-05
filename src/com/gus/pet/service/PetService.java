package com.gus.pet.service;

import com.gus.pet.entity.Pet;
import com.gus.pet.entity.petSex;
import com.gus.pet.entity.petType;
import com.gus.pet.repository.PetRepository;

import java.io.File;
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
                    pet.address = sb.toString();
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
        Pet search = buildFilter();
        repository.searchPets(search);
        System.out.print("\n Choose the number of the pet you want to change: ");


    }

    public void deletePet() {
        Pet search = buildFilter();
        File[] petFound = repository.searchPets(search);
        int index = 0;
        String answer;
        System.out.print("\n Choose the number of the pet you want to delete: ");
        answer = input.nextLine();
        index = Integer.parseInt(answer);

        repository.removePet(petFound[index-1]);
    }

    public void listAllPets() {
        repository.listAllPet();
    }

    public void listPetWithFilters() {

        Pet search = buildFilter();
        repository.searchPets(search);

    }

    public Pet buildFilter()
    {
        String choice;
        Pet filter = new Pet();
        byte criteriaCount = 0;
        System.out.print("Select the animal type: ");
        choice = input.nextLine();
        if (choice.equals("Dog")) {
            filter.type = petType.PET_TYPE_DOG;
        } else if (choice.equals("Cat")) {
            filter.type = petType.PET_TYPE_CAT;
        } else {
            throw new IllegalArgumentException("Invalid option");
        }

        while(criteriaCount<2) {
            System.out.print("Criteria Options");

            System.out.println("\n1 - Name");
            System.out.println("2 - Sex");
            System.out.println("3 - Address");
            System.out.println("4 - Age");
            System.out.println("5 - Weight");
            System.out.println("6 - Breed\n");
            System.out.println("Select criteria number "+(criteriaCount+1)+" : ");

            String regex = "\\d";
            Pattern patter = Pattern.compile(regex);
            choice = input.nextLine();
            Matcher matcher = patter.matcher(choice);
            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group());
                switch (result) {
                    case 1:
                        System.out.println("Enter name: ");
                        choice = input.nextLine();
                        regex = "([a-zA-Z])+(\\s)*([a-zA-Z])*";
                        patter = Pattern.compile(regex);
                        matcher = patter.matcher(choice);
                        if (matcher.find()) {
                            filter.name = matcher.group();
                        } else {
                            throw new IllegalArgumentException("You must write a name and a surname!");
                        }
                        break;
                    case 2:
                        System.out.println("Enter sex: ");
                        choice = input.nextLine();
                        if (choice.equals("Male")) {
                            filter.sex = petSex.PET_SEX_MALE;
                        } else if (choice.equals("Female")) {
                            filter.sex = petSex.PET_SEX_FEMALE;
                        } else {
                            throw new IllegalArgumentException("Invalid option");
                        }
                        break;
                    case 3:
                        System.out.println("Enter address: ");
                        choice = input.nextLine();
                        filter.address = choice;
                        break;
                    case 4:
                        System.out.println("Enter age: ");
                        choice = input.nextLine();
                        regex = "(\\d)+(\\.)*(\\d)*";
                        patter = Pattern.compile(regex);
                        matcher = patter.matcher(choice);
                        if (matcher.find()) {
                            filter.age = matcher.group();
                        }
                        break;
                    case 5:
                        System.out.println("Enter weight: ");
                        choice = input.nextLine();
                        regex = "(\\d)+(\\.)*(\\d)*";
                        patter = Pattern.compile(regex);
                        matcher = patter.matcher(choice);
                        if (matcher.find()) {
                            filter.weight = matcher.group();
                        }
                        break;
                    case 6:
                        System.out.println("Enter breed: ");
                        choice = input.nextLine();
                        regex = "([a-zA-Z])+(\\s)*([a-zA-Z])*";
                        patter = Pattern.compile(regex);
                        matcher = patter.matcher(choice);
                        if (matcher.find()) {
                            filter.breed = matcher.group();
                        }
                        break;
                }
                if(criteriaCount == 0) {
                    System.out.println("Add a second criteria? (Y/N)");
                    choice = input.nextLine();
                    if (choice.equals("Y")) {
                        criteriaCount++;
                    }
                    else {
                        criteriaCount = 2;
                    }
                }
                else
                {
                    criteriaCount = 2;
                }

            }
        }
        return filter;
    }


}
