# 어깨너머 트위터(현 X) fan-out 실습

## 개요

대규모 트래픽을 견디는 트위터 애플리케이션에서 fan-out 문제를 다룬 백엔드 시스템을 아주 심플하게 만들어본다.

## 샘플 목표

### 서비스 설명

(1) 애플리케이션을 이용하는 '사용자'가 있다.

(2) 사용자는 관심있는 사용자를 '팔로우' 할 수 있고 누군가 나를 팔로우하는 '팔로워'를 가진다.

(3) 사용자는 애플리케이션의 홈타임라인에 접속하여 '팔로우'한 사람들의 최신 '트윗'을 본다.

### 트위터(현 X) 백엔드 시스템

(1) 1차 시스템 구성

▢ 사용자가 홈타임라인에 접근하면 팔로우들의 최근에 작성한 트윗을 데이터베이스에서 조회하여 가져온다.

문제점

■ 트위터 애플리케이션은 데이터베이스 읽기 쓰기 비율이 읽기가 쓰기의 약 25배라고 한다.  
■ 서비스가 성장함에 따라 초당 600K(60만)의 읽기 요청이 발생한다고 한다.  
■ 데이터베이스 읽기 요청에 부하가 발생한다.


(2) 2차 시스템 구성

▢ 데이터베이스 읽기 성능을 개선하기 위해 사용자가 조회할 데이터를 미리 캐시(레디스) DB에 올려두고 읽기 요청이 발생했을 때 캐시 DB에 접근하도록 한다.  
▢ 이로인해 캐시(레디스) DB에 조회할 데이터를 업데이트하는 비용이 발생한다.

문제점

■ 특정 유명인들은 팔로워의 수가 많다. (실제로 팔로워가 100,000,000(1억)명 이상인 사용자가 존재)  
■ 이런 유명인들이 새로운 트윗을 작성하는 경우 팔로워하는 사용자들의 홈타임라인에 트윗의 내용을 보여줘야하기 때문에 캐시(레디스) DB 업데이트가 팔로우 수에 비례해서 발생한다.  
■ 업데이트의 시간은 수분에서 수십분이 걸릴 수 있고 이로인해 팔로워는 유명인의 새로운 트윗이 노출되는 시점 차이가 발생한다.


(3) 3차 시스템 구성

▢ 캐시(레디스) DB의 업데이트 비용을 개선하기 위해 사용자들 중 유명인의 정보를 필터링한다.  
▢ 사용자가 홈타임라인에 접속하면 사용자가 팔로우하는 대상 중 유명인들의 정보는 '1차 시스템 구성' 방식처럼 DB에서 조회하고 그 외의 사용자 정보는 '2차 시스템 구성' 방식처럼 캐시(레디스) DB에서 조회하여 결합한 최종 데이터를 받는다.


### 실습 목표

(1) '1차 시스템 구성'으로 애플리케이션을 구축한다.  

(2) 조회 부하를 발생시켜 확인한다.

(3) '2차 시스템 구성' 방식으로 전환한다.

(4) '(2)번'의 부하가 개선됐는지 확인한다.

(5) 100,000,000(1억)명의 팔로우를 가진 사용자 데이터를 생성한 후 fan-out 부하를 발생시키고 확인한다.

(6) '3차 시스템 구성' 방식으로 전환한다.

(7) '(5)번'의 부하가 개선됐는지 확인한다.

## 준비

- rdb(mariadb)
  - create database xapp;
  - create user 'xapp'@'%' identified by 'xapp';
  - grant all privileges on xapp.* to 'xapp'@'%';
  - flush privileges;

## 실습

### 1차 시스템 구성

팔로우한 사용자의 최근 트윗을 데이터베이스에 접근해서 조회하는 API를 개발한다.

### 조회 부하 테스트

부하 테스트는 대량으로 트윗 조회 API를 호출하고 응답 시간을 측정하여 판단한다.

테스트를 진행하기 위해서는 적절한 샘플 데이터가 필요하다.

#### 샘플 데이터

부하 테스트에 영향을 미치지 않는 아주 간단한 데이터를 세팅했다.  

> 부하의 단위가 DB 읽기 요청으로 판단했기 때문이다.

### 부하 테스트(1차 시스템)

부하 테스트는 Apache JMeter로 진행했다.  

> <시나리오>  
> 사용자가 팔로우의 최근 트윗을 조회하는 API를 호출하면 서버에서는 DB에서 데이터를 가져오며 DB 호출 카운트를 증가시킨다.

Apache JMeter 세팅

1. Thread Group 설정
   1. 유저 수(Number of Threads): 1000
   2. 유저 생성시간(Ramp-up period): 1
   3. Thread 반복(Loop Count): Infinite
2. HTTP Request Sampler 생성
3. Summary Report Listener 생성

### 2차 시스템 구성

메모리 기반의 데이터베이스인 Redis를 도입해서 RDB 읽기 요청 수를 개선한다.

팔로우한 사용자의 최근 트윗을 Redis에 업데이트하는 로직을 추가한다.

### 부하 테스트(2차 시스템)

[1] '부하 테스트(1차 시스템)'에서 진행한 테스트를 진행하면서 RDB 읽기 호출이 개선됐는지 확인한다.

팔로우한 사용자의 최근 트윗을 가져오는 API를 호출할 때 Redis에 캐시된 내용이 없어 캐시를 업데이트하기 위한 RDB 읽기 호출이 한 번 발생하고 이 후에는 RDB 읽기 호출이 발생하지 않는다.

■ 1차 시스템 구성: 10000건 호출 시 10000건의 RDB 읽기 호출 발생  
■ 2차 시스템 구성: 10000건 호출 시 1건의 RDB 읽기 호출 발생

[2] 유명인(사용자)가 트윗할 때 Redis 업데이트 부하로 인해 문제가 발생하는 상황을 경험한다.

▢ ~~1억~~ 1000만 1명의 사용자(유명인)를 준비하고 ~~1억~~ 1000만명이 한 명을 팔로우하는 데이터를 구성한다.   
▢ 사용자(유명인)이 트윗을 등록하면 ~~1억~~ 1000만명의 팔로워의 Redis 캐시를 업데이트한다.(부하 예상)  
▢ 팔로워들이 유명인이 등록한 새트윗을 확인하는 시점에 차이가 발생하는지 확인한다.   

#### 샘플 데이터 생성

1000만 건의 샘플 데이터를 생성하는 MariaDB 프로시저를 만들어서 실행했다.

[프로시저 생성 SQL](./SAMPLE.md)

LettuceConnection pipeline 명령 실행 시 5m Timeout 발생