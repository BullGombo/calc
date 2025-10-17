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

}
