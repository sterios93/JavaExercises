package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

    }

    public void removeNegativesAndReverse() {
        // Read a list of integers, remove all negative numbers from it and print the remaining elements in reversed order. In case of no elements left in the list, print "empty".
        // 10 -5 7 9 -33 50
        // 50 9 7 10

        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .filter(i -> i > 0)
                .collect(Collectors.toList());

        if (numbers.size() > 0) {
            for (int start = numbers.size() - 1; start >= 0 ; start --) {
                System.out.print(numbers.get(start) + " ");
            }
        } else System.out.println("empty");
    }

    public void manipulationAdvanced() {
        // 5. List Manipulation Advanced
        // Now we will implement more complicated list commands. Again, read a list, and until you receive "end" read commands:
        // Contains {number} – check if the list contains the number. If yes print "Yes", otherwise print "No such number"
        // Print even – print all the numbers that are even separated by a space
        // Print odd – print all the numbers that are odd separated by a space
        // Get sum – print the sum of all the numbers
        // Filter ({condition} {number}) – print all the numbers that fulfill that condition. The condition will be either '<', '>', ">=", "<="

        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while(true) {
            String[] line = sc.nextLine().split(" ");

            String command = line[0];
            if (command.equals("end")) break;
            String command2 = line[1];

            switch (command) {
                case "Contains":
                    System.out.println(numbers.contains(Integer.parseInt(command2)));
                    break;
                case "Print":
                    if (command2.equals("odd")) {
                        for (Integer num: numbers) {
                            if (num % 2 == 1) System.out.print(num + " ");
                        }
                        System.out.println();
                    } else {
                        for (Integer num: numbers) {
                            if (num % 2 == 0) System.out.print(num + " ");
                        }
                        System.out.println();
                    }
                    break;
                case "Get":
                    int sum = 0;
                    for (Integer num: numbers) {
                        sum += num;
                    }
                    System.out.println(sum);
                    break;
                case "Filter":
                    int b = Integer.parseInt(line[2]);

                    if (command2.equals("<")) {
                        numbers.stream().filter(n -> n < b).forEach(i -> System.out.print(i + " "));
                    } else if (command2.equals(">")) {
                        numbers.stream().filter(n -> n > b).forEach(i -> System.out.print(i + " "));
                    } else if (command2.equals(">=")) {
                        numbers.stream().filter(n -> n >= b).forEach(i -> System.out.print(i + " "));
                    } else if (command2.equals("<=")) {
                        numbers.stream().filter(n -> n <= b).forEach(i -> System.out.print(i + " "));
                    }
                    break;
            }
        }
    }

    public void manipulationBasics() {
        // List Manipulation Basics
        // Write a program that reads a list of integers. Then until you receive end, you will be given different commands:
        // Add {number}: add a number to the end of the list
        // Remove {number}: remove a number from the list
        // RemoveAt {index}: remove a number at a given index
        // Insert {number} {index}: insert a number at a given index
        // Note: All the indices will be valid!
        // When you receive the end  command print the final state of the list (separated by spaces)

        // 4 19 2 53 6 43    Output  4 53 6 8 43 3
        // Add 3
        // Remove 2
        // RemoveAt 1
        // Insert 8 3
        // end

        Scanner sc = new Scanner(System.in);
        List<Integer> result = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        while (true) {
            String[] commands = sc.nextLine().split(" ");

            String command = commands[0];
            if (command.equals("end")) {
                break;
            };
            String stringValue = commands[1];
            int num = Integer.parseInt(stringValue);

            switch (command) {
                case "Add":
                    result.add(num);
                    break;
                case "Remove":
                    result.remove(result.indexOf(num));
                    break;
                case "RemoveAt":
                    result.remove(num);
                    break;
                case "Insert":
                    int index = Integer.parseInt(commands[2]);
                    result.add(index, num);
                    break;
            }
        }

        for (Integer number : result) {
            System.out.print(number + " ");
        }
    }

    public void mergingLists() {
        // Merging Lists
        // You are going to receive two lists with numbers. Create a result list which contains the numbers from both of the
        // lists. The first element should be from the first list, the second from the second list and so on. If the length of the
        // two lists are not equal, just add the remaining elements at the end of the list.

        Scanner sc = new Scanner(System.in);
        int[] numbers1 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        int[] numbers2 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        List<Integer> result = new ArrayList<>();
        int[] smallerArray = numbers1.length > numbers2.length ? numbers2 : numbers1;
        int[] biggerArray = numbers1.length > numbers2.length ? numbers1 : numbers2;
        boolean equalLengthArrays = smallerArray.length == biggerArray.length;

        int index = 0;
        while(index < smallerArray.length) {
            int n1 = numbers1[index];
            int n2 = numbers2[index];
            result.add(n1);
            result.add(n2);
            index++;
        }

        if (!equalLengthArrays) {
            for (int i = index; index < biggerArray.length; index++) {
                result.add(biggerArray[index]);
            }
        }

        for (Integer num: result) {
            System.out.print(num + " ");
        }
    }

    public void gausTrick() {
        // Gauss' Trick
        // Write a program that sum all numbers in a list in the following order:
        // first + last, first + 1 + last - 1, first + 2 + last - 2, … first + n, last - n.
        // Input 1 2 3 4 5 Output 6 6 3

        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();
        int[] numbers = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        for (int i = 0; i < numbers.length / 2; i++) {
            Integer sum = numbers[i] + numbers[numbers.length - 1 - i];
            result.add(sum);
        }

        if (numbers.length % 2 == 1) {
            result.add(numbers[numbers.length / 2]);
        }

        for (Integer num : result) {
            System.out.print(num + " ");
        }
    }

    public  void sumAdjacent() {

        // input 3 3 6 1  output 12 1  Explanation  3 3 6 1 -> 6 6 1 -> 12 1

        Scanner sc = new Scanner(System.in);
        List<Double> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());


        int index = 0;

        while (index < numbers.size() - 1) {
            Double current = numbers.get(index);
            Double next = numbers.get(index + 1);

            if (current.equals(next)) {
                Double sum = current + next;
                numbers.set(index, sum);
                numbers.remove(index + 1);
                index = 0;
            } else {
                index++;
            }
        }

        for (Double number : numbers) {
            System.out.print(number + " ");
        }
    }
}
