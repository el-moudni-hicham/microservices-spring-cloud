# 🌱 Microservices with Spring Cloud 

```
 • Spring Cloud Gateway
 • Eureka Discovery
 • Open Feign Rest Client
 • Hystrix DashBoard 
```
> Project Class Diagram
<table><tr><td>
<img src="https://user-images.githubusercontent.com/85403056/236585102-84377ea4-b260-4c5d-9245-a2c8ff2d2b54.png">
</td></tr></table>


## Table of contents
* [Customer Service](#customer-service)
* [Inventory Service](#inventory-service)
* [Gateway Service](#gateway-service)
* [Eureka Discovery Service](#eureka-discovery-service)
* [Billing Service](#billing-service)

## Customer Service
* Create a new Spring project :<br>

<table><tr>
<td ">
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236404166-0cdd0aed-05b3-4c44-95d6-79daf41bf5c6.png" alt="project example" width="75%"/>
            </p>

</td></tr></table>

```
Selected dependencies :
• Spring Web 
• Spring Data JPA 
• H2 Database
• Rest Repositories 
• Lombok
• Spring Boot DevTools
• Eureka Discovery Client
• Spring Boot Actuator 
```
Application Configuration `application.properties`
```java
spring.application.name=customer-service
spring.datasource.url=jdbc:h2:mem:customers-db
spring.cloud.discovery.enabled=true
#management.endpoints.web.exposure.include=*
server.port=8888
```

<h3> Steps : </h3>

  ## 1. Create `Customer` entity
  ```java
package ma.enset.customermicroservice.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
```
  ## 2. Create `CustomerRepository` Spring Data based interface
  ```java
  package ma.enset.customermicroservice.repository;

import ma.enset.customermicroservice.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
```
 ## 3. Deploy Restful API of microservice with Spring Data Rest
 ## 4. Microservice test
  

<table>
<tr>
<td width="50%">
          <h5 align="center">Customers</h3>
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236414072-e9bf49dd-05fd-4d52-b5c5-f8ecf49f5cc8.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <h5 align="center">Find Customer By Id</h3>
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236414160-d0a7da52-909c-48d9-b592-80dbecf7707e.png" alt="project example"/>
            </p>
 </td>
</tr>

<tr>
<td width="50%">
          <h5 align="center">Actuator</h5>
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236414349-3f86ffb3-2e2a-4815-a55a-4b1ab40dddb1.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <h5 align="center">Actuator Health<h5>
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236414431-f043594d-a237-4149-a407-80a05959fd87.png" alt="project example"/>
            </p>
 </td>
</tr>
</table>

* Database [H2](http://localhost:8888/h2-console)
<table>
<tr>
<td width="50%">
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236420735-a54885b7-bd01-4f76-8caf-26ae19ed59e7.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236420641-5c5ab66e-5208-4265-8e93-378bacad55ef.png"/>
            </p>
 </td>
</tr>
</table>

## Inventory Service

Application Configuration `application.properties`
```java
spring.application.name=inventory-service
spring.datasource.url=jdbc:h2:mem:products-db
spring.cloud.discovery.enabled=true
server.port=8889
```

<h3> Steps : </h3>

  1. Create `Product` entity
  2. Create `ProductRepository` Spring Data based interface
  3. Deploy Restful API of microservice with Spring Data Rest
  4. Microservice test
  
<table>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">All Products</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236423805-4c7aa516-5ebe-4aef-bd31-938dfe7b3bc6.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Database</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236424054-105ae425-fcc9-4405-a7de-8db996600e09.png"/>
            </p>
 </td>
</tr>
</table>

## Gateway Service

```
Selected dependencies
• Gateway 
• Spring Boot Actuator
• Hystrix 
• Eureka Discovery Client 
```
## 1. Static routes configuration : application.yml / application.properties
`application.properties`
```java
spring.application.name=gateway-service
spring.cloud.discovery.enabled=true
server.port=8890
```
`application.yml`
```java
spring:
  cloud:
    gateway:
      routes:
        - id : r1
          uri : http://localhost:8888/
          predicates :
            - Path= /customers/**
        - id : r2
          uri : http://localhost:8889/
          predicates :
            - Path= /products/**
```
<table>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">Access to All Customers from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428273-48a62fce-bc15-462c-8f32-9ad081cf9e39.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Access to All Products from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428181-ac3075b3-b3e9-4747-9f1e-f4a829748d8f.png"/>
            </p>
 </td>
</tr>
</table>


## 2. Static routes configuration : Java Config Class

```java
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("r1", (r) -> r.path("/customers/**").uri("http://localhost:8888/"))
                .route("r2", (r) -> r.path("/products/**").uri("http://localhost:8889/"))
                .build();
    }
```
<table>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">Access to All Customers from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428273-48a62fce-bc15-462c-8f32-9ad081cf9e39.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Access to All Products from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428181-ac3075b3-b3e9-4747-9f1e-f4a829748d8f.png"/>
            </p>
 </td>
</tr>
</table>

## 3. Eureka Discovery Service : Dynamic Routing

## Eureka Discovery Service

```
Selected dependencies
• Eureka Server
```
 EnableEurekaServer 
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryApplication.class, args);
    }
}
```

`application.properties`
```java
server.port=8761
# dont register server itself as a client.
eureka.client.fetch-registry=false
# Does not register itself in the service registry.
eureka.client.register-with-eureka=false
```
[Eureka Spring](http://localhost:8761/)
<table>
<tr>
 <td width="50%">
          <p align="center">
             <img src="https://user-images.githubusercontent.com/85403056/236432037-8d12c52d-da2a-4b73-86d6-6f4de662a4d9.png"/>
            </p>
 </td>
</tr>
</table>

* Static routes configuration with Discovery Service
```java
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("r1", (r) -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route("r2", (r) -> r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }
```

<table>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">Access to All Customers from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428273-48a62fce-bc15-462c-8f32-9ad081cf9e39.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Access to All Products from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236428181-ac3075b3-b3e9-4747-9f1e-f4a829748d8f.png"/>
            </p>
 </td>
</tr>
</table>

* Dynamic routes configuration with Discovery Service

```java
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc,
                                                            DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }
```

<table>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">Access to All Customers from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236434078-2c1d2ac9-8e38-48cf-902a-11c4479c904d.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Access to All Products from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236434249-ae66d7c8-6cfc-425e-bc52-b647332c3733.png"/>
            </p>
 </td>
</tr>
</table>

## Billing Service

```
Selected dependencies
• OpenFeign 
• Spring HATEOAS
```
* Service Test

<table>
<tr>
<td width="50%">
           <p align="center">
             <h5 align="center">Bill Complet Informations</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236586249-7faed1f5-7037-41dd-8e6c-67a258553c72.png" alt="project example"/>
            </p>
 </td>
</tr>
<tr>
<td width="50%">
          <p align="center">
             <h5 align="center">Bill Complet Informations</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236586493-4da8fa55-2935-4a2f-996d-b9da75f21d9a.png" alt="project example"/>
            </p>
 </td>
 <td width="50%">
          <p align="center">
             <h5 align="center">Access to All Products from Gateway</h5>
             <img src="https://user-images.githubusercontent.com/85403056/236586397-626bb453-51c1-4b32-b65c-5db55acb7af5.png"/>
            </p>
 </td>
</tr>
</table>
