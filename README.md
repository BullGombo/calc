# �Calculator Assignment

Java를 활용한 사칙연산 계산기 프로젝트 과제

## 📋 프로젝트 개요

콘솔 기반 사칙연산 계산기

## 🎯 주요 기능

### Lv.1 - 기본 계산기 구현
- 2개의 숫자를 입력받아 사칙연산 수행
- `exit` 입력 시 프로그램 종료
- 양의 정수(0 포함) 입력받기
- 사칙연산(+, -, *, /) 지원
- 연산 결과 저장 및 출력
- 반복문을 통한 지속적인 계산 수행

### Lv.2 - 클래스를 활용한 계산기 개선
- `Calculator` 클래스 생성 및 연산 로직 분리
- 계산된 결과를 컬렉션에 저장하는 기능
- **캡슐화**: 컬렉션 필드를 `private`으로 설정하여 직접 접근 차단
- **Getter/Setter**: 간접 접근을 통한 데이터 관리
- **삭제 기능**: 가장 먼저 저장된 데이터를 삭제하는 메서드 구현

### Lv.3 - Enum과 제네릭 이해한 계산기 추가 개선
- `OperatorType` Enum 클래스로 사칙연산 기능 분리
- `Calculator` 클래스는 사칙연산의 결과값들을 저장하고 삭제하는 기능을 담당
- 제네릭을 활용하여 정수와 실수 자유롭게 입력 가능


## 🏗️ 프로젝트 구조

```
src/
├── App.java                 # 메인 실행 클래스
   └── Calculator.java       # 각종 속성 변수, 생성자, 계산 결과 컬렉션 저장/접근 getter/setter, 연산 메서드, 현재 입력보다 높은 연산값 조회 메서드
```

## 💡 핵심 구현 사항

### [Lv.2] 
### 1. 캡슐화 (Encapsulation) 
```java
private List<String> history = new ArrayList<>();
```
- 연산 결과 리스트를 `private`으로 선언하여 외부 직접 접근 차단

### 2. Getter/Setter 메서드
```java
public List<Integer> getResult() { ... }
public void setResult(double result) { ... }
```
- 간접 접근을 통한 데이터 조회 및 수정

### 3. 데이터 삭제 기능
```java
public static void removeFirstHistory() { ... }
```
- FIFO(First In First Out) 방식으로 가장 먼저 저장된 데이터 삭제
- 사용자가 계속 삭제할지 선택 가능

### 4. 예외 처리
- **0으로 나누기**: `ArithmeticException` 처리
- **잘못된 수**: `NumberFormatException` 처리
- **잘못된 연산자**: `IllegalArgumentException` 처리

### [Lv.3]
### 5. Enum을 활용
```java
    // ######################### enum 추가 #########################
    // 사칙연산을 관리하기 위한 enum 타입 #2025-10-20
    public enum Operator {
        ADD('+'),
        SUBTRACT('-'),
        MULTIPLY('*'),
        DIVIDE('/');

        private final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        // char 입력값을 enum으로 변환하는 정적 메서드
        public static Operator fromChar(char c) {
            for (Operator op : Operator.values()) {
                if (op.symbol == c) return op;
            }
            throw new IllegalArgumentException("잘못된 연산자: " + c);
        }

        @Override
        public String toString() {
            return String.valueOf(symbol);
        }
    }

```


### 6. 제네릭을 활용하여 정수와 실수 자유롭게 입력
```java
// ======================= 제네릭 적용 #2025-10-20 =======================
    // 기존: public class Calculator { ... }
    // 변경: 여러 타입의 숫자를 (int, double 등)을 처리하기 위해 제네릭 extends Number 추가
public class Calculator<T extends Number> { // #2025-10-20

    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    // ######################### 속성 #########################
    // 연산결과 정보 캡슐화를 위해 'private'을 씀
//    private int firstNum;             // 첫 번째 피연산자
//    private int secondNum;            // 두 번째 피연산자
    private T firstNum;             // int → T
    private T secondNum;            // int → T
    // private char operator;            // 연산자
    // ======================= enum으로 대체 #2025-10-20 =======================
    private Operator operator;         // 연산자(enum 타입)
    private double result;            // 연산 결과 ( 이녀석만 '연산' 이후에 도출됨 )
    private List<String> history = new ArrayList<>();
...

- `Integer`, `Double` Wrapper 클래스의 부모 클래스인 `Number`클래스를 상속 받아 제네릭`<T>` 구현

### 7. 스트림과 람다
```java
        // ######################### 현재 입력보다 높은 연산값 조회 #########################
        public void printHigherResults(double value) {
            List<Double> higherResults = getHistory().stream()   // 1. 데이터 흐름 준비
                    //history가 List<String>, result는 String 타입
                    .map(record -> {  // "10 + 5 = 15.0" → "15.0"
                    // "="를 기준으로 문자열을 분리, [1]번째 인덱스인 결과값을 Double로 반환
                        String[] parts = record.split(" = ");
                        return Double.parseDouble(parts[1]);
                    })                                            // 2. 타입 변환
                    .filter(result -> result > value)         // 2. 비교 연산
                    .collect(Collectors.toList());                // 3. 최종 연산 (수집)
            System.out.println("입력된 연산보다 높은 값 조회 = " + higherResults);

        }
