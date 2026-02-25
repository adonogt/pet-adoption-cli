# Pet Adoption System — CLI

A command-line application for managing pet registrations at an animal shelter, built in Java with object-oriented design, file I/O, and exception handling.

## Getting Started

Clone the repository, open it in IntelliJ IDEA or any Java IDE, and run `Main.java`.

## Features

Register, search, edit, and delete pets through a simple terminal interface. Search supports combining up to two criteria such as name, breed, age, weight, sex, or address.

## Project Structure

```
src/
└── com.gus.pet/
    ├── entity/          Pet class and enums (PetType, PetSex)
    ├── menu/            CLI menu and user interaction
    ├── repository/      File read/write operations
    ├── service/         Business logic and validations
    └── Main.java        Application entry point

resources/
└── pet_registration_form.txt

registered-pets/
└── Created automatically on first run. Stores one .txt file per registered pet.
```

## Pet File Format

Files are named using the registration timestamp and the pet's full name, for example `20231101T1234-AKAMARUINUZUKA.TXT`, and contain the following structure:

```
1 - Akamaru Inuzuka
2 - Dog
3 - Male
4 - 7-chome 2-1, Konohagakure, Nara
5 - 2 years
6 - 38kg
7 - Akita Inu
```

## Credits

This project is a fork of the original challenge designed and created by [Lucas Carrilho (@devmagro)](https://github.com/karilho/desafioCadastro). All credit for the challenge concept, requirements, and structure goes to him. Thank you for putting together such a well-thought-out exercise.