package com.example.calculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        System.out.println("Hello, Calculator!");

        Scanner sc = new Scanner(System.in);

        // ######################### 정수 두개와 사칙연산 기호 입력 #########################
        System.out.print("첫 번째 숫자를 입력하세요: ");
        // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
        int firstNum = Integer.parseInt(sc.nextLine()); // Integer.parseInt() : 문자열(String)을 정수(int)로 변환할 때 사용하는 메서드
        System.out.println("입력된 첫 숫자 = " + firstNum);

        System.out.print("두 번째 숫자를 입력하세요: ");
        // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
        int secondNum = Integer.parseInt(sc.nextLine());
        System.out.println("입력된 두번째 숫자 = " + secondNum);

        System.out.print("사칙연산 기호를 입력하세요: ");
        // 사칙연산 기호를 적합한 타입으로 선언한 변수에 저장합니다.
        char operator = sc.nextLine().charAt(0);    // charAt(0) : 문자열(String)에서 특정 위치의 문자를 가져오는 메서드
        System.out.println("입력된 사칙연산 기호 = " + operator);

        // ######################### 사칙 연산 #########################
        int result = 0;
        /* 제어문을 활용하여 위 요구사항을 만족할 수 있게 구현합니다.*/
        if (operator == '+') {
            result = firstNum + secondNum;
        } else if (operator == '-') {
            result = firstNum - secondNum;
        } else if (operator == '*') {
            result = firstNum * secondNum;
        } else if (operator == '/') {
            result = firstNum / secondNum;
        } else {
            System.out.println("사칙연산 입력 오류");
            return;
        }
        System.out.println("결과: " + result);
    }
}