...


## 🚀 실행 방법

1. 프로젝트 클론
```bash
git clone [repository-url]
```

2. 각 패키지 내에서 컴파일 및 실행

## 📝 사용 예시

```
Hello, Calculator!
==== 연산 이력 ====
(기록 없음)
==================
첫 번째 숫자를 입력하세요: 5432.11
두 번째 숫자를 입력하세요: 5934.984
사칙연산 기호를 입력하세요: +
계산: 5432.11 + 5934.984 = 11367.094
==== 연산 이력 ====
1. 5432.11 + 5934.984 = 11367.094
==================
입력된 연산보다 높은 값 조회 = []
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복

첫 번째 숫자를 입력하세요: 2000
두 번째 숫자를 입력하세요: 1000
사칙연산 기호를 입력하세요: -
계산: 2000.0 - 1000.0 = 1000.0
==== 연산 이력 ====
1. 5432.11 + 5934.984 = 11367.094
2. 2000.0 - 1000.0 = 1000.0
==================
입력된 연산보다 높은 값 조회 = [11367.094]
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복

첫 번째 숫자를 입력하세요: 35
두 번째 숫자를 입력하세요: 560
사칙연산 기호를 입력하세요: *
계산: 35.0 * 560.0 = 19600.0
==== 연산 이력 ====
1. 5432.11 + 5934.984 = 11367.094
2. 2000.0 - 1000.0 = 1000.0
3. 35.0 * 560.0 = 19600.0
==================
입력된 연산보다 높은 값 조회 = []
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복

첫 번째 숫자를 입력하세요: 3333
두 번째 숫자를 입력하세요: 1111
사칙연산 기호를 입력하세요: /
계산: 3333.0 / 1111.0 = 3.0
==== 연산 이력 ====
1. 5432.11 + 5934.984 = 11367.094
2. 2000.0 - 1000.0 = 1000.0
3. 35.0 * 560.0 = 19600.0
4. 3333.0 / 1111.0 = 3.0
==================
입력된 연산보다 높은 값 조회 = [11367.094, 1000.0, 19600.0]
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복

첫 번째 숫자를 입력하세요: 34.1754
두 번째 숫자를 입력하세요: 3.1415
사칙연산 기호를 입력하세요: *
계산: 34.1754 * 3.1415 = 107.362019
==== 연산 이력 ====
1. 5432.11 + 5934.984 = 11367.094
2. 2000.0 - 1000.0 = 1000.0
3. 35.0 * 560.0 = 19600.0
4. 3333.0 / 1111.0 = 3.0
5. 34.1754 * 3.1415 = 107.362019
==================
입력된 연산보다 높은 값 조회 = [11367.094, 1000.0, 19600.0]
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복
del
가장 오래된 연산 기록(index[0])을 삭제했습니다.
첫 번째 숫자를 입력하세요: 1
두 번째 숫자를 입력하세요: 1
사칙연산 기호를 입력하세요: +
계산: 1.0 + 1.0 = 2.0
==== 연산 이력 ====
1. 2000.0 - 1000.0 = 1000.0
2. 35.0 * 560.0 = 19600.0
3. 3333.0 / 1111.0 = 3.0
4. 34.1754 * 3.1415 = 107.362019
5. 1.0 + 1.0 = 2.0
==================
입력된 연산보다 높은 값 조회 = [1000.0, 19600.0, 3.0, 107.362019]
더 계산하시겠습니까?
exit : 종료
del : 가장 오래된 데이터 삭제
그 외 : 반복
exit

종료 코드 0(으)로 완료된 프로세스
```

## 🛠️ 기술 스택

- **언어**: Java
- **자료구조**: ArrayList
- **디자인 패턴**: 캡슐화, Getter/Setter 패턴

## 📚 학습 내용

- 객체지향 프로그래밍(OOP) 기본 원칙
- 캡슐화와 정보 은닉
- Java 컬렉션 프레임워크(ArrayList) 활용
- 예외 처리(Exception Handling)
- Scanner를 통한 사용자 입력 처리
- Enum 타입 활용하여 연산자 정보 관리
- 제네릭 구현
- 람다, 스트림 활용용

## 🔄 버전 히스토리



## 👤 작성자

Ch.2 프로그래밍 기본 주차 4조 사이좋조 김동욱
