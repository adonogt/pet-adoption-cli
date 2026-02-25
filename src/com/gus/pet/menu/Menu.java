package com.gus.pet.menu;

import com.gus.pet.service.PetService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public Scanner input;
    public Menu(Scanner keyboard) {
        this.input = keyboard;
    }

    public boolean printOptions() {

        PetService service = new PetService(input);
        System.out.println("1 - Register a new pet.");
        System.out.println("2 - Update the data of a registered pet.");
        System.out.println("3 - Delete a registered pet.");
        System.out.println("4 - List all registered pets.");
        System.out.println("5 - List pets by some criteria (age, name, breed).");
        System.out.println("6 - Exit.");
        System.out.print("Type your choice: ");

        String regex = "\\d";
        Pattern patter = Pattern.compile(regex);
        String choice = input.nextLine();
        Matcher matcher = patter.matcher(choice);

        if (matcher.find()) {
            int result = Integer.parseInt(matcher.group());

            if (result > 0 && result <= 6) {
                switch (result){
                    case 1:
                        service.registerPet();
                        break;
                    case 2:
                        service.updatePet();
                        break;
                    case 3:
                        service.deletePet();
                        break;
                    case 4:
                        service.listAllPets();
                        break;
                    case 5:
                        service.listPetWithFilters();
                        break;
                    case 6:
                        System.out.println("System Finished");
                        break;
                    default:
                        break;
                }
                return true;
            } else {
              return false;
            }
        }
        return false;
    }
}
