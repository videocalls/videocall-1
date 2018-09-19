#!/bin/sh

#비회원 만료안내 메일전송 스케줄 (메일 발송 : 3개월전, 1개월전)
/usr/bin/curl http://127.0.0.1:8090/cmm/cron/guestMemberMailSend
#비회원 탈퇴(가동의서 만료) 스케줄
/usr/bin/curl http://127.0.0.1:8090/cmm/cron/deleteGuestMember
#회원 금융정보제공 동의서 재동의 (대상 : 가,나  메일 발송 : 1개월전, 3일전 ) 
/usr/bin/curl http://127.0.0.1:8090/cmm/cronSvcTermsEmail.do

#회원 삭제처리 스케줄
/usr/bin/curl http://127.0.0.1:8096/cmm/cron/deleteMemberProcess
#탈퇴회원 물리데이터 삭제 스케줄
/usr/bin/curl http://127.0.0.1:8096/cmm/cron/deleteMember
