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
    // 다


## 👤 작성자

Ch.2 프로그래밍 기본 주차 4조 사이좋조 김동욱
