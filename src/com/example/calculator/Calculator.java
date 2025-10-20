// #2025-10-17

package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    // ######################### 속성 #########################
    // 연산결과 정보 캡슐화를 위해 'private'을 씀
    private int firstNum;             // 첫 번째 피연산자
    private int secondNum;            // 두 번째 피연산자
    private char operator;            // 연산자
    private double result;            // 연산 결과 ( 이녀석만 '연산' 이후에 도출됨 )
    private List<String> history = new ArrayList<>();


    // ######################### 생성자 #########################
    // 생성자
    // 기본 생성자 -> Calculator() {}
    // 1. 클래스와 이름이 동일
    // 2. 반환 데이터 타입이 없음
    // 3. 여러개 존재 가능
    public Calculator() {} // 생략 가능

    //  초기 result 값을 설정할 수 있는 생성자
    public Calculator(double result) {
        this.result = result;
    }


    // ######################### 기능 #########################

    // 게터
    // 피연산자 두개, 연산자 하나, 결과값 하나, 히스토리
    public int getFirstNum() {
        return firstNum;
    }
    public int getSecondNum() {
        return secondNum;
    }
    public char getOperator() {
        return operator;
    }
    public double getResult() {
        return result;
    }
    public List<String> getHistory() {
        return history;
    }

    // 세터
    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }
    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }
    public void setOperator(char operator) {
        this.operator = operator;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public void addHistory(String record) {
        history.add(record);
    }

    // #2025-10-18   ( App.java에서 가져오고, static 등을 조금 수정함 )
    // 연산 메서드 분리, 결과 출력, 결과를 컬렉션 클래스로 반환
    // ######################### 연산 / 저장 메서드 #########################
    public void calculate(int firstNum, int secondNum, char operator) {

//        Calculator calcResult = new Calculator();

        // 입력한 숫자, 기호, 결과를 객체에 계속 누적
        setFirstNum(firstNum);
        setSecondNum(secondNum);
        setOperator(operator);
        // 나눗셈을 위해 int result = 0 -> double result = 0
        double result = 0;

        // ------------------- 사칙 연산 -------------------
        try { //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력
            if (operator == '+') {
                result = firstNum + secondNum;
            } else if (operator == '-') {
                result = firstNum - secondNum;
            } else if (operator == '*') {
                result = firstNum * secondNum;
            } else if (operator == '/') {
                result = (double) firstNum / secondNum; // 소숫점 계산을 위해 (double) #2025-10-17, LV1 완료 이후
//        } else if (operator == '/' && secondNum == 0) {       // 절차상 절대 실행 될 수 없는 dead code 라는 것...
//            System.out.println("나눗셈 분모가 0");              // try - catch 문의 catch 안에 처리하는게 다른 오류를 같이 잡기에도 좋아보임
//            return;
            } else {
                System.out.println("사칙연산 입력 오류");
                return;
            }
//            // 콘솔에 결과 출력 -> '보기좋은 코드'를 작성하기 위해 메인 메서드로 옮김
//            System.out.println("결과: " + firstNum + operator + secondNum + " = " + result);

            // result는 다른 세 변수들과 다르게 연산 이후에 도출 되기 때문에, 연산 이후 set
            setResult(result);

            // 히스토리 타입의 컬렉션에 모든 요소 저장 (addHistory)
            String record = firstNum + " " + operator + " " + secondNum + " = " + result;
            addHistory(record);

        } catch (ArithmeticException e) {
            if (operator == '/' && secondNum == 0) {
                System.out.println("나눗셈 분모에 0을 입력해서는 안됨");
                // 입력받은 firstNum과 secondNum의 타입이 정수가 아닌 경우
//            } else if (firstNum instanceof Integer || secondNum instanceof Integer) {
//                System.out.println("정수 입력란에 정수가 입력되지 않음");
            } else {
                System.out.println("연산 오류 발생");
            }
        }
        // return 연산 결과 반환 위치 옮김
    }

}
