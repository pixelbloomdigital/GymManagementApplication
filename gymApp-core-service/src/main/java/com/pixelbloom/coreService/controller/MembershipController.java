package com.pixelbloom.coreService.controller;

public class MembershipController {
    //Admin apis
    //1. Assignbatch

    /*
    Request :
{
  "visitorId": "V123",
  "batchType": "Zumba",
  "timeSlot": "7-8PM",
  "planType": "3-days-week",
  "price": 1500
}
Response :
{
  "memberId": "V123",
  "batchType": "Zumba",
  "timeSlot": "7-8PM",
  "planType": "3-days-week",
  "status": "INACTIVE",
  "price": 1500
}
confirm status active and confirm batch,membership choosen only after payment success
     */

    //2. GET /api/memberships/member/{memberId}
    //3. GET /api/memberships/plans (both user/Admin)
    //4. POST /api/memberships/expire
    //5. Create / Renew Membership
    //POST /api/memberships
    //{
    //  "memberId": 12,
    //  "planId": 2,
    //  "serviceType": "FITNESS",
    //  "startDate": "2026-01-01" }
}
