package com.example.quiz_app;

public class QuestionAnswer {

    public static String[] question = {
            "What does CPU stand for?",
            "Which data structure uses FIFO (First In First Out)?",
            "What is the time complexity of binary search in a sorted array?",
            "Which protocol is used to send emails?",
            "What does HTML stand for?",
            "Which language is primarily used for Artificial Intelligence?",
            "What is the main function of an Operating System?",
            "Which sorting algorithm is the fastest in the average case?",
            "What does IP in IP address stand for?",
            "Which one is NOT an operating system?"
    };


    public static String[][] choices = {
            {"Central Processing Unit", "Computer Power Unit", "Control Processing Unit", "Central Program Unit"},
            {"Stack", "Queue", "Tree", "Graph"},
            {"O(n)", "O(log n)", "O(n log n)", "O(1)"},
            {"HTTP", "FTP", "SMTP", "TCP"},
            {"Hyper Text Markup Language", "Home Tool Markup Language", "Hyperlinks and Text Markup Language", "Hyperlinking Text Management Language"},
            {"Java", "Python", "LISP", "PHP"},
            {"Run applications", "Manage hardware", "Both A and B", "Compile code"},
            {"Bubble Sort", "Quick Sort", "Selection Sort", "Insertion Sort"},
            {"Internet Protocol", "Internal Process", "Input Protocol", "Internet Program"},
            {"Linux", "Windows", "MacOS", "Oracle"}
    };


    public static String[] correctAnswers = {
            "Central Processing Unit",
            "Queue",
            "O(log n)",
            "SMTP",
            "Hyper Text Markup Language",
            "LISP",
            "Both A and B",
            "Quick Sort",
            "Internet Protocol",
            "Oracle"
    };

}