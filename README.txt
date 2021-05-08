Claim Management

Introduction
A leading HealthCare Management Organisation wants to strengthen its Middleware by exposing the core logic related to Claims Management as Microservices.This middleware Microservices will be hosted on cloud so that all the up/downstream applications can get an access to this performing business transactions.
There will also be a Member Portal to be developed part of this scope that consumes these Microservices and responses back to memberswho are in need of claim related information.
In this project ,there are  microservices registered with eureka server and a client application for frontend.Communication between services is done using feign clients through zuul(API gateway). Spring security also  implemented based on  JWT(JSON Web Token).Fault tolerance results are retrieved using Hystrix.It also uses in-memory database to deploy code independently.


Individual Components of the system

Member Microservice
Member Portal app is the only client application this microservice.An authorized member can view the premium bills,submit the claim,can view the claim status,which is already submitted.
Post authorization,basic member based validation are performed and then it communicates to the other microservices for retrieving required data.
Member Microservice will interact with Claim Microservice with the following functionalities:
To check the eligibility of the member and claim and then action to the claim set for processing.
To get the status of already submitted claim.

Claim Microservice
Member Microservice interact with Claim Microservice.Post authorization of request,Claim Microservice allows the following operations:
To view status of the submitted claim:
Retrieve the claim status from database and return
To verify claim eligibility by interacting with Policy Microservice and action settlement:
View the claim details and check the following:
If the claimed amount is applicable under subscribed policy.
If the claimed benefit is applicable under subscribed policy.
If the Hospital in which benefits are availed is a Permissible Health Care Provider(Hospital).
If the above 3 criteria are satisfied ,update the claim as “Pending Action” else “Claim Rejected”.

Policy Microservice
Claim MIcroservice interact with policy microservice.Post authorization of request,Claim microservice allows the following operations:
Provide the permissible providers in which healthcare services can be offered.
Provide the list of benefits which the member is under is eligible to under a subscribed policy.
To provide the eligible claim amount ,for the given benefit under a subscribed policy.

The client application is called the Member portal. The user can login to the portal and can perform functions like viewing the premium he has to pay and can also submit a claim.









