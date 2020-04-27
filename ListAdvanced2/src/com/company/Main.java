package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

    }

    public void listOperations() {
        // You will be given numbers (list of integers) on the first input line.
        //  Until you receive "End" you will be given operations you have to apply on the list. The possible commands are:
        //    • Add {number} - add number at the end
        //    • Insert {number} {index} - insert number at given index
        //    • Remove {index} - remove that index
        //    • Shift left {count} - first number becomes last 'count' times
        //    • Shift right {count} - last number becomes first 'count' times
        // Note: It is possible that the index given is outside of the bounds of the array. In that case print "Invalid index".

        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while (true) {

            String[] commands = sc.nextLine().split(" ");
            String action = commands[0];

            if (action.equals("End")) {
                for (Integer num : numbers) {
                    System.out.print(num + " ");
                }
                break;
            }

            switch (action) {
                case "Add":
                    int numAdd = Integer.parseInt(commands[1]);
                    numbers.add(numAdd);
                    break;
                case "Insert":
                    int insertNum = Integer.parseInt(commands[1]);
                    int insertIndex = Integer.parseInt(commands[2]);
                    if (insertIndex > numbers.size() - 1) {
                        System.out.println("Invalid index");
                    } else {
                        numbers.add(insertIndex, insertNum);
                    }
                    break;
                case "Remove":
                    int removeIndex = Integer.parseInt(commands[1]);
                    if (removeIndex > numbers.size()) {
                        System.out.println("Invalid index");
                    } else {
                        numbers.remove(removeIndex);
                    }
                    break;
                case "Shift":
                    String position = commands[1];
                    int times = Integer.parseInt(commands[2]);
                    // 1 2 3 4
                    if (position.equals("left")) {
                        for (int i = 0; i < times; i++) {
                            Integer first = numbers.get(0);
                            numbers.add(first);
                            numbers.remove(0);
                        }
                    } else {
                        for (int i = 0; i < times; i++) {
                            Integer last = numbers.get(numbers.size() - 1);
                            numbers.add(0, last);
                            numbers.remove(numbers.size() - 1);
                        }
                    }
                    break;

            }
        }
    }

    public void houseParty() {
        // Write a program that keeps track of the guests that are going to a house party.
        // On the first input line you are going to receive how many commands you are going to have. On the next lines you are going to receive some of the following inputs:
        //    • "{name} is going!"
        //    • "{name} is not going!"
        // If you receive the first type of input, you have to add the person if he/she is not in the list.
        // (If he/she is in the list print on the console: "{name} is already in the list!").
        // If you receive the second type of input, you have to remove the person if he/she is in the list. (If not print: "{name} is not in the list!"). At the end print all guests.

        Scanner sc = new Scanner(System.in);
        int commandsCount = Integer.parseInt(sc.nextLine());
        List<String> names = new ArrayList<>();

        for (int i = 0; i < commandsCount; i++) {
            String[] commands = sc.nextLine().split(" ");
            String name = commands[0];
            String action = commands[2];

            if (action.equals("not")) {
                if (names.contains(name)) {
                    names.remove(name);
                } else {
                    System.out.println(name + "is not in the list!");
                }
            } else {
                if (names.contains(name)) {
                    System.out.println(name + " is already in the list!");
                } else names.add(name);
            }
        }

        for (String name: names) {
            System.out.print(name + " ");
        }
    }

    public void changeList() {
        // Write a program, which reads a list of integers from the console and receives commands, which manipulate the list. Your program may receive the following commands:
        //    • Delete {element} - delete all elements in the array, which are equal to the given element
        //    • Insert {element} {position} - insert element at the given position
        // You should stop the program when you receive the command "end". Print all numbers in the array separated with a single whitespace.

        // 1 2 3 4 5 5 5 6               1 10 2 3 4 6
        // Delete 5
        // Insert 10 1
        // Delete 5
        // end

        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while(true) {

            String[] commands = sc.nextLine().split(" ");
            String command = commands[0];

            if (command.equals("end")) {
                for (Integer num : numbers) {
                    System.out.print(num + " ");
                }
                break;
            }

            Integer num = Integer.parseInt(commands[1]);

            if (command.equals("Delete")) {
                for (int i = 0; i < numbers.size(); i++) {
                    int count = numbers.get(i);
                    if (count == num) numbers.remove(i);
                }
            } else {
                int position = Integer.parseInt(commands[2]);
                numbers.add(position, num);
            }
        }
    }

    public void Train() {
        // On the first line you will be given a list of wagons (integers).
        // Each integer represents the number of passengers that are currently in each wagon.
        // On the next line you will get the max capacity of each wagon (single integer). Until you receive "end" you will be given two types of input:
        //    • Add {passengers} - add a wagon to the end with the given number of passengers
        //    • {passengers} -  find an existing wagon to fit all the passengers (starting from the first wagon)
        //At the end print the final state of the train (all the wagons separated by a space)

        // Input                            Output
        // 32 54 21 12 4 0 23               72 54 21 12 4 75 23 10 0
        Scanner sc = new Scanner(System.in);
        List<Integer> wagons = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxCapacity = Integer.parseInt(sc.nextLine());

        while(true) {

            String[] commands = sc.nextLine().split(" ");

            if(commands[0].equals("Add")) {
                wagons.add(Integer.parseInt(commands[1]));
            } else if (commands[0].equals("end")) {
                for (Integer wagon: wagons) {
                    System.out.print(wagon + " ");
                }
                break;
            } else {
                for(int i = 0; i < wagons.size(); i++) {
                    Integer count = wagons.get(i);
                    Integer sum = count + Integer.parseInt(commands[0]);
                    if (sum <= maxCapacity) {
                        wagons.set(i, sum);
                        break;
                    }
                }
            }

        }
    }
}
