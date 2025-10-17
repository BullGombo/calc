package com.example.calculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        System.out.println("Hello, Calculator!");

        Scanner sc = new Scanner(System.in);

        // ######################### exit 입력 전까지 반복하는 do-while문 #########################

        do {
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
            try { //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력
                if (operator == '+') {
                    result = firstNum + secondNum;
                } else if (operator == '-') {
                    result = firstNum - secondNum;
                } else if (operator == '*') {
                    result = firstNum * secondNum;
                } else if (operator == '/') {
                    result = firstNum / secondNum;
//        } else if (operator == '/' && secondNum == 0) {       // 절차상 절대 실행 될 수 없는 dead code 라는 것...
//            System.out.println("나눗셈 분모가 0");              // try - catch 문의 catch 안에 처리하는게 다른 오류를 같이 잡기에도 좋아보임
//            return;
                } else {
                    System.out.println("사칙연산 입력 오류");
                    return;
                }
                System.out.println("결과: " + result);
            } catch (ArithmeticException e) {
                if (operator == '/' && secondNum == 0) {
                    System.out.println("나눗셈 분모에 0을 입력해서는 안됨");

                } else {
                    System.out.println("연산 오류 발생");
                }
            }
            
        } while (isContinue());

    }

    // 김순철 튜터님 피드백 2025-10-16-18:00
    // 가독성을 위해 ``반복 판단`` 메서드 분리
    private static boolean isContinue() {

        Scanner sc = new Scanner(System.in);

        System.out.println("더 계산하시겠습니까? (exit 입력 시 종료, 이외의 문자 입력 시 반복)");
        String insertExit = sc.nextLine();

        //        if (insertExit.equals("exit")) {
        //        return false;
        //        }
        //        return true;
        return !insertExit.equals("exit");
    }
    
}
