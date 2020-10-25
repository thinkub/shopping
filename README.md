## 사용기술
* Java11
* spring boot 2.3.4
* jpa, query dsl, h2
* gradle
* Swagger

## Getting Started
* Build
```
./gradlew clean bootJar
```
* Run
```
java -jar build/libs/*.jar
```

## Api Documentation (Swagger)
`http://localhost:8081/swagger-ui.html`

## Table Schema

#### 상품 (Table : product)
* 상품 기본정보 Table

| column name | type | length | pk | fk | null | desc |
| --- | --- | --- | --- | --- | --- | --- | 
| productId | BigInt | | Y | N | N | 상품 아이디 |
| productName | varchar | 100 | N | N | N | 상품명 |
| main_category | varchar | 20 | N | N | N | 상품 카테고리 |
| sale_type | varchar | 20 | N | N | N | 상품 판매 유형 |
| sale_price | int | | N | N | N | 상품 판매 금액 |
| origin_price | int | | N | N | N | 상품 원가 금액 |
| discount_type | varchar | 10 | N | N | N | 할인 유형 |
| discount | decimal | 10,3 | N | N | N | 할인 정보 (정률, 정액) |
| display_yn | varchar | 1 | N | N | N | 상품 노출 여부 |
| register_datetime | date | | N | N | N | 등록일시 |

#### 상품 카테고리 (Table : product_category)
* 상품별 카테고리 정보 -> 상품은 n개의 카테고리 정보를 가질 수 있음

| column name | type | length | pk | fk | null | desc | 
| --- | --- | --- | --- | --- | --- | --- |
| product_category_id | BigInt | | Y | N | N | 상품 카테고리 아이디 |
| product_id | varchar | 100 | N | Y | N | 상품 아이디(product.productId) |
| category | varchar | 20 | N | N | N | 상품 카테고리 |
| register_datetime | date | | N | N | N | 등록일시 |

#### 상품 패키지 (table : product_package)
 * 묶음상품, 서브 상품 정보 Table
 
| column name | type | length | pk | fk | null | desc |
| --- | --- | --- | --- | --- | --- | --- | 
| product_package_id | BigInt | | Y | N | N | 상품 카테고리 아이디 |
| main_product_id | BigInt | | N | Y | N | 메인 상품 아이디(product.productId) |
| sub_product_id | BigInt | | N | Y | N | 서브 상품 아이디(product.productId) |
| discount_type | varchar | 10 | N | N | N | 서브 상품 할인 유형 |
| discount | decimal | 10,3 | N | N | N | 서브 상품 할인 정보 (정률, 정액) |
| register_datetime | date | | N | N | N | 등록일시 |







