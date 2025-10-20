package com.example.calculator;

import java.util.Scanner;

public class App {

    //Calculator 객체를 main 전체에서 공유할 수 있도록 static 필드로 변경 #2025-10-17
    private static Calculator calcResult = new Calculator();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, Calculator!");

        // 실행 시 기존 연산 이력 출력 #2025-10-17
        printHistory();  // 수정 추가

        // ------------------- exit 입력 전까지 반복하는 do-while문 -------------------
        // ------------------- 정수 두개와 사칙연산 기호 입력 -------------------
        do {
            // 입력란 오입력 예외처리 + 편의 개선 #2025-10-20
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
                int firstNum = Integer.parseInt(sc.nextLine()); // Integer.parseInt() : 문자열(String)을 정수(int)로 변환할 때 사용하는 메서드

                System.out.print("두 번째 숫자를 입력하세요: ");
                // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
                int secondNum = Integer.parseInt(sc.nextLine());

                System.out.print("사칙연산 기호를 입력하세요: ");
                // 사칙연산 기호를 적합한 타입으로 선언한 변수에 저장합니다.
                char operator = sc.nextLine().charAt(0);    // charAt(0) : 문자열(String)에서 특정 위치의 문자를 가져오는 메서드

                // 사칙연산 기호 유효성 검사 추가 #2025-10-20
                if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                    System.out.println("잘못된 사칙연산 기호 (+, -, *, / 만 입력 가능)");
                    continue; // 다시 입력받기
                }

                // 결과를 히스토리 컬렉션 객체에 담아서 반환 #2025-10-17
//          Calculator calcResult = calculate(firstNum, secondNum, operator);
                // calculate() 결과를 공유 객체 calcResult에 누적 저장 #2025-10-17
                // Calculator 클래스의 calculate 메서드 호출 #2025-10-18
                calcResult.calculate(firstNum, secondNum, operator);

            } catch (NumberFormatException e) {
                System.out.println("정수 입력란에 정수가 입력되지 않음");
                continue;
            }

            // 결과 출력 (입력했던거 보이게) #2025-10-17
            // System.out.println("결과: " + result);
            System.out.println("계산: " + calcResult.getFirstNum()
                    + " " + calcResult.getOperator()
                    + " " + calcResult.getSecondNum()
                    + " = " + calcResult.getResult());

            // 연산 후 컬렉션 이력 출력 #2025-10-17
            printHistory();

        } while (isContinue()); // false 전달시 반복 정지

    }

    // 김순철 튜터님 피드백 #2025-10-16-18:00
    // 가독성을 위해 ``반복 판단`` 메서드 분리
    // ######################### 반복 판단 메서드 #########################
    private static boolean isContinue() {

        Scanner sc = new Scanner(System.in);
//        Calculator calcResult = new Calculator();

        System.out.println("더 계산하시겠습니까?" + "\n" + "exit : 종료" + "\n" +
                "del : 가장 오래된 데이터 삭제" + "\n" + "그 외 : 반복");
        String insertExit = sc.nextLine();

        if (insertExit.equals("exit")) {
            return false;
        // 첫번째 데이터 삭제 #2025-10-17
        } else if (insertExit.equals("del")) {
            removeFirstHistory();
        }
        return true;
//        return !insertExit.equals("exit");  // !true = false 라서 이런식으로
    }

    // 연산 메서드를 Calculation으로 이동
//    // #2025-10-17
//    // 연산 메서드 분리, 결과 출력, 결과를 컬렉션 클래스로 반환
//    // ######################### 연산 / 저장 메서드 #########################
//    private static void calculate(int firstNum, int secondNum, char operator) {
//
// //        Calculator calcResult = new Calculator();
//
//        // 입력한 숫자, 기호, 결과를 객체에 계속 누적
//        calcResult.setFirstNum(firstNum);
//        calcResult.setSecondNum(secondNum);
//        calcResult.setOperator(operator);
//        // 나눗셈을 위해 int result = 0 -> double result = 0
//        double result = 0;
//
//        // ------------------- 사칙 연산 -------------------
//        try { //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력
//            if (operator == '+') {
//                result = firstNum + secondNum;
//            } else if (operator == '-') {
//                result = firstNum - secondNum;
//            } else if (operator == '*') {
//                result = firstNum * secondNum;
//            } else if (operator == '/') {
//                result = (double) firstNum / secondNum; // 소숫점 계산을 위해 (double) #2025-10-17, LV1 완료 이후
// //        } else if (operator == '/' && secondNum == 0) {       // 절차상 절대 실행 될 수 없는 dead code 라는 것...
// //            System.out.println("나눗셈 분모가 0");              // try - catch 문의 catch 안에 처리하는게 다른 오류를 같이 잡기에도 좋아보임
// //            return;
//            } else {
//                System.out.println("사칙연산 입력 오류");
//                return;
//            }
// //            // 콘솔에 결과 출력 -> '보기좋은 코드'를 작성하기 위해 메인 메서드로 옮김
// //            System.out.println("결과: " + firstNum + operator + secondNum + " = " + result);
//
//            // result는 다른 세 변수들과 다르게 연산 이후에 도출 되기 때문에, 연산 이후 set
//            calcResult.setResult(result);
//
//            // 히스토리 타입의 컬렉션에 모든 요소 저장 (addHistory)
//            String record = firstNum + " " + operator + " " + secondNum + " = " + result;
//            calcResult.addHistory(record);
//
//        } catch (ArithmeticException e) {
//            if (operator == '/' && secondNum == 0) {
//                System.out.println("나눗셈 분모에 0을 입력해서는 안됨");
//            } else {
//                System.out.println("연산 오류 발생");
//            }
//        }
//        // return 연산 결과 반환 위치 옮김
//    }

    // #2025-10-17
    // ######################### 데이터 삭제 메서드 #########################
    public static void removeFirstHistory() {
        // Calculator 클래스에 저장된 연산 결과들 중 '가장 먼저 저장된 데이터'를 삭제하는 기능
        // 어떻게 삭제명령을 인식 시킬 것인가?
        // isContinue에서 특정 문자를 감지할 경우 해당 메서드가 호출되게?

//        Calculator calcResult = new Calculator();

        if (calcResult.getHistory().isEmpty()) {
            System.out.println("비어있음");
        } else  {
            calcResult.getHistory().removeFirst();  // 첫번째 인덱스를 삭제
            System.out.println("가장 오래된 연산 기록(index[0])을 삭제했습니다.");
        }

    }

    // #2025-10-17
    // ######################### 데이터 출력 메서드 #########################
    private static void printHistory() {
        System.out.println("==== 연산 이력 ====");
        if (calcResult.getHistory().isEmpty()) {
            System.out.println("(기록 없음)");
        } else {
            // 현재 리스트의 사이즈만큼 반복해서 출력
            for (int i = 0; i < calcResult.getHistory().size(); i++) {
                System.out.println((i + 1) + ". " + calcResult.getHistory().get(i));
            }
        }
        System.out.println("==================");
    }

}
