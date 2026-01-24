package com.pixelbloom.coreService.controller;

public class AttendanceController {

    /*
  1.   Mark Attendance  : POST
Request

{
  "memberId": "V123",
  "batchId": "B01",
  "date": "2026-01-22",
  "timeSlot": "7-8PM",
  "markedBy": "trainer/admin"
}
Response :

{
  "attendanceId": "A123",
  "memberId": "V123",
  "batchId": "B01",
  "date": "2026-01-22",
  "status": "MARKED"
}

----------------------
2. GET  : /members/{memberId}/attendance?batchId=B01
3. GET /api/attendance/today
4. GET /api/attendance/date/{date}
5. PUT /api/attendance/{attendanceId}
6. DELETE /api/attendance/{attendanceId}
7. GET /api/attendance/member/{memberId}
8. GET /api/attendance/batch/{batchId}
9. GET /api/attendance/batch/{batchId}/date/{date}
10. GET /api/attendance/member/{memberId}/date/{date}
11. GET /api/attendance/member/{memberId}/month/{month}
-----------------------------------------------------------
12. POST /api/attendance/check-in - USER
13. POST /api/attendance/check-out

     */
}
